<script setup lang="ts">
import fresh from "~/composables/fresh.ts";
import httpService from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import User from "~/modules/User.ts"

const user = reactive(new User())
fresh((route) => {
  httpService.get(
      Constant.Api.USER_API,
      {
        params: {
          id: route.params.id
        }
      }
  ).then((data) => {
    let userRet = data as User
    console.log(userRet)
    user.username = userRet.username
    console.log(user)
  })
})
</script>

<template>
  <el-descriptions title="User Info">
    <el-descriptions-item label="Username">{{ user.username }}</el-descriptions-item>
    <el-descriptions-item label="Telephone">18100000000</el-descriptions-item>
    <el-descriptions-item label="Place">Suzhou</el-descriptions-item>
    <el-descriptions-item label="Remarks">
      <el-tag size="small">School</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="Address"
    >No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu
      Province
    </el-descriptions-item
    >
  </el-descriptions>
</template>

<style scoped>

</style>