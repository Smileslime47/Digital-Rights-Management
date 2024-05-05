<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import TokenUtils from "~/server/TokenUtils.ts";
import fresh from "~/composables/fresh.ts";
import {useRoute} from "vue-router";
import Constant from "~/constant/Constant.ts";
import {httpService} from "~/server/http.ts";

const addr = ref("")
const balance = ref(0)
const initialized = ref(false)

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
  }
  initialized.value = true
})
</script>

<template>
  <TemplatePage side="true">
      <AccountPanel :addr="addr" :balance="balance" :loaded="initialized"/>

      <el-divider/>

      <ChainPanel :addr="addr" :loaded="initialized"/>

      <el-divider/>

      <PendingPanel :addr="addr" :loaded="initialized"/>
  </TemplatePage>
</template>