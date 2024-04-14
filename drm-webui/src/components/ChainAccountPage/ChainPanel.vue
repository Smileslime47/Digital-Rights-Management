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
      Constant.Api.CHAIN.RIGHT.OWNER,
      {
        params: {
          addr: props.addr,
        }
      }
  ).then((data) => {
    chainRights.value = data[Constant.RespondField.RIGHT]
  })
})
</script>

<template>
  <h1>链上合约</h1>
  <el-tabs>
    <el-tab-pane label="版权合约">
      <el-table style="width: 100%" :data="chainRights" stripe>
        <el-table-column prop="title" label="版权标题"/>
        <el-table-column prop="owner" label="版权所有人"/>
        <el-table-column prop="deployer" label="版权部署人"/>
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
            <el-button type="primary" size="mini" @click="routeTo.chainRight(scope.row.addr)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="授权合约">授权合约</el-tab-pane>
  </el-tabs>
</template>