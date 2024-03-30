<script setup lang="ts">
import PendingRight from "~/modules/PendingRight.ts";

defineProps<{
  pendingRights: PendingRight[],
}>()
</script>

<template>
  <h1>合约申请记录</h1>
  <el-tabs>
    <el-tab-pane label="版权合约">
      <el-table style="width: 100%" :data="pendingRights">
        <el-table-column prop="title" label="版权标题"/>
        <el-table-column prop="owner" label="版权所有人"/>
        <el-table-column prop="registrationNumber" label="版权登记号"/>
        <el-table-column label="发行时间">
          <template #default="scope">
            {{ new Date(scope.row.issueTime).toLocaleDateString() }}
          </template>
        </el-table-column>
        <el-table-column prop="expireTime" label="到期时间">
          <template #default="scope">
            {{ new Date(scope.row.expireTime).toLocaleDateString() }}
          </template>
        </el-table-column>
        <el-table-column prop="description" label="版权描述"/>
        <el-table-column prop="status" label="合约状态"/>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="授权合约">授权合约</el-tab-pane>
  </el-tabs>
</template>