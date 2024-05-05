<script setup lang="ts">
import routeTo from "~/route/routeTo.ts";

const props = defineProps<{
  addr: string,
  loaded: boolean,
}>()
</script>

<template>
  <el-card>
    <h1>链上数据</h1>
    <el-tabs v-if="loaded">
      <el-tab-pane label="版权信息">
        <ChainRightPanel :addr="props.addr"/>
      </el-tab-pane>
      <el-tab-pane label="授权信息">
        <ChainLicensePanel :addr="props.addr"/>
      </el-tab-pane>
    </el-tabs>
    <el-skeleton :rows="5" animated v-else/>
  </el-card>
</template>