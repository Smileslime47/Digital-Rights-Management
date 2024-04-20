<script setup lang="ts">
import RightData from "~/modules/RightData.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean
}>()
</script>

<template>
  <el-card>
    <h1>数字资源</h1>
    <el-descriptions>
      <el-descriptions-item label="版权资源">{{right.fileName}}</el-descriptions-item>
      <el-descriptions-item label="版权资源Hash地址">{{right.fileHash}}</el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>