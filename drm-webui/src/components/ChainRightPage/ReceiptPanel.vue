<script setup lang="ts">
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import Receipt, {EmptyReceipt} from "~/modules/Receipt.ts";

const rightDeployer = ref(<string>useRoute().params.deployer)
const rightIndex = ref(<string>useRoute().params.index)

const receipt = ref<Receipt>(EmptyReceipt())

fresh(() => {
  httpService.get(
      Constant.Api.RECEIPT.ROOT,
      {
        params: {
          deployer: rightDeployer.value,
          index: rightIndex.value
        }
      }
  ).then((data) => {
    receipt.value = data[Constant.RespondField.RECEIPT] as Receipt
  })
})

</script>

<template>
  <el-card>
    <h1>部署记录</h1>
    <el-descriptions>
      <el-descriptions-item label="交易状态">{{ receipt.status }}</el-descriptions-item>
      <el-descriptions-item label="交易哈希">{{ receipt.transactionHash }}</el-descriptions-item>
      <el-descriptions-item label="区块块号">{{ receipt.blockNumber }}</el-descriptions-item>
      <el-descriptions-item label="区块哈希">{{ receipt.blockHash }}</el-descriptions-item>
      <el-descriptions-item label="消耗Gas">{{ receipt.gasUsed }}</el-descriptions-item>
      <el-descriptions-item label="创建时间">{{ new Date(receipt.createTime).toLocaleDateString() }}</el-descriptions-item>
    </el-descriptions>

  </el-card>
</template>