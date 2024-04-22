<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import fresh from "~/composables/fresh.ts";
import routeTo from "~/route/routeTo.ts";

const props = defineProps<{
  addr: string,
}>()
const chainRights = ref([])

fresh((_) => {
  //获取账户待审合约
  httpService.get(
      Constant.Api.CHAIN.RIGHT.DEPLOYER,
      {
        params: {
          deployer: props.addr
        }
      }
  ).then((data) => {
    chainRights.value = data[Constant.RespondField.RIGHT]
  })
})
</script>

<template>
  <el-table style="width: 100%" :data="chainRights" stripe>
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
    <el-table-column prop="fileName" label="版权资源"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="primary" plain bg text @click="routeTo.chainRight(props.addr,scope.row.index)">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>