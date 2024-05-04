<script setup lang="ts">
import RightData from "~/modules/RightData.ts";
import routeTo from "~/route/routeTo.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean,
  loaded:boolean
}>()
</script>

<template>
  <el-card>
    <h1>版权信息</h1>
    <el-alert title="以下内容从区块链中读取，已上链数据将无法更改。" type="success"/>
    <el-divider/>
    <el-descriptions v-if="loaded">
      <el-descriptions-item label="版权名称">{{ right.title }}</el-descriptions-item>
      <el-descriptions-item label="版权所有者">{{ right.owner }}</el-descriptions-item>
      <el-descriptions-item label="版权登记号">{{ right.registrationNumber }}</el-descriptions-item>
      <el-descriptions-item label="有效时间">{{ new Date(right.issueTime).toLocaleDateString() }} --
        {{ new Date(right.expireTime).toLocaleDateString() }}
      </el-descriptions-item>
      <el-descriptions-item label="版权部署人">{{ right.deployer }}</el-descriptions-item>
      <el-descriptions-item label="版权序列号">{{ right.deployer + "[" + right.index + "]"}}</el-descriptions-item>
      <el-descriptions-item label="版权描述">{{ right.description }}</el-descriptions-item>
    </el-descriptions>
    <el-skeleton :row="7" animated v-else/>
    <template #footer>
      <el-button type="primary" plain bg text @click="routeTo.createLicense(right.deployer,right.index)">申请授权</el-button>
    </template>
  </el-card>
</template>