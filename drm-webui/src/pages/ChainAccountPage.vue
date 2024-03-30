<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import TokenUtils from "~/server/TokenUtils.ts";
import fresh from "~/composables/fresh.ts";
import {useRoute} from "vue-router";
import Constant from "~/constant/Constant.ts";
import {httpService} from "~/server/http.ts";
import pendingRight from "~/modules/PendingRight.ts";

const addr = ref("")
const balance = ref(0)
const initialized = ref(false)

const pendingRights = ref([])

fresh(async (_) => {
  //获取链上地址
  await TokenUtils.getChainAddress(useRoute().path).then((result) => {
    if (result != null) {
      addr.value = result
    }
  })
  if (addr.value != null && addr.value.length > 0) {
    //获取账户余额
    await httpService.get(
        Constant.Api.CHAIN.ACCOUNT.GET_BALANCE,
        {
          params: {
            addr: addr.value
          }
        }
    ).then((data) => {
      balance.value = data[Constant.RespondField.BALANCE]
    })

    //获取账户待审合约
    await httpService.get(
        Constant.Api.CHAIN.RIGHT.ROOT,
        {
          params: {
            addr: addr.value
          }
        }
    ).then((data) => {
      pendingRights.value = data[Constant.RespondField.RIGHT]
    })
  }
  initialized.value = true
})
</script>

<template>
  <TemplatePage side="true" v-loading="!initialized">
    <div v-if="addr!=null&&addr.length>0">
      <AccountPanel :addr="addr" :balance="balance"/>

      <el-divider/>

      <h1>链上合约</h1>

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

      <el-divider/>

      <PendingPanel :pendingRights="pendingRights"/>
    </div>

    <div v-else>
      <NewAccountPanel/>
    </div>
  </TemplatePage>
</template>