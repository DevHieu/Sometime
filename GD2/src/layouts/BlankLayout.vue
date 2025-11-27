<script setup>
defineProps({});
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="container vh-100">
    <RouterLink to="/" class="position-absolute mt-5 z-3">
      <img src="../assets/icons/logo.png" alt="logo" style="width: 300px" />
    </RouterLink>
    <div
      class="min-vh-100 d-flex flex-column justify-content-center align-items-center"
    >
      <RouterView />
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
