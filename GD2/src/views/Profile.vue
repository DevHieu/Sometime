<script setup>
import { ref } from "vue";
import ProfileInfo from "../components/Profile/Profileinfo.vue";
import ChangePassword from "../components/Profile/ChangePassword.vue";

const isHandled = ref(false);

const isHandledToggle = (value) => {
  isHandled.value = value;
};
</script>

<template>
  <div
    v-if="isHandled"
    class="position-absolute top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center z-3"
    style="background: rgba(0, 0, 0, 0.6)"
  >
    <div class="text-center p-4 rounded">
      <div
        class="spinner-border text-light"
        role="status"
        style="width: 5rem; height: 5rem"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
      <p class="text-light fw-bold fs-4 mt-3">Đang xử lí...</p>
    </div>
  </div>
  <div
    class="container-lg d-flex align-items-center justify-content-center mt-4 p-4"
  >
    <div class="row w-100 main-container shadow-lg">
      <aside
        class="col-md-3 col-lg-3 p-0 sidebar-nav-container border-end pt-3"
      >
        <nav class="h-100 p-2">
          <ul
            class="nav flex-column nav-pills"
            id="profile-tabs"
            role="tablist"
          >
            <li class="nav-item" role="presentation">
              <button
                class="nav-link active text-start p-3 rounded border-0 d-flex align-items-center w-100 btn-dark"
                id="profile-tab"
                data-bs-toggle="tab"
                data-bs-target="#profile-panel"
                type="button"
                role="tab"
              >
                <span class="pi pi-user me-3"></span>
                Thông tin Hồ Sơ
              </button>
            </li>

            <li class="nav-item" role="presentation">
              <button
                class="nav-link text-start p-3 rounded border-0 d-flex align-items-center w-100"
                id="password-tab"
                data-bs-toggle="tab"
                data-bs-target="#password-panel"
                type="button"
                role="tab"
              >
                <span class="pi pi-lock me-3"></span>
                Đổi Mật khẩu
              </button>
            </li>
          </ul>
        </nav>
      </aside>

      <main class="col-md-9 col-lg-9 p-0">
        <div class="position-relative tab-content h-100 main-content-panel">
          <ProfileInfo @handledToogle="isHandledToggle" />
          <ChangePassword @handledToogle="isHandledToggle" />
        </div>
      </main>
    </div>
  </div>
</template>

<style lang="scss" scoped>
:root {
  --bs-primary: #000 !important; /* nút chính chuyển sang đen */
  --bs-primary-rgb: 0, 0, 0 !important;

  --bs-link-color: #fff;
  --bs-link-hover-color: #ccc;
}
</style>
