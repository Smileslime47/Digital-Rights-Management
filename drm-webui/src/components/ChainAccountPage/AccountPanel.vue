<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElLoading, ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";
import TemplatePage from "~/pages/TemplatePage.vue";

defineProps<{
  addr: String | null,
  balance: number,
  loaded: boolean,
}>()

const chargeValue = ref("")

const passwordForm = reactive({
  password: "",
  confirmPassword: ""
})

const confirmCharge = () => {
  httpService.post(
      Constant.Api.CHAIN.ACCOUNT.CHARGE,
      {value: chargeValue.value}
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("充值成功!")
      routeTo.fresh()
    } else {
      ElMessage.error("充值失败，请联系管理员检查银行账户余额。")
    }
  })
}

const confirmCreate = () => {
  const loadingInstance = ElLoading.service()
  httpService.post(
      Constant.Api.CHAIN.ACCOUNT.NEW_ACCOUNT,
      passwordForm
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("创建成功:" + data[Constant.RespondField.ADDRESS])
    } else {
      ElMessage.error("创建失败！")
    }
    loadingInstance.close()
    routeTo.fresh()
  })
}
</script>

<template>
  <el-card>
    <div v-if="loaded">
      <div v-if="addr!=null&&addr.length>0">
        <h1>链上账户管理</h1>
        <el-descriptions>
          <el-descriptions-item label="区块链地址">{{ addr }}</el-descriptions-item>
          <el-descriptions-item label="账户余额">{{ balance }} ETH</el-descriptions-item>
          <el-descriptions-item label="充值">
            <el-space>
              <el-input v-model="chargeValue" style="width: 240px" placeholder="Please input">
                <template #append>ETH</template>
              </el-input>
              <el-button text bg type="primary" @click="confirmCharge">确认</el-button>
            </el-space>
          </el-descriptions-item>
        </el-descriptions>
      </div>

      <div v-else>
        <h1>创建新的链上账户</h1>
        <el-form label-width="auto" :model="passwordForm">
          <el-form-item label="密码">
            <el-input show-password v-model="passwordForm.password"/>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input show-password v-model="passwordForm.confirmPassword"/>
          </el-form-item>
          <el-form-item>
            <el-button text bg type="primary" @click="confirmCreate">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-skeleton :rows="5" animated v-else/>
  </el-card>
</template>