<script setup lang="ts">
import RightData, {EmptyRight} from "~/modules/RightData.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import TokenUtils from "~/server/TokenUtils.ts";
import PendingLicense from "~/modules/PendingLicense.ts";
import {ElMessage} from "element-plus";
import routeTo from "~/route/routeTo.ts";

const form = reactive({
  owner: "",
  availableTime: [0, 0],
  description: ""
})

const deployer = ref("")
const right = ref<RightData>(EmptyRight())

fresh(async (route) => {
  //获取链上账户
  TokenUtils.getChainAddress(useRoute().path).then((result) => {
    if (result != null) {
      deployer.value = result
      httpService.get(
          Constant.Api.CHAIN.RIGHT.ROOT,
          {
            params: {
              addr: route.params.right,
              caller: deployer.value
            }
          }
      ).then((data) => {
        right.value = data[Constant.RespondField.RIGHT] as RightData
      })
    } else {
      ElMessage.error("Need Chain Account.")
      routeTo.chainAccount()
    }
  })
})

const confirmCreate = () => {
  //生成合约部署表单
  let pendingLicense = new PendingLicense(
      right.value.title,
      right.value.addr,
      deployer.value,
      form.owner,
      form.availableTime[0],
      form.availableTime[1],
      form.description,
  )

  //发送合约部署审核请求
  httpService.post(
      Constant.Api.PENDING_LICENSE.ROOT,
      pendingLicense
  ).then((data) => {
    let success = data[Constant.RespondField.SUCCESS]
    if (success) {
      ElMessage.success("提交成功！")
    } else {
      ElMessage.error("提交失败！")
    }
  })
}

</script>

<template>
  <TemplatePage :side="true">
    <el-card>
      <h1>申请授权</h1>
      <el-row>
        <el-col :span="12">
          <el-form :model="form" label-width="80px">
            <el-form-item label="版权名称">
              <el-input disabled v-model="right.title"></el-input>
            </el-form-item>
            <el-form-item label="所有人">
              <el-input disabled v-model="right.owner"></el-input>
            </el-form-item>
            <el-form-item label="登记号">
              <el-input disabled v-model="right.registrationNumber"></el-input>
            </el-form-item>
            <el-form-item label="申请人">
              <el-input v-model="form.owner"></el-input>
            </el-form-item>
            <el-form-item label="授权时间">
              <el-date-picker
                  v-model="form.availableTime"
                  range-separator="至"
                  start-placeholder="起始日期"
                  end-placeholder="终止日期"
                  type="daterange"
                  value-format="x"
              />
            </el-form-item>
            <el-form-item label="授权描述">
              <el-input autosize placeholder="如授权范围等附加信息" type="textarea"
                        v-model="form.description"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button plain text bg type="primary" @click="confirmCreate">提交</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
  </TemplatePage>
</template>