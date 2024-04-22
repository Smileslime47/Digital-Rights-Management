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

const rightDeployer = ref(<string>useRoute().params.deployer)
const rightIndex = ref(<string>useRoute().params.index)

const callerAddr = ref("")

const chainLicenses = ref([])

const downloadByLicense = (licenseDeployer:string,licenseIndex:number) => {
  httpService.get(
      Constant.Api.IPFS.BY_LICENSE,
      {
        params: {
          deployer: licenseDeployer,
          index: licenseIndex
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
  TokenUtils.getChainAddress(useRoute().path).then((data) => {
    callerAddr.value = <string>data
    //获取账户待审合约
    httpService.get(
        Constant.Api.CHAIN.LICENSE.BY_RIGHT,
        {
          params: {
            deployer: rightDeployer.value,
            index: rightIndex.value
          }
        }
    ).then((data) => {
      chainLicenses.value = data[Constant.RespondField.LICENSE]
    })
  })
})
</script>

<template>
  <el-table style="width: 100%" :data="chainLicenses" stripe>
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
          <el-button type="primary" plain text bg @click="routeTo.chainRight(scope.row.rightKeyPair.deployer,scope.row.rightKeyPair.arrayIndex)">查看版权</el-button>
          <el-button type="success" plain text bg @click="downloadByLicense(scope.row.rightKeyPair.deployer,scope.row.rightKeyPair.arrayIndex)">下载资源</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
</template>