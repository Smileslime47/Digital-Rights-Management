<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";

const props = defineProps<{
  addr: string,
}>()
const chainRights = ref([])

const getPendingRights = (addr: String) => {
  //获取账户待审合约
  httpService.get(
      Constant.Api.CHAIN.RIGHT.ROOT,
      {
        params: {
          addr: addr,
        }
      }
  ).then((data) => {
    chainRights.value = data[Constant.RespondField.RIGHT]
  })
}

onMounted(() => {
  getPendingRights(props.addr)
})
</script>

<template>
  <h1>链上合约</h1>
  <el-tabs>
    <el-tab-pane label="版权合约">
      <el-table style="width: 100%" :data="chainRights" stripe>
        <el-table-column prop="addr" label="地址"/>
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
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="授权合约">授权合约</el-tab-pane>
  </el-tabs>
</template>