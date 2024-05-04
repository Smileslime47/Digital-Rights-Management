<script setup lang="ts">
import circleCheck from "~/assets/icons/CircleCheck.svg";
import warning from "~/assets/icons/warning.svg";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";

const registerFlag = defineModel({type: Boolean})

const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  email: '',
  phoneNumber: '',
})

const switchToLogin = () => {
  registerFlag.value = false
}

const register = () => {
  if (registerForm.password !== registerForm.confirmPassword) {
    ElMessage.warning("两次密码输入不一致，请先确认您的密码")
    return
  }
  httpService.post(
      Constant.Api.USER.REGISTER,
      registerForm
  ).then((principleMap) => {
    localStorage.setItem(Constant.Authentication.TOKEN_STORAGE, principleMap[Constant.Authentication.TOKEN_STORAGE])
    localStorage.setItem(Constant.Authentication.USER_ID_CLAIM, principleMap[Constant.Authentication.USER_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.GROUP_ID_CLAIM, principleMap[Constant.Authentication.GROUP_ID_CLAIM])
    localStorage.setItem(Constant.Authentication.USER_NICKNAME_CLAIM, principleMap[Constant.Authentication.USER_NICKNAME_CLAIM])
    localStorage.setItem(Constant.Authentication.EXPIRE_TIME_CLAIM, principleMap[Constant.Authentication.EXPIRE_TIME_CLAIM])
    routeTo.home()
  })
}
</script>

<template>
  <div class="vertical-center">
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
</template>

<style scoped>
.login-form {
  width: 60%;
  margin-top: 20px
}
</style>