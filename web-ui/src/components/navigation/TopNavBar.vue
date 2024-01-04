<script setup lang="ts">
import githubIcon from "~/assets/icons/github.svg"
import webIcon from "~/assets/icons/web-icon.svg"


const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const goToGithub=()=>{
  window.open("https://github.com/Smileslime47/Digital-Rights-Management")
}
</script>

<template>
  <el-menu
      mode="horizontal"
      :ellipsis=false
      @select="handleSelect"
  >
    <el-menu-item index="0">
      <el-space>
        <img
            style="width: 40px"
            :src=webIcon
            alt="webIcon"
        />
        <el-text size="large" class="mx-1">Digital Rights Manager</el-text>
      </el-space>
    </el-menu-item>
    <div class="flex-grow"/>
    <el-menu-item index="1">
      <el-text>登入</el-text>
    </el-menu-item>
    <el-menu-item index="2">
      <el-text>注册</el-text>
    </el-menu-item>
    <el-divider direction="vertical" border-style="solid" />
    <el-menu-item index="3" @click="goToGithub">
      <img
          style="width: 20px"
          :src=githubIcon
          alt="githubIcon"
      />
    </el-menu-item>
  </el-menu>
</template>

<style scoped>
.el-menu{
  padding-left: 50px;
  padding-right: 50px;
}
.el-divider{
  height:100%
}
.flex-grow {
  flex-grow: 1;
}
</style>
