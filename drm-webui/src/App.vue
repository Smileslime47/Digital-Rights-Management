<template>
  <!--
  如果把导航栏等内容放在router-view外会导致路由刷新时展示在这些组件上的信息无法及时跟进
  因此将所有内容都放在router-view内，通过<TemplatePage>构建页面模板
  -->
  <router-view/>
</template>