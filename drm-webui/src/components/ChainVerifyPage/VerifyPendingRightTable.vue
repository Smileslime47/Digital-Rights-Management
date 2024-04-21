<script setup lang="ts">
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";
import {ElMessage} from "element-plus";
import {reactive} from "vue";

const rejectWindowVisible = ref(false)
const pendingRights = ref([])
const maxPage = ref(0)
const pageNow = ref("")

const RejectForm = reactive({
  pendingRightId: 0,
  rejectReason: ""
})

watch(pageNow, (newVal) => {
  routeTo.chainVerify(newVal)
})

fresh(async (route) => {
  pageNow.value = <string>route.params.page

  //获取账户待审合约
  await httpService.get(
      Constant.Api.PENDING_RIGHT.VERIFY,
      {
        params: {
          page: pageNow.value
        }
      }
  ).then((data) => {
    pendingRights.value = data[Constant.RespondField.RIGHT]
    maxPage.value = Math.ceil(data[Constant.RespondField.COUNT] / Constant.Global.DEFAULT_PAGE_SIZE)

    console.log(pendingRights.value)
  })
})

const confirm = (rightId: number) => {
  httpService.post(
      Constant.Api.PENDING_RIGHT.CONFIRM,
      {rightId},
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
      Constant.Api.PENDING_RIGHT.REJECT,
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
  RejectForm.pendingRightId = 0
  rejectWindowVisible.value = false
}

const handleRejectButton = (rightId: number) => {
  rejectWindowVisible.value = true
  RejectForm.pendingRightId = rightId
}
</script>

<template>
  <el-table style="width: 100%" :data="pendingRights" stripe>
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
    <el-table-column prop="description" label="版权描述"/>
    <el-table-column prop="status" label="合约状态"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button text bg type="primary" @click="confirm(scope.row.id)">通过</el-button>
          <el-button text bg type="danger" @click="handleRejectButton(scope.row.id)">拒绝</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination layout="prev, pager, next" :page-count="maxPage" v-model:current-page="pageNow"/>

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