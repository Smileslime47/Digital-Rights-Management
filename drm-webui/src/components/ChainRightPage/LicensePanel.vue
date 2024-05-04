<script setup lang="ts">
import RightData from "~/modules/RightData.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean,
  loaded: boolean
}>()

</script>
<template>
  <el-card>
    <h1>授权列表</h1>

    <h2>链上版权</h2>
    <ChainLicenseOfRightTable :right="props.right" :is-deployer="props.isDeployer" v-if="loaded"/>
    <el-skeleton :row="1" animated v-else/>

    <el-divider/>

    <h2>待审核授权</h2>
    <PendingLicenseOfRightTable :right="props.right" :is-deployer="props.isDeployer" v-if="loaded"/>
    <el-skeleton :row="1" animated v-else/>
  </el-card>
</template>