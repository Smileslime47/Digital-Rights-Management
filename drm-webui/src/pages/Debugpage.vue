<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import {ElMessage} from "element-plus";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";
import TemplatePage from "~/pages/TemplatePage.vue";

const debugUser = () => {
  httpService.get(
      Constant.Api.DEBUG_API+Constant.Api.DEBUG_USER,
  ).then((data) => {
    //@ts-ignore
    let debugUser = data as User
    ElMessage.info(debugUser.nickname)
  })
}

const errorStatus = () => {
  httpService.get(
    Constant.Api.DEBUG_API + Constant.Api.DEBUG_STATUS
  ).then((data) => {
    ElMessage.info(data as any)
  })
}

const toProfile = () => {
  routeTo.profile(1)
}
</script>

<template>
  <TemplatePage>
    <el-button @click="debugUser">
      测试按钮
    </el-button>
    <el-button @click="errorStatus">
      错误码测试
    </el-button>
    <el-button @click="toProfile">
      前往个人页
    </el-button>
  </TemplatePage>
</template>

<style scoped>

</style>