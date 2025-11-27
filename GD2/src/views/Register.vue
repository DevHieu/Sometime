<script setup>
import { ref } from "vue";

const message = ref("");
const username = ref("");
const fullname = ref("");
const email = ref("");
const password = ref("");
</script>

<template>
  <div
    class="container d-flex justify-content-center align-items-center vh-100"
  >
    <div class="col-12 col-sm-8 col-md-5 col-lg-4">
      <div class="card shadow">
        <div class="card-body p-4">
          <h3 class="text-center mb-4 fw-bold">Đăng ký</h3>
          <p class="text-danger text-center">{{ message }}</p>

          <form @submit.prevent="register">
            <div class="mb-3">
              <label class="form-label">Tên đăng nhập</label>
              <input
                v-model="username"
                type="text"
                class="form-control"
                required
              />
            </div>

            <div class="mb-3">
              <label class="form-label">Họ và tên</label>
              <input
                v-model="fullname"
                type="text"
                class="form-control"
                required
              />
            </div>

            <div class="mb-3">
              <label class="form-label">Mật khẩu</label>
              <input
                v-model="password"
                type="password"
                class="form-control"
                required
              />
            </div>

            <div class="mb-3">
              <label class="form-label">Email</label>
              <input
                v-model="email"
                type="email"
                class="form-control"
                required
              />
            </div>

            <button type="submit" class="btn btn-dark w-100 mt-2">
              Đăng ký tài khoản
            </button>

            <div class="text-center mt-3">
              Đã có tài khoản?
              <router-link to="/auth/login" class="small text-black fw-bold">
                Đăng nhập
              </router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
