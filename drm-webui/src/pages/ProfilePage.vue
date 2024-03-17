<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import httpService from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import User from "~/modules/User.ts"
import Group from "~/modules/Group.ts";
import {ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";

const user = ref(new User())
const group = ref(new Group())
const selfProfile = ref(false)
fresh((route) => {
  httpService.get(
      Constant.Api.USER_API,
      {
        params: {
          id: route.params.id
        }
      }
  ).then((data) => {
    selfProfile.value = data[Constant.RespondField.SELF_PROFILE]
    user.value = data[Constant.RespondField.USER] as User
  })
  httpService.get(
      Constant.Api.GROUP_API,
      {
        params: {
          id: user.value.permissionId
        }
      }
  ).then((data) => {
    group.value = data[Constant.RespondField.GROUP] as Group
  })
})
const update = () => {
  console.log(user)
  httpService.post(
      Constant.Api.USER_API,
      user.value
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
    <h1>个人资料</h1>
    <el-form label-width="auto">
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
    <el-button-group v-if="selfProfile">
      <el-button text bg type="primary" @click="update">提交修改</el-button>
      <el-button text bg type="warning" @click="routeTo.changePassword">修改密码</el-button>
      <el-button text bg  @click="routeTo.chainAccount">链上账户</el-button>
    </el-button-group>

    <h1>用户组</h1>
    <el-descriptions column="1">
      <el-descriptions-item label="用户组">
        <el-tag size="small">{{ group.groupName }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item v-if="selfProfile">
        <el-descriptions :border=true title="权限">
          <el-descriptions-item label="允许登陆">{{ group.permissionLogin }}</el-descriptions-item>
          <el-descriptions-item label="允许展示资料">{{ group.permissionShowProfile }}</el-descriptions-item>
          <el-descriptions-item label="允许上传版权资源">{{ group.permissionCreateRight }}</el-descriptions-item>
          <el-descriptions-item label="允许获取版权授权">{{ group.permissionCreateLicense }}</el-descriptions-item>
          <el-descriptions-item label="允许创建链上账户">{{ group.permissionCreateChainAccount }}</el-descriptions-item>
        </el-descriptions>
      </el-descriptions-item>
    </el-descriptions>
  </TemplatePage>
</template>

<style scoped>
.el-button-group {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>