<script setup lang="ts">
import webIcon from "~/assets/icons/web-icon.svg";
</script>

<template>
<div class="footer">
  <el-space>
    <el-image
        style="width: 40px"
        :src=webIcon
        alt="webIcon"
    />
    <el-text size="large" >Digital Rights Manager</el-text>
    <el-text size="small">Copyright (c) 2024 Smile slime 47 </el-text>
    <el-text size="large">本项目采用 <el-link type="primary" href="https://github.com/Smileslime47/Digital-Rights-Management/blob/main/LICENSE">MIT</el-link> 协议进行许可</el-text>
  </el-space>
</div>
</template>

<style scoped>
.footer{
  background: #f5f7fa;
  height: 100%;
  min-height: 200px;
  padding:0 25%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>