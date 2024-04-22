<script setup lang="ts">
const props = defineProps<{
  addr: string,
}>()
</script>

<template>
  <el-card>
    <h1>申请记录</h1>
    <el-tabs>
      <el-tab-pane label="版权信息">
        <PendingRightOfUserTable :addr="props.addr"/>
      </el-tab-pane>
      <el-tab-pane label="授权信息">
        <PendingLicenseOfUserTable :addr="props.addr" />
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>