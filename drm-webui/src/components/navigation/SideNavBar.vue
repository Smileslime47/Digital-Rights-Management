<script setup lang="ts">
import routeTo from "~/route/routeTo.ts";
import TokenUtils from "~/server/TokenUtils.ts";
import {useRoute} from "vue-router";
import Constant from "~/constant/Constant.ts";

const userId = ref(0)
const logged = ref(false)
const noticeFilter = ref("all")
const permissionVerify = ref(false)

onMounted(() => {
  TokenUtils.getUser(
      useRoute().path
  ).then((result) => {
    if (result != null) {
      logged.value = true
      userId.value = result.id
    }
  })
})
</script>

<template>
  <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
  >
    <el-menu-item index="1" @click="routeTo.profile(userId)">
      <span>个人资料</span>
    </el-menu-item>
    <el-menu-item index="2" @click="routeTo.notice(noticeFilter,Constant.Global.DEFAULT_PAGE)">
      <span>通知列表</span>
    </el-menu-item>
    <el-menu-item index="3" @click="routeTo.chainAccount">
      <span>链上账户</span>
    </el-menu-item>
    <el-menu-item index="4" @click="routeTo.createRight">
      <span>上传资源</span>
    </el-menu-item>
  </el-menu>
</template>