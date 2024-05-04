<script setup>
import Aside from "@/components/Layout/Aside.vue";
import Header from "@/components/Layout/Header.vue";
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <Aside></Aside>
      </el-aside>
      <el-container>
        <el-header>
          <Header></Header>
        </el-header>
        <el-main class="flex-1 overflow-auto">
          <router-view></router-view>
        </el-main>
        <el-footer class="flex-shrink-0">
          AI云寻 ©2024 Created by Canary&Koala
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout {
  height: 100vh; /* 设置高度为视口高度 */
  width: 100%;
}
</style>
