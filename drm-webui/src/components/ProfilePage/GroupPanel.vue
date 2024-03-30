<script setup lang="ts">
import Group from "~/modules/Group.ts";

defineProps<{
  group: Group
  selfProfile: boolean
}>()
</script>

<template>
  <h1>用户组</h1>
  <el-descriptions column="1">
    <el-descriptions-item label="用户组">
      <el-tag size="small">{{ group.groupName }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item v-if="selfProfile">
      <el-descriptions :border=true title="权限">
        <el-descriptions-item label="允许登陆">{{ group.permissionLogin }}</el-descriptions-item>
        <el-descriptions-item label="允许展示资料">{{ group.permissionShowProfile }}</el-descriptions-item>
        <el-descriptions-item label="允许上传版权资源">{{ group.permissionCreateRight }}</el-descriptions-item>
        <el-descriptions-item label="允许获取版权授权">{{ group.permissionCreateLicense }}</el-descriptions-item>
        <el-descriptions-item label="允许创建链上账户">{{ group.permissionCreateChainAccount }}</el-descriptions-item>
        <el-descriptions-item label="审核版权">{{ group.permissionVerifyRight }}</el-descriptions-item>
      </el-descriptions>
    </el-descriptions-item>
  </el-descriptions>
</template>