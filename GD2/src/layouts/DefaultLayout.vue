<script setup>
import Navbar from "../components/Navbar.vue";
import Sidebar from "../components/Sidebar.vue";
</script>

<template>
  <div>
    <Navbar />
    <Sidebar />
    <div class="container p-0"><RouterView /></div>
  </div>
</template>

<style lang="scss" scoped></style>
