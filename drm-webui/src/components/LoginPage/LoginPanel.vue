<script setup lang="ts">
import {reactive} from "vue";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";

const registerFlag = defineModel({type: Boolean})

const loginForm = reactive({
  username: '',
  password: '',
  rememberMe: false,
})

const switchToRegister = () => {
  registerFlag.value = true
}

const login = () => {
  httpService.post(
      Constant.Api.USER.LOGIN,
      loginForm
  ).then((principleMap) => {
    console.log(principleMap)
    localStorage.setItem(Constant.Authentication.TOKEN_STORAGE, principleMap[Constant.Authentication.TOKEN_STORAGE])
    localStorage.setItem(Constant.Authentication.USER_ID_CLAIM, principleMap[Constant.Authentication.USER_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.GROUP_ID_CLAIM, principleMap[Constant.Authentication.GROUP_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.EXPIRE_TIME_CLAIM, principleMap[Constant.Authentication.EXPIRE_TIME_CLAIM])
    routeTo.home()
  })
}
</script>

<template>
  <div class="vertical-center">
          <span class="large-text">
            登入
          </span>
    <el-form :model="loginForm" class="login-form">
      <el-form-item>
        <el-input v-model="loginForm.username" placeholder="用户名"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="loginForm.password" placeholder="密码" type="password" show-password/>
      </el-form-item>
      <el-form-item label="记住我">
        <el-switch v-model="loginForm.rememberMe"/>
      </el-form-item>
    </el-form>
    <el-space>
      <el-button
          class="smooth-button"
          size="large"
          text
          bg
          plain
          @click="login()">登入
      </el-button>
      <el-button
          class="smooth-button"
          size="large"
          text
          bg
          plain
          @click="switchToRegister()">注册
      </el-button>
    </el-space>
  </div>
</template>

<style scoped>
.login-form {
  width: 60%;
  margin-top: 20px
}
</style>