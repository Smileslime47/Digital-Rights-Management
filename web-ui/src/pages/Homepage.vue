<script setup lang="ts">
import "~/assets/fonts/font.css"
const myAvatarUrl=ref("https://avatars.githubusercontent.com/u/77948910?v=4")
const placeholderAvatar=ref("https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png")
</script>

<template>
  <div class="hero">
      <span class="tagline text-blue">
        基于区块链的
      </span>
      <br/>
      <span class="tagline text-black" style="margin-top: 20px">
        数字版权解决方案
      </span>
      <br/>
      <el-button-group style="margin-top:20px">
        <el-button size="large" type="primary" plain>登入</el-button>
        <el-button size="large" plain>注册</el-button>
      </el-button-group>
  </div>

  <el-divider />

  <div class="card-container">
      <el-card class="divider">
        <template #header>
          <span class="card-info text-blue">基于区块链</span>
        </template>
        <span class="card-info text-black">数字版权信息存放于我们的私有链中，保证不可篡改性</span>
      </el-card>
      <el-card class="divider">
        <template #header>
          <span class="card-info text-blue">DRM加密预览</span>
        </template>
        <span class="card-info text-black">分发的资源经过DRM加密，需要上传到服务器经过解密后推流至浏览器</span>
      </el-card>
      <el-card class="divider">
        <template #header>
          <span class="card-info text-blue">高效可靠</span>
        </template>
        <span class="card-info text-black">人工审核的版权信息，防止资源造成的侵权问题</span>
      </el-card>
  </div>

  <el-divider />

  <div style="text-align: center">
    <span style="font-size: 20px">Powered By:</span>
    <div class="card-container">
        <el-card class="divider" :body-style="{ padding: '0px' }" >
          <el-image
              :src=myAvatarUrl
              style="width: 200px"
          />
          <div style="padding: 14px">
            <span>Smile_slime_47</span>
            <div class="bottom">
              <el-button text class="button">我本人</el-button>
            </div>
          </div>
        </el-card>
        <el-card class="divider" :body-style="{ padding: '0px' }" >
          <el-image
              :src=placeholderAvatar
              style="width: 200px"
          />
          <div style="padding: 14px">
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
.divider{
  margin:0 10px
}
.text-blue{
  color:#409EFF
}
.text-black{
  color:#323232
}
.card-container{
  margin:20px;
  display: flex;
  justify-content: center;
}
.hero{
  width: 100%;
  text-align:center;
  padding:96px 0;
}
.tagline{
  font-size:76px;
}
.card-info{
  font-size:20px
}

.el-space{
  width:60%
}
.el-card{
  max-width:400px;
}
.el-button{
  width:150px;
  font-size:20px
}
</style>