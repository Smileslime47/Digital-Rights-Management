<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import Right, {EmptyRight} from "~/modules/Right.ts";

const right = ref<Right>(EmptyRight())

fresh((route) => {
  httpService.get(
      Constant.Api.CHAIN.RIGHT.ROOT,
      {
        params: {
          addr: route.params.addr
        }
      }
  ).then((data) => {
    right.value = data[Constant.RespondField.RIGHT] as Right
  })
})
</script>

<template>
  <TemplatePage>
    <h1>版权信息</h1>
    <el-descriptions>
      <el-descriptions-item label="版权名称">{{ right.title }}</el-descriptions-item>
      <el-descriptions-item label="版权所有者">{{right.owner}}</el-descriptions-item>
      <el-descriptions-item label="版权登记号">{{right.registrationNumber}}</el-descriptions-item>
      <el-descriptions-item label="有效时间">{{new Date(right.issueTime).toLocaleDateString()}} -- {{new Date(right.expireTime).toLocaleDateString()}}</el-descriptions-item>
      <el-descriptions-item label="版权描述">{{right.description}}</el-descriptions-item>
      <el-descriptions-item label="版权资源">{{right.fileName}}</el-descriptions-item>
      <el-descriptions-item label="版权部署人">{{ right.deployer }}</el-descriptions-item>
      <el-descriptions-item label="版权资源Hash地址">{{right.fileHash}}</el-descriptions-item>
      <el-descriptions-item label="版权区块链地址">{{right.addr}}</el-descriptions-item>
    </el-descriptions>
  </TemplatePage>
</template>