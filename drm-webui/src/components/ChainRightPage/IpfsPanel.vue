<script setup lang="ts">
import RightData from "~/modules/RightData.ts";
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean
}>()

const route = useRoute()

const downloadByRight = () => {
  httpService.get(
      Constant.Api.IPFS.BY_RIGHT,
      {
        params:{
          deployer: route.params.deployer,
          index: route.params.index
        },
        responseType: 'blob'
      }
  ).then((data) => {
    let a = document.createElement('a')
    a.href = window.URL.createObjectURL(new Blob([data]))
    a.download = props.right.fileName
    a.click() //执行下载
    window.URL.revokeObjectURL(a.href)
  })
}
</script>

<template>
  <el-card>
    <h1>数字资源</h1>
    <el-descriptions>
      <el-descriptions-item label="版权资源">{{right.fileName}}</el-descriptions-item>
      <el-descriptions-item label="版权资源Hash地址">{{right.fileHash}}</el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <el-button v-if="isDeployer" type="primary" plain bg text @click="downloadByRight(right.addr)">下载资源</el-button>
    </template>
  </el-card>
</template>