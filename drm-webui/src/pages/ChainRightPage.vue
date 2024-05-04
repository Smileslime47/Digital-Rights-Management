<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import RightData, {EmptyRight} from "~/modules/RightData.ts";
import TokenUtils from "~/server/TokenUtils.ts";

const right = ref<RightData>(EmptyRight())
const isDeployer = ref(false)
const loaded = ref(false)

fresh(async (route) => {
  await TokenUtils.getChainAddress(useRoute().path).then((result) => {
    let caller = result
    httpService.get(
        Constant.Api.CHAIN.RIGHT.ROOT,
        {
          params: {
            deployer: route.params.deployer,
            index: route.params.index
          }
        }
    ).then((data) => {
      right.value = data[Constant.RespondField.RIGHT] as RightData
      isDeployer.value = caller === right.value.deployer
      loaded.value = true
    })
  })
})
</script>

<template>
  <TemplatePage>
    <RightPanel :right="right" :is-deployer="isDeployer" :loaded="loaded"/>

    <el-divider/>

    <ReceiptPanel :loaded="loaded"/>

    <el-divider/>

    <IpfsPanel :right="right" :is-deployer="isDeployer" :loaded="loaded"/>

    <el-divider/>

    <LicensePanel :right="right" :is-deployer="isDeployer" :loaded="loaded"/>
  </TemplatePage>
</template>