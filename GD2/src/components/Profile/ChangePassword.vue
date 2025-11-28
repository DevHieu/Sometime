<script setup>
import { ref } from "vue";
import axios from "axios";

const message = ref("");
const messageColor = ref("text-success");
const oldPassword = ref("");
const newPassword = ref("");
const confirmPassword = ref("");

const emits = defineEmits(["handledToogle"]);

const handleChangePassword = async () => {
  emits("handledToogle", true);
  const userId = JSON.parse(sessionStorage.getItem("user")).username;

  if (newPassword.value !== confirmPassword.value) {
    messageColor.value = "text-danger";
    message.value = "Mật khẩu mới và xác nhận mật khẩu không khớp.";
    emits("handledToogle", false);
    return;
  }

  const resp = await axios.put("/user/change-password", {
    username: userId,
    oldPassword: oldPassword.value,
    newPassword: newPassword.value,
  });

  if (resp.data.status) {
    message.value = "Đổi mật khẩu thành công.";
    messageColor.value = "text-success";
    oldPassword.value = "";
    newPassword.value = "";
    confirmPassword.value = "";
  } else {
    message.value = resp.data.message;
    messageColor.value = "text-danger";
  }

  emits("handledToogle", false);
};
</script>

<template>
  <div class="tab-pane fade p-4" id="password-panel" role="tabpanel">
    <h2 class="text-black mb-4 fw-bold">Đổi Mật khẩu</h2>
    <strong v-bind:class="messageColor" v-html="message"></strong>
    <form @submit.prevent="handleChangePassword">
      <div class="mb-4">
        <label class="form-label text-black mb-2">Mật khẩu hiện tại</label>
        <input
          type="password"
          class="form-control profile-input"
          v-model="oldPassword"
        />
      </div>

      <div class="mb-4">
        <label class="form-label text-black mb-2">Mật khẩu mới</label>
        <input
          type="password"
          class="form-control profile-input"
          v-model="newPassword"
        />
      </div>

      <div class="mb-5">
        <label class="form-label text-black mb-2">Xác nhận mật khẩu mới</label>
        <input
          type="password"
          class="form-control profile-input"
          v-model="confirmPassword"
        />
      </div>

      <div class="text-end">
        <button type="submit" class="btn btn-primary fw-bold px-4 py-2">
          Cập nhật mật khẩu
        </button>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped></style>
