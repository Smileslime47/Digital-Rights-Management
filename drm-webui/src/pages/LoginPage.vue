<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";

const registerFlag = ref(false)

</script>

<template>
  <TemplatePage>
    <el-row class="login-container">
      <el-col :span="12">
        <HeroPanel/>
      </el-col>
      <el-col :span="12">
        <LoginPanel v-if="!registerFlag" v-model="registerFlag"/>
        <RegisterPanel v-else v-model="registerFlag"/>
      </el-col>
    </el-row>
  </TemplatePage>
</template>

<style scoped>
.login-container {
  height: 100%;
  margin-top: 10%
}
</style>