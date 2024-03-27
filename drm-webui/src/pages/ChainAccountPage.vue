<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import TokenUtils from "~/server/TokenUtils.ts";
import fresh from "~/composables/fresh.ts";
import {useRoute} from "vue-router";
import Constant from "~/constant/Constant.ts";
import {httpService} from "~/server/http.ts";
import {ElLoading, ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";

const passwordForm = reactive({
  password: "",
  confirmPassword: ""
})

const addr = ref("")
const balance = ref(0)
const chargeValue = ref("")
const initialized = ref(false)

fresh(async (_) => {
  await TokenUtils.getChainAddress(
      useRoute().path
  ).then((result) => {
    if (result != null) {
      addr.value = result
    }
  })
  if (addr.value != null && addr.value.length > 0) {
    httpService.get(
        Constant.Api.CHAIN_API + Constant.Api.CHAIN_ACCOUNT_API + Constant.Api.ACCOUNT_GET_BALANCE,
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

const confirmCreate = () => {
  const loadingInstance = ElLoading.service()
  httpService.post(
      Constant.Api.CHAIN_API + Constant.Api.CHAIN_ACCOUNT_API + Constant.Api.ACCOUNT_NEW_ACCOUNT,
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
const confirmCharge = () => {
  httpService.post(
      Constant.Api.CHAIN_API + Constant.Api.CHAIN_ACCOUNT_API + Constant.Api.ACCOUNT_CHARGE,
      chargeValue.value
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("充值成功!" )
    } else {
      ElMessage.error("充值失败，请联系管理员检查银行账户余额。")
    }
    routeTo.fresh()
  })
}
</script>

<template>
  <TemplatePage side="true" v-loading="!initialized">
    <div v-if="addr!=null&&addr.length>0">
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
  </TemplatePage>
</template>

<style scoped>
.el-input__wrapper {
  border-radius: 0 !important;
}
</style>