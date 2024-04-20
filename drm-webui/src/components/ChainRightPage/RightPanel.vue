<script setup lang="ts">
import RightData from "~/modules/RightData.ts";
import routeTo from "~/route/routeTo.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean
}>()
</script>

<template>
<el-card>
  <h1>版权信息</h1>
  <el-descriptions>
    <el-descriptions-item label="版权名称">{{ right.title }}</el-descriptions-item>
    <el-descriptions-item label="版权所有者">{{right.owner}}</el-descriptions-item>
    <el-descriptions-item label="版权登记号">{{right.registrationNumber}}</el-descriptions-item>
    <el-descriptions-item label="有效时间">{{new Date(right.issueTime).toLocaleDateString()}} -- {{new Date(right.expireTime).toLocaleDateString()}}</el-descriptions-item>
    <el-descriptions-item label="版权描述">{{right.description}}</el-descriptions-item>
    <el-descriptions-item label="版权部署人">{{ right.deployer }}</el-descriptions-item>
    <el-descriptions-item label="版权区块链地址">{{right.addr}}</el-descriptions-item>
  </el-descriptions>
  <template #footer v-if="!isDeployer">
    <el-button type="primary" plain bg text @click="routeTo.createLicense(right.addr)">申请授权</el-button>
  </template>
</el-card>
</template>