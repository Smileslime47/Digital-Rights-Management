<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import RightData, {EmptyRight} from "~/modules/RightData.ts";
import TokenUtils from "~/server/TokenUtils.ts";

const right = ref<RightData>(EmptyRight())
const rightAddr = ref("")
const isDeployer = ref(false)

fresh(async (route) => {
  await TokenUtils.getChainAddress(useRoute().path).then((result) => {
    let caller = result
    httpService.get(
        Constant.Api.CHAIN.RIGHT.ROOT,
        {
          params: {
            addr: route.params.right,
            caller: caller
          }
        }
    ).then((data) => {
      right.value = data[Constant.RespondField.RIGHT] as RightData
      isDeployer.value = caller === right.value.deployer
    })
  })
})
</script>

<template>
  <TemplatePage>
    <RightPanel :right="right" :is-deployer="isDeployer"/>

    <el-divider/>

    <IpfsPanel :right="right" :is-deployer="isDeployer"/>

    <el-divider/>

    <LicensePanel :is-deployer="isDeployer"/>
  </TemplatePage>
</template>