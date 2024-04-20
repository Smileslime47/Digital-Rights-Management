<script setup lang="ts">
import RightData from "~/modules/RightData.ts";

const props = defineProps<{
  right: RightData,
  caller: string
}>()
</script>

<template>
<el-card>
  <h1>授权列表</h1>
</el-card>
</template>