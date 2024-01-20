<script setup lang="ts">

import {reactive} from 'vue'
import routeTo from "~/route/routeTo.ts";

// do not use same name with ref
const form = reactive({
  username: '',
  password: '',
  rememberMe: false,
})

</script>

<template>
  <div class="horizontal-center">
    <el-space direction="vertical">
      <span style="font-size: 32px">
          登入
      </span>
      <el-form :model="form" style="min-width: 500px;margin-top:20px">
        <el-form-item>
          <el-input v-model="form.username" placeholder="用户名"/>
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" placeholder="密码"/>
        </el-form-item>
        <el-form-item label="记住我">
          <el-switch v-model="form.rememberMe"/>
        </el-form-item>
      </el-form>
      <el-button-group style="margin-top:20px">
        <el-button size="large" type="primary" plain @click="routeTo.login()">登入</el-button>
        <el-button size="large" plain>注册</el-button>
      </el-button-group>
    </el-space>
  </div>
</template>
<style scoped>

</style>