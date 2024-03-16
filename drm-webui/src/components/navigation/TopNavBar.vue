<script setup lang="ts">
import githubIcon from "~/assets/icons/github.svg"
import webIcon from "~/assets/icons/web-icon.svg"
import bugIcon from "~/assets/icons/bug.svg"
import routeTo from "~/route/routeTo.ts";
import fresh from "~/composables/fresh.ts";
import Constant from "~/constant/Constant.ts";
import httpService from "~/server/http.ts";
import User from "~/modules/User.ts";
import {useRoute} from "vue-router";
import * as jose from 'jose'

const goToGithub = () => {
  window.open("https://github.com/Smileslime47/Digital-Rights-Management")
}

const nickname = ref("")
const userId = ref(0)
const logged = ref(false)
const route = useRoute()

const logout = () => {
  localStorage.clear()
  if (route.path === "/home") {
    routeTo.fresh()
  } else {
    routeTo.home()
  }
}

fresh((_) => {
  //是否登陆
  let userLocalId = localStorage.getItem(Constant.Authentication.USER_ID_CLAIM)
  if (userLocalId == null) return

  //判断登陆凭证是否过期
  let userExpireTime = localStorage.getItem(Constant.Authentication.EXPIRE_TIME_CLAIM)
  if (userExpireTime == null) return;
  let expireTime = Number.parseInt(userExpireTime)
  let nowTime = new Date().getTime()
  if (nowTime - expireTime > 0) {
    localStorage.clear()
    return;
  }

  if (new Date(userExpireTime))
    httpService.get(
        Constant.Api.USER_API,
        {
          params: {
            id: userLocalId
          }
        }
    ).then((data) => {
      let user = data[Constant.RespondField.USER] as User
      logged.value = true
      userId.value = user.id
      nickname.value = user.nickname
    })
})
</script>

<template>
  <el-menu
      mode="horizontal"
      :ellipsis=false
  >
    <el-menu-item index="0" @click="routeTo.home()">
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

    <!--Profile/登入按钮-->
    <el-menu-item v-if="logged" index="1" @click="routeTo.profile(userId)">
      <el-dropdown style="width: 100%;height: 100%">
        <div class="vertical-center">
          <el-text>{{ nickname }}</el-text>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="routeTo.profile(userId)">个人资料</el-dropdown-item>
            <el-dropdown-item @click="logout" divided>登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-menu-item>
    <el-menu-item v-else index="1" @click="routeTo.login()">
      <el-text>登入/注册</el-text>
    </el-menu-item>
    <el-divider direction="vertical" border-style="solid"/>

    <!--Github页面-->
    <el-menu-item index="2" @click="goToGithub">
      <img
          style="width: 20px"
          :src=githubIcon
          alt="githubIcon"
      />
    </el-menu-item>

    <!--Debug页面-->
    <el-menu-item index="3" @click="routeTo.debug()">
      <img
          style="width: 20px"
          :src=bugIcon
          alt="bugIcon"
      />
    </el-menu-item>
  </el-menu>
</template>

<style scoped>
.el-menu {
  padding-left: 50px;
  padding-right: 50px;
}

.el-divider {
  height: 100%
}

.flex-grow {
  flex-grow: 1;
}
</style>
