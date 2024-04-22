<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElMessage} from "element-plus";

const props = defineProps<{
  addr: string,
}>()

const pendingRights = ref([])
const maxRightPage = ref(0)
const rightPageNow = ref("1")

const chainPassword = ref("")

const getPendingRights = (addr: String, page: String) => {
  //获取账户待审合约
  httpService.get(
      Constant.Api.PENDING_RIGHT.ROOT,
      {
        params: {
          addr: addr,
          page: page
        }
      }
  ).then((data) => {
    pendingRights.value = data[Constant.RespondField.RIGHT]
    maxRightPage.value = Math.ceil(data[Constant.RespondField.COUNT] / Constant.Global.DEFAULT_PAGE_SIZE)
  })
}

const deployRight = (id: number) => {
  httpService.post(
      Constant.Api.PENDING_RIGHT.DEPLOY,
      {
        password: chainPassword.value,
        pendingId: id
      },
  ).then((data) => {
    let cost = data[Constant.RespondField.COST]
    ElMessage.success("部署成功,实际花费" + cost + " WEI。")
  })
  ElMessage.info("已发送部署交易请求，部署成功后会向您发送通知。")
}


watch(rightPageNow, (newVal) => {
  getPendingRights(props.addr, newVal)
})


onMounted(() => {
  getPendingRights(props.addr, rightPageNow.value)
})
</script>

<template>
  <el-table style="width: 100%" :data="pendingRights" stripe>
    <el-table-column type="expand">
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
              <el-button type="primary" text bg class="smooth-button" @click="deployRight(props.row.id)">确认部署
              </el-button>
            </el-col>
          </el-row>
        </el-space>
      </template>
    </el-table-column>
    <el-table-column prop="title" label="版权标题"/>
    <el-table-column prop="owner" label="版权所有人"/>
    <el-table-column prop="registrationNumber" label="版权登记号"/>
    <el-table-column label="发行时间">
      <template #default="scope">
        {{ new Date(scope.row.issueTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="expireTime" label="到期时间">
      <template #default="scope">
        {{ new Date(scope.row.expireTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="申请时间">
      <template #default="scope">
        {{ new Date(scope.row.createTime).toLocaleString() }}
      </template>
    </el-table-column>
    <el-table-column prop="description" label="版权描述"/>
    <el-table-column prop="status" label="合约状态"/>
  </el-table>
  <el-pagination layout="prev, pager, next" :page-count="maxRightPage" v-model:current-page="rightPageNow"/>
</template>