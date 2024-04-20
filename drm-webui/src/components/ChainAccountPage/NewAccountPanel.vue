<script setup lang="ts">
import {ElLoading, ElMessage} from "element-plus";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";

const passwordForm = reactive({
  password: "",
  confirmPassword: ""
})

const confirmCreate = () => {
  const loadingInstance = ElLoading.service()
  httpService.post(
      Constant.Api.CHAIN.ACCOUNT.NEW_ACCOUNT,
      passwordForm
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("创建成功:" + data[Constant.RespondField.ADDRESS])
    } else {
      ElMessage.error("创建失败！")
    }
    loadingInstance.close()
    routeTo.fresh()
  })
}
</script>

<template>
  <el-card>
    <h1>创建新的链上账户</h1>
    <el-form label-width="auto" :model="passwordForm">
      <el-form-item label="密码">
        <el-input show-password v-model="passwordForm.password"/>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input show-password v-model="passwordForm.confirmPassword"/>
      </el-form-item>
      <el-form-item>
        <el-button text bg type="primary" @click="confirmCreate">确认</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>