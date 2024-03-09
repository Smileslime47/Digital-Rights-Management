<template>
    <el-container>
      <el-header>
        <TopNavBar />
      </el-header>

      <el-main style="min-height: 80vh">
        <!--TopNavBar导航栏预留空间-->
        <div style="height:60px"/>
        <router-view/>
      </el-main>

      <el-footer>
        <Footer/>
      </el-footer>
    </el-container>
</template>

<style>
.el-header{
  height: 60px;
  width: 100%;
  position: fixed;
  padding:0;
  top: 0;
  left: 0;
  z-index: 1;
}

.el-footer{
  padding:0 !important;
}
</style>
