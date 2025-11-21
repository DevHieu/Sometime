<script setup>
import UploadForm from "../components/Upload/UploadForm.vue";
import UploadItem from "../components/Upload/UploadItem.vue";
import { ref, onMounted } from "vue";
import axios from "axios";

const postList = ref([]);
const postSelected = ref(null);

const isHandled = ref(false);

const fetchData = async (userId) => {
  const response = await axios.get("/blogs/get-by-userid", {
    params: {
      userId: userId,
    },
  });
  console.log(response.data);
  postList.value = response.data;
};

const fillFormWithData = (postData) => {
  console.log("ddafsdhjg");
  postSelected.value = postData;
};

const isHandledToggle = (value) => {
  isHandled.value = value;
};

onMounted(() => {
  fetchData(JSON.parse(sessionStorage.getItem("user"))?.username);
});
</script>

<template class="position-relative;">
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
  <div class="row">
    <div class="col-sm-7 border-end">
      <h1><strong>Các bài đăng của bạn</strong></h1>
      <div style="height: 84.5vh; overflow-y: auto">
        <ul class="list-group">
          <UploadItem
            v-for="item in postList"
            :key="item.id"
            :postData="item"
            @click="fillFormWithData(item)"
          />
        </ul>
      </div>
    </div>
    <div class="col-sm-5 border-start">
      <UploadForm @handledToogle="isHandledToggle" :postData="postSelected" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.sticky-panel {
  top: var(--navbar-height);
  height: var(--real-height);
}
</style>
