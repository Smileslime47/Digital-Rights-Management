<script setup lang="ts">
import routeTo from "~/route/routeTo.ts";
import User from "~/modules/User.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElMessage} from "element-plus";

const props = defineProps<{
  user: User
  selfProfile: boolean
  loaded:boolean
}>()

const update = () => {
  httpService.post(
      Constant.Api.USER.ROOT,
      props.user
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
  <el-card>
    <h1>个人资料</h1>
    <el-form label-width="auto" v-if="loaded">
      <el-form-item label="用户名">
        <el-input v-model="user.username" style="width: 240px" disabled v-if="selfProfile"/>
        <el-text style="width: 240px" v-else>{{ user.username }}</el-text>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="user.nickname" style="width: 240px" v-if="selfProfile"/>
        <el-text style="width: 240px" v-else>{{ user.nickname }}</el-text>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="user.phoneNumber" style="width: 240px" v-if="selfProfile"/>
        <el-text style="width: 240px" v-else>{{ user.phoneNumber }}</el-text>
      </el-form-item>
      <el-form-item label="电子邮箱">
        <el-input v-model="user.email" style="width: 240px" v-if="selfProfile"/>
        <el-text style="width: 240px" v-else>{{ user.email }}</el-text>
      </el-form-item>
    </el-form>
    <el-skeleton :rows="4" animated v-else />
    <el-button-group v-if="selfProfile">
      <el-button text bg type="primary" @click="update">提交修改</el-button>
      <el-button text bg type="warning" @click="routeTo.changePassword">修改密码</el-button>
      <el-button text bg @click="routeTo.chainAccount">链上账户</el-button>
    </el-button-group>
  </el-card>
</template>