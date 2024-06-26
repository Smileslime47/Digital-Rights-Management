<script setup lang="ts">
import TemplatePage from "~/pages/TemplatePage.vue";
import Constant from "~/constant/Constant.ts";
import routeTo from "~/route/routeTo.ts";
import fresh from "~/composables/fresh.ts";
import {httpService} from "~/server/http.ts";
import {ElMessage} from "element-plus";

const notices = ref([])
const filter = ref("")
const maxPage = ref(0)
const pageNow = ref("")

const initialized = ref(false)

watch(pageNow, (newVal) => {
  routeTo.notice(filter.value, newVal)
})

fresh(async (route) => {
  filter.value = <string>route.params.filter
  pageNow.value = <string>route.params.page

  httpService.get(
      Constant.Api.NOTICE.ROOT,
      {
        params: {
          filter: filter.value,
          page: pageNow.value
        }
      }
  ).then((data) => {
    notices.value = data[Constant.RespondField.NOTICE]
    maxPage.value = Math.ceil(data[Constant.RespondField.COUNT] / Constant.Global.DEFAULT_PAGE_SIZE)
    initialized.value = true
  })
})


const changeStatus = (id: number, filter: string) => {
  httpService.post(
      Constant.Api.NOTICE.ROOT,
      {
        noticeId: id,
        targetStatus: filter
      }
  ).then((data) => {
    if (!data[Constant.RespondField.SUCCESS]) {
      ElMessage.error("修改状态失败！")
    }
    routeTo.fresh()
  })
}
</script>

<template>
  <TemplatePage :side="true">
    <el-container>
      <el-aside>
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
        >
          <el-menu-item index="1" @click="routeTo.notice(Constant.NoticeFilter.ALL,1)">
            <span>全部通知</span>
          </el-menu-item>
          <el-menu-item index="2" @click="routeTo.notice(Constant.NoticeFilter.UNREAD,1)">
            <span>未读通知</span>
          </el-menu-item>
          <el-menu-item index="3" @click="routeTo.notice(Constant.NoticeFilter.READ,1)">
            <span>已读通知</span>
          </el-menu-item>
          <el-menu-item index="4" @click="routeTo.notice(Constant.NoticeFilter.ARCHIVED,1)">
            <span>归档</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <h1>通知列表</h1>
        <div v-if="initialized">
          <el-space fill direction="vertical" style="width:100%">
            <el-card class="notice" v-for="notice in notices">
              <template #header>
                <span>{{ notice.title }}</span>
                <br/>
                <el-text>{{ new Date(notice.sentTime).toLocaleDateString() }}</el-text>
              </template>
              <el-text>{{ notice.content }}</el-text>
              <template #footer>
                <el-button-group>
                  <el-button text bg type="info" @click="routeTo.page(notice.targetRoute)" v-if="notice.targetRoute">
                    查看
                  </el-button>
                  <el-button text bg type="primary" @click="changeStatus(notice.id,Constant.NoticeFilter.UNREAD)">未读
                  </el-button>
                  <el-button text bg type="primary" @click="changeStatus(notice.id,Constant.NoticeFilter.READ)">已读
                  </el-button>
                  <el-button text bg type="danger" @click="changeStatus(notice.id,Constant.NoticeFilter.ARCHIVED)">归档
                  </el-button>
                </el-button-group>
              </template>
            </el-card>
          </el-space>
          <el-pagination style="margin-top:10px" layout="prev, pager, next" :page-count="maxPage"
                         v-model:current-page="pageNow"/>
        </div>
        <el-skeleton animated v-else/>
      </el-main>
    </el-container>
  </TemplatePage>
</template>