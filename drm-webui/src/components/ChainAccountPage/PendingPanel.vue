<script setup lang="ts">
const props = defineProps<{
  addr: string,
  loaded: boolean,
}>()
</script>

<template>
  <el-card>
    <h1>申请记录</h1>
    <el-tabs v-if="loaded">
      <el-tab-pane label="版权信息">
        <PendingRightOfUserTable :addr="props.addr"/>
      </el-tab-pane>
      <el-tab-pane label="授权信息">
        <PendingLicenseOfUserTable :addr="props.addr"/>
      </el-tab-pane>
    </el-tabs>
    <el-skeleton :rows="5" animated v-else/>
  </el-card>
</template>