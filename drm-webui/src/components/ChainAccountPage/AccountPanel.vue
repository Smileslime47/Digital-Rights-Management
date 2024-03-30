<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";

defineProps<{
  addr: String|null,
  balance:number
}>()

const chargeValue = ref("")

const confirmCharge = () => {
  httpService.post(
      Constant.Api.CHAIN.ACCOUNT.CHARGE,
      chargeValue.value
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("充值成功!")
    } else {
      ElMessage.error("充值失败，请联系管理员检查银行账户余额。")
    }
    routeTo.fresh()
  })
}
</script>

<template>
    <h1>链上账户管理</h1>
    <el-descriptions>
      <el-descriptions-item label="区块链地址">{{ addr }}</el-descriptions-item>
      <el-descriptions-item label="账户余额">{{ balance }} Wei</el-descriptions-item>
      <el-descriptions-item label="充值">
        <el-space>
          <el-input v-model="chargeValue" style="width: 240px" placeholder="Please input">
            <template #append>Wei</template>
          </el-input>
          <el-button text bg type="primary" @click="confirmCharge">确认</el-button>
        </el-space>
      </el-descriptions-item>
    </el-descriptions>
</template>

<style scoped>

</style>