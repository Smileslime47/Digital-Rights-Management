<script setup lang="ts">
const props = defineProps<{
  isDeployer: boolean
}>()
</script>
<template>
  <el-card>
    <h1>授权列表</h1>

    <h2>链上版权</h2>
    <PendingLicenseOfRightTable/>

    <el-divider/>

    <h2>待审核授权</h2>
    <PendingLicenseOfRightTable :is-deployer="props.isDeployer"/>
  </el-card>
</template>