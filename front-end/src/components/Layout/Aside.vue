<script setup>
import {
  House,
    View,
    Search,
    PieChart,
    ChatRound
} from '@element-plus/icons-vue'
</script>

<template>
  <div class="aside">
    <div class="el-aside-logo"></div>
    <!-- element-plus的菜单标签 -->
    <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff" router>
      <el-menu-item index="/home">
        <el-icon><House/></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-sub-menu>
        <template #title>
          <el-icon><View/></el-icon>
          <span>关系可视化</span>
        </template>
        <el-menu-item index="/relation/search">
          <el-icon><Search/></el-icon>
          <span>检索关系</span>
        </el-menu-item>
        <el-menu-item index="/relation/overall">
          <el-icon><PieChart/></el-icon>
          <span>关系全貌</span>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/chat">
        <el-icon><ChatRound/></el-icon>
        <span>问答系统</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style>
.aside {
  height: 100vh;
  background-color: #232323;
  .el-aside-logo {
    height: 120px;
    background: url('@/assets/img/logo.png') no-repeat center / 120px auto;
  }
  el-menu {
    border-right: none;
  }
}
</style>
