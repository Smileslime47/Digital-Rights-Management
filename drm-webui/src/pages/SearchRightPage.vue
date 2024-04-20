<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import fresh from "~/composables/fresh.ts";
import Constant from "~/constant/Constant.ts";
import {httpService} from "~/server/http.ts";
import Right from "~/modules/Right.ts";
import {Search} from "@element-plus/icons-vue";
import routeTo from "~/route/routeTo.ts";
import TokenUtils from "~/server/TokenUtils.ts";

const initialized = ref(false)
const searchContent = ref("")
const chainRights = ref<Right[]>([])

fresh(async (route) => {
  let caller = await TokenUtils.getChainAddress(useRoute().path)
  searchContent.value = <string>route.params.title
  httpService.get(
      Constant.Api.CHAIN.RIGHT.SEARCH,
      {
        params: {
          title: searchContent.value,
          caller: caller
        }
      }
  ).then((data) => {
    chainRights.value = data[Constant.RespondField.RIGHT]
    initialized.value = true
  })
})
</script>

<template>
  <TemplatePage>
    <div class="vertical-center" v-loading="!initialized">
      <el-input
          class="wide-input"
          style="margin-bottom: 50px;"
          v-model="searchContent"
          placeholder="搜索版权标题">
        <template #append>
          <el-button class="vertical-center" :icon="Search" @click="routeTo.searchRight(searchContent)"/>
        </template>
      </el-input>

      <el-space fill direction="vertical" style="width:80%" v-if="chainRights.length>0">
        <el-card class="notice" v-for="right in chainRights">
          <template #header>
            <el-link class="medium-text" @click="routeTo.chainRight(right.addr)">{{ right.title }}</el-link>
          </template>
          <el-text>{{ right.description !== "" ? right.description : "无描述" }}</el-text>
          <template #footer>
            <span>版权所有人：{{ right.owner }}</span>
            <br/>
            <span>版权登记号：{{ right.registrationNumber }}</span>
            <br/>
            <span>版权有效期：{{
                new Date(right.issueTime).toLocaleDateString()
              }} - {{ new Date(right.expireTime).toLocaleDateString() }}</span>
          </template>
        </el-card>
      </el-space>
      <el-empty v-else :image-size="200"/>
    </div>
  </TemplatePage>
</template>