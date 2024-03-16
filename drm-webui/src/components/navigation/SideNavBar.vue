<script setup lang="ts">
import fresh from "~/composables/fresh.ts";
import routeTo from "~/route/routeTo.ts";
import TokenUtils from "~/server/TokenUtils.ts";
import {useRoute} from "vue-router";

const userId = ref(0)
const logged = ref(false)

fresh((_) => {
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
      default-active="2"
      class="el-menu-vertical-demo"
  >
    <el-menu-item index="1" @click="routeTo.profile(userId)">
      <span>个人资料</span>
    </el-menu-item>
    <el-menu-item index="2">
      <span>上传资源</span>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <span>获取授权</span>
    </el-menu-item>
    <el-menu-item index="4">
      <span>版权审核</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>
</style>