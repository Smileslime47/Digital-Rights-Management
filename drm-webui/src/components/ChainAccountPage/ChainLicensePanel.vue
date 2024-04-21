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
      Constant.Api.CHAIN.LICENSE.BY_DEPLOYER,
      {
        params: {
          addr: props.addr,
        }
      }
  ).then((data) => {
    chainRights.value = data[Constant.RespondField.LICENSE]
  })
})
</script>

<template>
  <el-table style="width: 100%" :data="chainRights" stripe>
    <el-table-column prop="rightTitle" label="版权标题"/>
    <el-table-column prop="owner" label="授权所有人"/>
    <el-table-column prop="deployer" label="授权部署人"/>
    <el-table-column label="授权起始时间">
      <template #default="scope">
        {{ new Date(scope.row.issueTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="expireTime" label="授权到期时间">
      <template #default="scope">
        {{ new Date(scope.row.expireTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="description" label="授权描述"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button type="primary" size="mini" @click="routeTo.chainRight(scope.row.rightAddr)">查看版权</el-button>
          <el-button type="primary" size="mini" @click="routeTo.chainRight(scope.row.rightAddr)">下载资源</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
</template>