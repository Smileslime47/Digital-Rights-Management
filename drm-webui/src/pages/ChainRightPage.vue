<script setup lang="ts">

import TemplatePage from "~/pages/TemplatePage.vue";
import {UploadFilled} from "@element-plus/icons-vue";

const form = reactive({
  title: "",
  registrationNumber: "",
  availableTime: "",
  description: "",
})

const confirmCreate = () => {

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
            >
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
              <div class="el-upload__text">
                拖拽文件至此处或者<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="console.log(form.availableTime)">提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </TemplatePage>
</template>

<style scoped>

</style>