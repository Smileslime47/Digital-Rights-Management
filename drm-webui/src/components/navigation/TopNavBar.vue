<script setup lang="ts">
import githubIcon from "~/assets/icons/github.svg"
import webIcon from "~/assets/icons/web-icon.svg"
import SwaggerIcon from "~/assets/icons/swagger.svg"
import routeTo from "~/route/routeTo.ts";
import fresh from "~/composables/fresh.ts";
import {useRoute} from "vue-router";
import {Search} from '@element-plus/icons-vue'
import TokenUtils from "~/server/TokenUtils.ts";
import Constant from "~/constant/Constant.ts";

const goToGithub = () => {
  window.open("https://github.com/Smileslime47/Digital-Rights-Management")
}

const goToSwagger = () => {
  window.open(Constant.Property.BASE_URL + "/openapi")
}

const nickname = ref("")
const userId = ref(0)
const logged = ref(false)
const noticeCnt = ref(0)

const searchContent = ref("")

const logout = () => {
  localStorage.clear()
  routeTo.fresh()
}

fresh(async (_) => {
  let token = TokenUtils.getToken()
  if (token != null) {
    nickname.value = token.user_nickname
    userId.value = Number(token.user_id)
    logged.value = true

    TokenUtils.getNoticeCnt(
        useRoute().path
    ).then((result) => {
      if (result != null) {
        noticeCnt.value = result
      }
    })
  }
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
    <el-menu-item index="1">
      <el-input
          v-model="searchContent"
          placeholder="搜索版权标题">
        <template #append>
          <el-button :icon="Search" @click="routeTo.searchRight(searchContent)"/>
        </template>
      </el-input>
    </el-menu-item>

    <div class="flex-grow"/>

    <!--Profile/登入按钮-->
    <el-sub-menu v-if="logged" index="2" @click="routeTo.profile(userId)">
      <template #title>
        <el-text>{{ nickname }}</el-text>
      </template>
      <el-menu-item @click="routeTo.profile(userId)">
        个人资料
      </el-menu-item>
      <el-menu-item @click="routeTo.notice(Constant.NoticeFilter.ALL,1)">
        <el-badge v-if="logged" :is-dot="noticeCnt>0">
          通知
        </el-badge>
      </el-menu-item>
      <el-menu-item @click="logout" divided>登出</el-menu-item>
    </el-sub-menu>
    <el-menu-item v-else index="2" @click="routeTo.login()">
      <el-text>登入/注册</el-text>
    </el-menu-item>

    <el-divider direction="vertical" border-style="solid"/>

    <!--Github页面-->
    <el-menu-item index="3" @click="goToGithub">
      <img
          style="width: 20px"
          :src=githubIcon
          alt="githubIcon"
      />
    </el-menu-item>
    <el-menu-item index="3" @click="goToSwagger">
      <img
          style="width: 20px"
          :src=SwaggerIcon
          alt="SwaggerIcon"
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
