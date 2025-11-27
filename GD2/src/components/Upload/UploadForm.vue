<script setup>
import { ref } from "vue";

const props = defineProps({
  postData: {
    type: Object,
    default: null,
  },
});

const submitType = ref("create");

const title = ref(props.postData?.title || "");
const content = ref(props.postData?.description || "");
const image = ref(null);
const isImageChange = ref(false);
const imageFile = ref(null);

const emits = defineEmits(["handledToogle"]);
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
