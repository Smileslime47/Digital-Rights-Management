<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import fresh from "~/composables/fresh.ts";
import routeTo from "~/route/routeTo.ts";
import TokenUtils from "~/server/TokenUtils.ts";
import RightData from "~/modules/RightData.ts";

const props = defineProps<{
  right: RightData,
  isDeployer: boolean
}>()

const rightAddr = ref(<string>useRoute().params.right)
const callerAddr = ref("")

const chainRights = ref([])

const downloadByRight = (rightAddr:string) => {
  httpService.get(
      Constant.Api.IPFS.BY_RIGHT,
      {
        params:{
          addr: rightAddr
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

fresh((_) => {
  TokenUtils.getChainAddress(useRoute().path).then((data)=>{
    callerAddr.value = data
    //获取账户待审合约
    httpService.get(
        Constant.Api.CHAIN.LICENSE.BY_RIGHT,
        {
          params: {
            addr: rightAddr.value,
            caller: callerAddr.value
          }
        }
    ).then((data) => {
      chainRights.value = data[Constant.RespondField.LICENSE]
    })
  })
})
</script>

<template>
  <el-table style="width: 100%" :data="chainRights" stripe>
    <el-table-column prop="rightTitle" label="版权标题"/>
    <el-table-column prop="owner" label="授权所有人"/>
    <el-table-column prop="deployer" label="授权部署人"/>
    <el-table-column label="授权起始时间">
      <template #default="scope">
        {{ new Date(scope.row.issueTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="expireTime" label="授权到期时间">
      <template #default="scope">
        {{ new Date(scope.row.expireTime).toLocaleDateString() }}
      </template>
    </el-table-column>
    <el-table-column prop="description" label="授权描述"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button type="primary" plain text bg @click="routeTo.chainRight(scope.row.rightAddr)">查看版权</el-button>
          <el-button type="success" plain text bg @click="downloadByRight(rightAddr)">下载资源</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
</template>