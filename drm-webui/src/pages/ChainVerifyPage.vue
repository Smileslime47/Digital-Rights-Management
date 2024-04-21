<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
</script>

<template>
  <TemplatePage :side="true">
    <el-tabs>
      <el-tab-pane label="版权合约">
        <VerifyPendingRightTable/>
      </el-tab-pane>
    </el-tabs>
  </TemplatePage>
</template>
