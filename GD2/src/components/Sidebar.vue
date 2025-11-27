<script setup>
import { RouterLink } from "vue-router";

const logout = () => {
  sessionStorage.clear();
  window.location.href = "/";
};
</script>

<template>
  <div
    class="offcanvas offcanvas-end"
    data-bs-scroll="true"
    tabindex="-1"
    id="sidebar"
    aria-labelledby="sidebarLabel"
  >
    <div class="offcanvas-header">
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="offcanvas"
        aria-label="Close"
        style="font-size: 20px; margin: 10px"
      ></button>
    </div>
    <div class="offcanvas-body p-0 d-flex flex-column">
      <div>
        <a
          href="/"
          class="list-group-item list-group-item-action active"
          aria-current="true"
        >
          Trang Chủ
        </a>
        <a href="/profile" class="list-group-item list-group-item-action">
          Tài khoản
        </a>
        <a href="/uploads" class="list-group-item list-group-item-action">
          Bài đăng
        </a>
      </div>

      <button
        @click="logout"
        class="list-group-item list-group-item-action mt-auto border-top"
      >
        <span class="fw-semibold text-danger">Đăng Xuất</span>
      </button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.list-group-item {
  border: none;
  padding: 2rem;
  font-size: 1.2rem;
  transition: all 0.3s ease;
  &:hover {
    background-color: #ebebeb7a;
  }
}
</style>
