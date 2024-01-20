<script setup lang="ts">
import "~/assets/fonts/font.css"
import routeTo from "~/route/routeTo.ts";

const myAvatarUrl = ref("https://avatars.githubusercontent.com/u/77948910?v=4")
const placeholderAvatar = ref("https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png")
</script>

<template>
  <div class="vertical-center">
    <div class="hero-title">
        <span class="hero-text text-blue">
          基于区块链的
        </span>
      <br/>
      <span class="hero-text text-black" style="margin-top: 20px">
          数字版权解决方案
      </span>
    </div>
    <div>
      <el-button
          size="large"
          text
          bg
          plain
          @click="routeTo.login()">登入/注册-></el-button>
    </div>
  </div>

  <el-divider/>
  <div style="text-align: center">
    <div class="horizontal-center">
      <el-card class="divider">
        <template #header>
          <span class="medium-text text-blue">基于区块链</span>
        </template>
        <span class="medium-text text-black">数字版权信息存放于我们的私有链中，保证不可篡改性</span>
      </el-card>
      <el-card class="divider">
        <template #header>
          <span class="medium-text text-blue">DRM加密预览</span>
        </template>
        <span class="medium-text text-black">分发的资源经过DRM加密，需要上传到服务器经过解密后推流至浏览器</span>
      </el-card>
      <el-card class="divider">
        <template #header>
          <span class="medium-text text-blue">高效可靠</span>
        </template>
        <span class="medium-text text-black">人工审核的版权信息，防止资源造成的侵权问题</span>
      </el-card>
    </div>
  </div>

  <el-divider/>

  <div style="text-align: center">
    <span class="medium-text">Powered By:</span>
    <div style="height:20px"/>
    <div class="horizontal-center">
      <el-card class="divider" :body-style="{ padding: '0px' }">
        <el-image
            :src=myAvatarUrl
            style="width: 200px"
        />
        <div class="author-tittle">
          <span>Smile_slime_47</span>
          <div class="bottom">
            <el-button text class="button">我本人</el-button>
          </div>
        </div>
      </el-card>
      <el-card class="divider" :body-style="{ padding: '0px' }">
        <el-image
            :src=placeholderAvatar
            style="width: 200px"
        />
        <div class="author-tittle">
          <span>官全龙</span>
          <div class="bottom">
            <el-button text class="button">指导老师</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.divider {
  margin: 0 10px
}

.hero-title {
  text-align: center;
  padding-top:96px;
  padding-bottom:48px;
}

.author-tittle {
  padding:14px;
}

.el-space {
  width: 60%
}

.el-card {
  max-width: 400px;
}

.el-button {
  width: 150px;
  font-size: 20px
}
</style>