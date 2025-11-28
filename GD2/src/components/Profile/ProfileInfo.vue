<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { uploadImage } from "../../services/uploadImageService";

const emits = defineEmits(["handledToogle"]);

const username = ref("");
const fullname = ref("");
const email = ref("");

const avatar = ref(null);
const isAvatarChange = ref(false);
const avatarFile = ref(null);

onMounted(() => {
  const data = JSON.parse(sessionStorage.getItem("user")); //Chuyển String sang Objext
  username.value = data.username;
  fullname.value = data.fullname;
  email.value = data.email;
  avatar.value = data.avatar;
  console.log(data.avatar);
});

const handleAvatarChange = (e) => {
  isAvatarChange.value = true;

  const image = e.target.files[0];
  const reader = new FileReader();
  reader.readAsDataURL(image);
  reader.onload = (e) => {
    avatarFile.value = e.target.result;
  };
};

const handleUpdateUser = async () => {
  emits("handledToogle", true);

  const payload = {
    username: username.value,
    fullname: fullname.value,
    email: email.value,
    avatar: avatar.value,
  };

  if (isAvatarChange.value) {
    try {
      const finalAvatarUrl = await uploadImage(avatarFile.value);
      payload.avatar = finalAvatarUrl;
    } catch (error) {
      emits("handledToogle", false);
      alert("Lỗi upload ảnh. Vui lòng thử lại.");
      return;
    }
  }

  const res = await axios.put("/user/update-info", payload);

  if (res.data.status) {
    sessionStorage.setItem("user", JSON.stringify(res.data.user));
    window.location.reload();
  }

  emits("handledToogle", false);
};
</script>

<template class="position-relative">
  <div class="tab-pane fade show active p-4" id="profile-panel" role="tabpanel">
    <h2 class="text-black mb-5 fw-bold">Thông Tin Hồ Sơ</h2>

    <form @submit.prevent="handleUpdateUser">
      <div class="d-flex align-items-center gap-4 mb-5">
        <div
          class="d-flex border rounded-circle justify-content-center align-items-center overflow-hidden"
          style="width: 7em; height: 7em; font-size: 1rem"
        >
          <span v-if="avatar === null && !isAvatarChange" class="small"
            >Avatar</span
          >
          <img
            v-else
            :src="avatarFile === null ? avatar : avatarFile"
            alt=""
            class="w-100 h-100 object-fit-cover"
          />
        </div>

        <div class="d-flex gap-2">
          <label
            for="avatarFileInput"
            class="btn btn-primary fw-semibold avatar-button"
          >
            Thay đổi Avatar
          </label>

          <input
            class="d-none"
            id="avatarFileInput"
            name="avatar"
            type="file"
            accept="image/*"
            @change="handleAvatarChange"
          />
          <button type="button" class="btn btn-danger fw-semibold">Xóa</button>
        </div>
      </div>

      <div class="mb-4">
        <label class="form-label text-black mb-2">Tên đăng nhập</label>
        <input
          type="text"
          class="form-control profile-input"
          v-model="username"
          readonly
        />
      </div>

      <div class="mb-4">
        <label class="form-label text-black mb-2">Họ và tên</label>
        <input
          type="text"
          class="form-control profile-input"
          v-model="fullname"
        />
      </div>

      <div class="mb-5">
        <label class="form-label text-black mb-2">Email</label>
        <input
          type="email"
          class="form-control profile-input"
          v-model="email"
        />
      </div>

      <div class="text-end">
        <button class="btn btn-primary fw-bold px-4 py-2" type="submit">
          Lưu thay đổi
        </button>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped></style>
