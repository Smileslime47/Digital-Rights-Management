<script setup lang="ts">

</script>

<template>
  <h1>待审核合约</h1>
  <el-tabs>
    <el-tab-pane label="版权合约">
      <el-table style="width: 100%">
        <el-table-column prop="date" label="版权标题"/>
        <el-table-column prop="name" label="版权所有人"/>
        <el-table-column prop="name" label="版权登记号"/>
        <el-table-column prop="address" label="发行时间"/>
        <el-table-column prop="name" label="到期时间"/>
        <el-table-column prop="name" label="版权描述"/>
        <el-table-column prop="name" label="合约状态"/>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="授权合约">授权合约</el-tab-pane>
  </el-tabs>
</template>

<style scoped>

</style>