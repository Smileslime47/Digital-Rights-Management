<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import User from "~/modules/User.ts"
import Group from "~/modules/Group.ts";

const user = ref(new User())
const group = ref(new Group())
const selfProfile = ref(false)

fresh(async (route) => {
  //获取用户信息
  await httpService.get(
      Constant.Api.USER.ROOT,
      {
        params: {
          id: route.params.id
        }
      }
  ).then((data) => {
    selfProfile.value = data[Constant.RespondField.SELF_PROFILE] as boolean
    user.value = data[Constant.RespondField.USER] as User
  })

  //获取用户组信息
  await httpService.get(
      Constant.Api.GROUP.ROOT,
      {
        params: {
          id: user.value.permissionId
        }
      }
  ).then((data) => {
    group.value = data[Constant.RespondField.GROUP] as Group
  })
})
</script>

<template>
  <TemplatePage side="true">
    <ProfilePanel :user="user" :selfProfile="selfProfile" />

    <el-divider />

    <GroupPanel :group="group" :selfProfile="selfProfile" />
  </TemplatePage>
</template>