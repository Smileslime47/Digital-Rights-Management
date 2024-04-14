<script setup lang="ts">
import {httpService} from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {ElMessage} from "element-plus";

const props = defineProps<{
  addr: string,
}>()
const pendingRights = ref([])
const maxPage = ref(0)
const pageNow = ref("1")

const chainPassword = ref("")

const getPendingRights = (addr: String, page: String) => {
  //获取账户待审合约
  httpService.get(
      Constant.Api.PENDING_RIGHT.ROOT,
      {
        params: {
          addr: addr,
          page: page
        }
      }
  ).then((data) => {
    pendingRights.value = data[Constant.RespondField.RIGHT]
    maxPage.value = Math.ceil(data[Constant.RespondField.COUNT] / Constant.Global.DEFAULT_PAGE_SIZE)
  })
}

const deploy = (id: number) => {
  httpService.post(
      Constant.Api.PENDING_RIGHT.DEPLOY,
      {
        password: chainPassword.value,
        pendingId: id
      },
  ).then((_) => {
    ElMessage.success("部署成功！")
  })
}

watch(pageNow, (newVal) => {
  getPendingRights(props.addr, newVal)
})

onMounted(() => {
  getPendingRights(props.addr, pageNow.value)
})
</script>

<template>
  <h1>合约申请记录</h1>
  <el-tabs>
    <el-tab-pane label="版权合约">
      <el-table style="width: 100%" :data="pendingRights" stripe>
        <el-table-column type="expand">
          <template #default="props">
            <el-space direction="vertical" style="width:100%" fill>
              <el-text>{{ props.row.comment }}</el-text>
              <el-row v-if="props.row.status === Constant.PendingStatus.CONFIRMED">
                <el-col :span="16">
                  <el-input v-model="chainPassword" placeholder="区块链账户密码"/>
                </el-col>
                <el-col :offset="1" :span="4">
                  <el-text>预估部署价格：</el-text>
                  <el-text>{{ props.row.estimatePrice / 1e18 }} ETH</el-text>
                </el-col>
                <el-col :offset="1" :span="2">
                  <el-button type="primary" text bg class="smooth-button" @click="deploy(props.row.id)">确认部署
                  </el-button>
                </el-col>
              </el-row>
            </el-space>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="版权标题"/>
        <el-table-column prop="owner" label="版权所有人"/>
        <el-table-column prop="registrationNumber" label="版权登记号"/>
        <el-table-column label="发行时间">
          <template #default="scope">
            {{ new Date(scope.row.issueTime).toLocaleDateString() }}
          </template>
        </el-table-column>
        <el-table-column prop="expireTime" label="到期时间">
          <template #default="scope">
            {{ new Date(scope.row.expireTime).toLocaleDateString() }}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="申请时间">
          <template #default="scope">
            {{ new Date(scope.row.createTime).toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column prop="description" label="版权描述"/>
        <el-table-column prop="status" label="合约状态"/>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-count="maxPage" v-model:current-page="pageNow"/>
    </el-tab-pane>
    <el-tab-pane label="授权合约">授权合约</el-tab-pane>
  </el-tabs>
</template>