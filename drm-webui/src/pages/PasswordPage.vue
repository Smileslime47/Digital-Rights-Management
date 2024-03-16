<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import httpService from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";
import fresh from "~/composables/fresh.ts";
import TokenUtils from "~/server/TokenUtils.ts";
import {useRoute} from "vue-router";

const changePasswordForm = reactive({
  oldPassword: "",
  newPassword: "",
  confirmPassword: ""
})
const userId = ref(0)
fresh(async (_) => {
  TokenUtils.getUser(
      useRoute().path
  ).then((result) => {
    if (result != null) {
      userId.value = result.id
    }
  })
})
const confirmChange = () => {
  httpService.post(
      Constant.Api.USER_API + Constant.Api.USER_CHANGE_PWD,
      changePasswordForm
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("修改成功！")
    } else {
      ElMessage.error("修改失败！")
    }
  })
}
</script>

<template>
  <TemplatePage side="true">
    <h1>修改密码</h1>
    <el-form label-width="auto" :model="changePasswordForm">
      <el-form-item label="原密码">
        <el-input show-password v-model="changePasswordForm.oldPassword"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input show-password v-model="changePasswordForm.newPassword"/>
      </el-form-item>
      <el-form-item label="确认新密码">
        <el-input show-password v-model="changePasswordForm.confirmPassword"/>
      </el-form-item>
      <el-form-item>
        <el-button-group>
          <el-button text bg type="primary" @click="confirmChange">确认</el-button>
          <el-button text bg @click="routeTo.profile(userId)">返回</el-button>
        </el-button-group>
      </el-form-item>
    </el-form>
  </TemplatePage>
</template>