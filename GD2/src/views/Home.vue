<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { RouterLink } from "vue-router";
import Banner from "../components/Home/Banner.vue";
import BlogItem from "../components/Home/BlogItem.vue";

const isLoading = ref(true);
const bannerList = ref([]);
const postList = ref([]);
const currentPage = ref(1);
const totalPages = ref(1);

const fetchPostData = async (page, size) => {
  try {
    const response = await axios.get("/blogs/get-by-page", {
      params: {
        page: page,
        size: size,
      },
    });
    console.log(response.data.posts);
    currentPage.value = response.data.page;
    totalPages.value = response.data.totalPages;
    postList.value = [...postList.value, ...response.data.posts];
  } catch (error) {
    console.error("Error fetching post data:", error);
  }

  try {
    const response = await axios.get("/blogs/get-5-most-watched");
    console.log(response.data);
    bannerList.value = response.data;
  } catch (error) {
    console.error("Error fetching post data:", error);
  }

  isLoading.value = false;
};

onMounted(() => {
  fetchPostData(1, 6);
});
</script>
<template>
  <div
    v-if="isLoading"
    class="position-absolute top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center z-3"
  >
    <div class="text-center p-4 rounded">
      <div
        class="spinner-border text-dark"
        role="status"
        style="width: 5rem; height: 5rem"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
      <p class="text-dark fw-bold fs-4 mt-3">Loading...</p>
    </div>
  </div>
  <div v-else>
    <section id="hero">
      <Banner :bannerList="bannerList" />
    </section>
    <section class="row row-cols-1 row-cols-md-3 g-4 my-5">
      <div class="col" v-for="post in postList">
        <BlogItem :post="post" />
      </div>
    </section>
    <div class="row justify-content-center my-4">
      <div class="col-auto">
        <button
          class="rounded-2 px-3 py-2 shadow-sm fs-5 fw-bold bg-white custom-button"
          :disabled="currentPage == totalPages"
          @click="fetchPostData(currentPage + 1, 6)"
        >
          Xem thêm
        </button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.custom-button {
  transition: all 0.15s ease-in-out;
  &:hover {
    box-shadow: 0 0 10px black !important;
  }
}
</style>
