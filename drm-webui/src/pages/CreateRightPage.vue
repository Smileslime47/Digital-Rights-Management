<script setup lang="ts">

import TemplatePage from "~/pages/TemplatePage.vue";
import {UploadFilled} from "@element-plus/icons-vue";
import TokenUtils from "~/server/TokenUtils.ts";
import fresh from "~/composables/fresh.ts";
import {ElMessage, UploadInstance} from "element-plus";
import routeTo from "~/route/routeTo.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import IpfsResponseBody from "~/modules/IpfsResponseBody.ts";
import PendingRight from "~/modules/PendingRight.ts";

const form = reactive({
  title: "",
  owner: "",
  registrationNumber: "",
  availableTime: [0, 0],
  description: ""
})

const addr = ref("")

const uploadUrl = Constant.Property.BASE_URL + Constant.Api.IPFS.ROOT
const uploadRef = ref<UploadInstance>()


fresh((_) => {
  //获取链上账户
  TokenUtils.getChainAddress(useRoute().path).then((result) => {
    if (result != null) {
      addr.value = result
    } else {
      ElMessage.error("Need Chain Account.")
      routeTo.chainAccount()
    }
  })
})


const confirmCreate = () => {
  uploadRef.value!.submit()
}

const onUploadSuccess = (response) => {
  //提取Ipfs文件信息
  let ipfsResponseBody = response.data as IpfsResponseBody

  //生成合约部署表单
  let pendingRight = new PendingRight(
      0,
      form.title,
      form.owner,
      form.registrationNumber,
      form.availableTime[0],
      form.availableTime[1],
      form.description,
      ipfsResponseBody.name,
      ipfsResponseBody.hash,
      "PENDING",
  )

  //发送合约部署审核请求
  httpService.post(
      Constant.Api.PENDING_RIGHT.ROOT,
      pendingRight
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
  <TemplatePage side="true">
    <h1>版权资源上传</h1>
    <el-row>
      <el-col :span="12">
        <el-form :model="form" label-width="80px">
          <el-form-item label="版权名称">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="所有人">
            <el-input v-model="form.owner"></el-input>
          </el-form-item>
          <el-form-item label="登记号">
            <el-input v-model="form.registrationNumber"></el-input>
          </el-form-item>
          <el-form-item label="有效时间">
            <el-date-picker
                v-model="form.availableTime"
                range-separator="至"
                start-placeholder="发行日期"
                end-placeholder="到期日期"
                type="daterange"
                value-format="x"
            />
          </el-form-item>
          <el-form-item label="版权描述">
            <el-input autosize type="textarea" v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item label="版权资源">
            <el-upload
                style="width:100%"
                drag
                multiple
                :auto-upload="false"
                ref="uploadRef"
                :action=uploadUrl
                :on-success=onUploadSuccess
            >
              <el-icon class="el-icon--upload">
                <upload-filled/>
              </el-icon>
              <div class="el-upload__text">
                拖拽文件至此处或者<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirmCreate">提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </TemplatePage>
</template>