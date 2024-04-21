<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import fresh from "~/composables/fresh.ts";
import {ElMessage} from "element-plus";
import {reactive} from "vue";
import RightData from "~/modules/RightData.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean
}>()

const rightAddr = ref(<string>useRoute().params.right)

const rejectWindowVisible = ref(false)
const pendingLicenses = ref([])
const maxLicensePage = ref(0)
const licensePageNow = ref(1)

const chainPassword = ref("")

const RejectForm = reactive({
  pendingLicenseId: 0,
  rejectReason: ""
})

const confirm = (licenseId: number) => {
  httpService.post(
      Constant.Api.PENDING_LICENSE.CONFIRM,
      {licenseId},
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("提交成功！")
    } else {
      ElMessage.error("提交失败！")
    }
  })
}

const reject = () => {
  httpService.post(
      Constant.Api.PENDING_LICENSE.REJECT,
      RejectForm
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("提交成功！")
    } else {
      ElMessage.error("提交失败！")
    }
  })
  RejectForm.rejectReason = ""
  RejectForm.pendingLicenseId = 0
  rejectWindowVisible.value = false
}

const handleRejectButton = (rightId: number) => {
  rejectWindowVisible.value = true
  RejectForm.pendingLicenseId = rightId
}

const getPendingLicenses = (addr: string, page: number) => {
  //获取账户待审合约
  httpService.get(
      Constant.Api.PENDING_LICENSE.BY_RIGHT,
      {
        params: {
          addr: addr,
          page: page
        }
      }
  ).then((data) => {
    pendingLicenses.value = data[Constant.RespondField.LICENSE]
    maxLicensePage.value = Math.ceil(data[Constant.RespondField.COUNT] / Constant.Global.DEFAULT_PAGE_SIZE)
  })
}

watch(licensePageNow, (newVal) => {
  getPendingLicenses(rightAddr.value, newVal)
})

fresh(() => {
  console.log(props.isDeployer)
  getPendingLicenses(rightAddr.value, licensePageNow.value)
})
</script>

<template>
  <el-table style="width: 100%" :data="pendingLicenses" stripe>
    <el-table-column type="expand" v-if="!isDeployer">
      <template #default="props">
        <el-space direction="vertical" style="width:100%" fill>
          <el-text>{{ props.row.comment }}</el-text>
          <el-row v-if="props.row.status === Constant.PendingStatus.CONFIRMED">
            <el-col :span="16">
              <el-input v-model="chainPassword" placeholder="区块链账户密码"/>
            </el-col>
            <el-col :offset="1" :span="4">
              <el-text>预估部署价格：</el-text>
              <el-text>{{ props.row.estimatePrice / 1e18 }} ETH</el-text>
            </el-col>
            <el-col :offset="1" :span="2">
              <el-button type="primary" text bg class="smooth-button" @click="deployLicense(props.row.id)">确认部署
              </el-button>
            </el-col>
          </el-row>
        </el-space>
      </template>
    </el-table-column>
    <el-table-column prop="rightTitle" label="版权标题"/>
    <el-table-column prop="rightAddr" label="版权地址"/>
    <el-table-column prop="owner" label="授权所有人"/>
    <el-table-column label="授权起始时间">
      <template #default="scope">
        {{ new Date(scope.row.issueTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="expireTime" label="授权到期时间">
      <template #default="scope">
        {{ new Date(scope.row.expireTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="申请时间">
      <template #default="scope">
        {{ new Date(scope.row.createTime).toLocaleString() }}
      </template>
    </el-table-column>
    <el-table-column prop="description" label="授权描述"/>
    <el-table-column prop="status" label="合约状态"/>
    <el-table-column v-if="isDeployer" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button text bg type="primary" @click="confirm(scope.row.id)">通过</el-button>
          <el-button text bg type="danger" @click="handleRejectButton(scope.row.id)">拒绝</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination layout="prev, pager, next" :page-count="maxLicensePage" v-model:current-page="licensePageNow"/>

  <el-dialog
      v-model="rejectWindowVisible"
      title="拒绝原因"
      width="500"
  >
    <el-input autosize type="textarea" v-model="RejectForm.rejectReason"></el-input>
    <template #footer>
      <div class="dialog-footer">
        <el-button text bg @click="rejectWindowVisible = false">取消</el-button>
        <el-button text bg type="primary" @click="reject">确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>
