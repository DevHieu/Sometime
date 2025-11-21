<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();

const username = ref("");
const password = ref("");
const message = ref("");

const login = async () => {
  const res = await axios.post("/auth/login", {
    username: username.value,
    password: password.value,
  });
  console.log(res);
  if (res.data.status == 1) {
    sessionStorage.setItem("loggedIn", "true");
    sessionStorage.setItem("user", JSON.stringify(res.data.user)); //session chỉ có thể lưu kiểu String => chuyển Json sang String
    router.push("/");
  } else if (res.data.status == 0) {
    //Không có tài khoản
    message.value = "*Không có tài khoản*";
  } else {
    // Sai mật khẩu
    message.value = "*Sai mật khẩu*";
  }
};
</script>

<template>
  <div
    class="container d-flex justify-content-center align-items-center vh-100"
  >
    <div class="col-12 col-sm-8 col-md-5 col-lg-4">
      <div class="card shadow">
        <div class="card-body p-4">
          <h3 class="text-center mb-4 fw-bold">Đăng nhập</h3>
          <p class="text-danger text-center">{{ message }}</p>
          <form @submit.prevent="login">
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
              <label class="form-label">Mật khẩu</label>
              <input
                v-model="password"
                type="password"
                class="form-control"
                required
              />
            </div>

            <button type="submit" class="btn btn-dark w-100 mt-2">
              Đăng nhập
            </button>

            <div class="text-center mt-3">
              Chưa có tài khoản?
              <router-link to="/auth/register" class="small text-black fw-bold">
                Đăng ký
              </router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
