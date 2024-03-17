<script setup lang="ts">
import {reactive} from "vue";
import routeTo from "~/route/routeTo.ts";
import Constant from "~/constant/Constant.ts";
import httpService from "~/server/http.ts";
import circleCheck from "~/assets/icons/CircleCheck.svg"
import warning from "~/assets/icons/warning.svg";
import {ElMessage} from "element-plus";
import TemplatePage from "~/pages/TemplatePage.vue";

const loginForm = reactive({
  username: '',
  password: '',
  rememberMe: false,
})

const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  email: '',
  phoneNumber: '',
})

const registerFlag = ref(false)

const switchToRegister = () => {
  registerFlag.value = true
}
const switchToLogin = () => {
  registerFlag.value = false
}

const login = () => {
  httpService.post(
      Constant.Api.USER_API + Constant.Api.USER_LOGIN,
      loginForm
  ).then((principleMap) => {
    console.log(principleMap)
    localStorage.setItem(Constant.Authentication.TOKEN_STORAGE, principleMap[Constant.Authentication.TOKEN_STORAGE])
    localStorage.setItem(Constant.Authentication.USER_ID_CLAIM, principleMap[Constant.Authentication.USER_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.GROUP_ID_CLAIM, principleMap[Constant.Authentication.GROUP_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.EXPIRE_TIME_CLAIM,principleMap[Constant.Authentication.EXPIRE_TIME_CLAIM])
    routeTo.home()
  })
}

const register = () => {
  if (registerForm.password !== registerForm.confirmPassword) {
    ElMessage.warning("两次密码输入不一致，请先确认您的密码")
    return
  }
  httpService.post(
      Constant.Api.USER_API + Constant.Api.USER_REGISTER,
      registerForm
  ).then((principleMap) => {
    localStorage.setItem(Constant.Authentication.TOKEN_STORAGE, principleMap[Constant.Authentication.TOKEN_STORAGE])
    localStorage.setItem(Constant.Authentication.USER_ID_CLAIM, principleMap[Constant.Authentication.USER_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.GROUP_ID_CLAIM, principleMap[Constant.Authentication.GROUP_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.EXPIRE_TIME_CLAIM,principleMap[Constant.Authentication.EXPIRE_TIME_CLAIM])
    routeTo.home()
  })
}
</script>

<template>
  <TemplatePage>
    <el-row class="login-container">
      <el-col :span="12">
        <div class="hero-title text-center">
        <span class="hero-text text-blue">
          基于区块链的
        </span>
          <br/>
          <span class="hero-text text-black" style="margin-top: 20px">
          数字版权解决方案
      </span>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="vertical-center" v-if=!registerFlag>
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
        <div class="vertical-center" v-else>
      <span style="font-size: 32px">
          注册
      </span>
          <el-form :model="registerForm" class="login-form">
            <el-form-item>
              <el-input v-model="registerForm.username" placeholder="用户名"/>
            </el-form-item>
            <el-form-item>
              <el-col :span="10">
                <el-input v-model="registerForm.password" placeholder="密码" type="password" show-password/>
              </el-col>
              <el-col :span="1"/>
              <el-col :span="10">
                <el-input v-model="registerForm.confirmPassword" placeholder="确认密码" type="password" show-password/>
              </el-col>
              <el-col :span="1"/>
              <el-col :span="1" class="horizontal-center">
                <el-tooltip v-if="registerForm.password===registerForm.confirmPassword" content="两次密码输入一致"
                            placement="top">
                  <img
                      style="width: 20px"
                      :src=circleCheck
                      alt="circleCheck"
                  />
                </el-tooltip>
                <el-tooltip v-else content="两次密码输入不一致" placement="top">
                  <img
                      style="width: 20px"
                      :src=warning
                      alt="warning"
                  />
                </el-tooltip>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.email" placeholder="邮箱"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.phoneNumber" placeholder="手机号码"/>
            </el-form-item>
          </el-form>
          <el-space>
            <el-button
                class="smooth-button"
                size="large"
                text
                bg
                plain
                @click="register()">注册
            </el-button>
            <el-button
                class="smooth-button"
                size="large"
                text
                bg
                plain
                @click="switchToLogin()">返回
            </el-button>
          </el-space>
        </div>
      </el-col>
    </el-row>
  </TemplatePage>
</template>

<style scoped>
.login-container {
  height: 100%;
  margin-top: 10%
}

.login-form {
  width: 60%;
  margin-top: 20px
}
</style>