<script setup>
import axios from "axios";
import { ref, watch } from "vue";
import { uploadImage } from "../../services/uploadImageService";

const props = defineProps({
  postData: {
    type: Object,
    default: null,
  },
});

const isUpdating = ref(false);
const submitType = ref("create");

const id = ref(props.postData?.id || null);
const title = ref(props.postData?.title || "");
const content = ref(props.postData?.description || "");
const image = ref(null);
const isImageChange = ref(false);
const imageFile = ref(null);

const emits = defineEmits(["handledToogle"]);

watch(
  () => props.postData,
  (newPostData) => {
    submitType.value = newPostData && newPostData.id ? "update" : "create";
    if (newPostData && newPostData.id) {
      submitType.value = "update";
      isUpdating.value = true;
      id.value = newPostData.id || null;
      title.value = newPostData.title || "";
      content.value = newPostData.content || "";
      image.value = newPostData.image || "";
    } else {
      submitType.value = "create";
      isUpdating.value = false;
    }
  },
  { immediate: true }
);

const clearForm = () => {
  submitType.value = "create";
  isUpdating.value = false;
  id.value = null;
  title.value = "";
  content.value = "";
  image.value = null;
  isImageChange.value = false;
  imageFile.value = null;

  const imageInput = document.getElementById("imageInput");
  if (imageInput) {
    imageInput.value = "";
  }
};

const handleUploadImage = (e) => {
  isImageChange.value = true;

  const image = e.target.files[0];
  const reader = new FileReader();
  reader.readAsDataURL(image);
  reader.onload = (e) => {
    imageFile.value = e.target.result;
  };
};

const handleFormSubmit = async () => {
  emits("handledToogle", true);
  const payload = {
    id: id.value,
    title: title.value,
    content: content.value,
    username: JSON.parse(sessionStorage.getItem("user")).username,
  };

  if (image.value == null && !isImageChange.value) {
    alert("Vui lòng chọn ảnh cho bài đăng.");
    return;
  }

  if (isImageChange.value) {
    try {
      const finalAvatarUrl = await uploadImage(imageFile.value);
      payload.image = finalAvatarUrl;
    } catch (error) {
      isUpdating.value = false;
      alert("Lỗi upload ảnh. Vui lòng thử lại.");
      return;
    }
  } else {
    payload.image = image.value;
  }

  if (isUpdating.value) {
    updateBlog(payload);
  } else {
    createBlog(payload);
  }
  emits("handledToogle", false);
};

const createBlog = async (payload) => {
  payload.id = null;
  const resp = await axios.post("/blogs/create", payload);

  if (resp.data.status) {
    alert("Tạo bài đăng thành công!");
    window.location.reload();
  } else {
    alert("Tạo bài đăng thất bại. Vui lòng thử lại.");
    clearForm();
  }
};
const updateBlog = async (payload) => {
  const resp = await axios.put("/blogs/update-blog", payload);
  if (resp.data.status) {
    alert("Cập nhật bài đăng thành công!");
    window.location.reload();
  } else {
    alert("Cập nhật bài đăng thất bại. Vui lòng thử lại.");
    clearForm();
  }
};
</script>

<template>
  <form class="d-flex flex-column h-100" @submit.prevent="handleFormSubmit">
    <h3>Thông tin Blog</h3>
    <div class="body">
      <div class="mb-3">
        <label class="form-label">Tiêu đề</label>
        <input
          v-model="title"
          type="text"
          name="title"
          class="form-control"
          maxlength="50"
          required
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Nội dung</label>
        <textarea
          v-model="content"
          name="description"
          class="form-control"
          rows="10"
          placeholder="Nội dung bài đăng..."
          required
        ></textarea>
      </div>

      <div class="mb-3">
        <label class="form-label">Ảnh:</label>
        <input
          @change="handleUploadImage"
          type="file"
          name="imageFile"
          class="form-control"
          accept="image/*"
          id="imageInput"
        />
        <div class="mt-2">
          <!--  -->
          <img
            v-if="image !== null || isImageChange"
            id="imagePreview"
            :src="imageFile === null ? image : imageFile"
            alt="Poster preview"
            style="
              max-width: 200px;
              max-height: 15em;
              border-radius: 8px;
              object-fit: cover;
            "
          />
        </div>
      </div>
    </div>

    <div class="mt-auto mb-3 d-flex gap-3">
      <button type="submit" class="btn btn-warning">{{ submitType }}</button>
      <button @click="clearForm" type="button" class="btn btn-secondary">
        Clear
      </button>
    </div>
  </form>
</template>

<style lang="scss" scoped></style>
