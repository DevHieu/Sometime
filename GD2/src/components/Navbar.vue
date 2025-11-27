<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";

const isAuthenticated = ref();
const user = ref({});
</script>

<template>
  <nav class="sticky-top navbar navbar-expand-lg z-2">
    <div class="container-fluid">
      <div class="navbar-brand">
        <RouterLink to="/">
          <img
            src="../assets/icons/logo.png"
            alt="logo"
            class="mx-5"
            style="height: 60px"
          />
        </RouterLink>
      </div>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0 gap-4"></ul>

        <div class="d-flex align-items-center me-5">
          <form
            class="d-flex position-relative me-2"
            role="search"
            action="/search"
            method="get"
          >
            <input
              class="form-control rounded-pill"
              name="query"
              type="search"
              placeholder="Tìm kiếm"
              aria-label="Search"
              style="padding-right: 3rem"
            />

            <button
              class="btn btn-link position-absolute end-0 top-50 translate-middle-y text-black py-1 px-2 border-start"
              type="submit"
              aria-label="Search"
            >
              <i class="pi pi-search"></i>
            </button>
          </form>

          <!-- Menu khi user đăng nhập thành công -->
          <div v-if="isAuthenticated">
            <button
              class="btn btn-dark rounded-circle d-flex align-items-center justify-content-center p-0 overflow-hidden"
              style="width: 40px; height: 40px"
              data-bs-toggle="offcanvas"
              data-bs-target="#sidebar"
            >
              <span v-if="user.avatar === null" class="pi pi-user"></span>
              <img
                v-else
                :src="user.avatar"
                alt=""
                class="w-100 h-100 object-fit-cover"
              />
            </button>
          </div>

          <div v-else>
            <RouterLink
              to="/auth/login"
              class="text-decoration-none d-flex align-items-center ms-2 border rounded-pill px-3 py-1 login-btn"
            >
              <span class="text-black fw-semibold">Login</span>
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style lang="scss" scoped>
.navbar {
  backdrop-filter: blur(20px) !important;
}

.login-btn {
  transition: all 0.3s ease;
  &:hover {
    box-shadow: 0 0 5px black;
    border-color: black;
    text-decoration: none;
  }
}
</style>
