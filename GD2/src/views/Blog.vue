<script setup>
import Comment from "../components/Comment/CommentBox.vue";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const route = useRoute();
const blog = ref({});

const isLoading = ref(true);

const id = route.query.id;

const getBlogData = async (blogId) => {
  const response = await axios.get("/blogs/get-blog", {
    params: {
      id: blogId,
    },
  });
  console.log(response.data);
  blog.value = response.data;
  isLoading.value = false;
};

const updateViewCount = async (blogId) => {
  console.log(blogId);
  await axios.put(`/blogs/update-view?blogId=${blogId}`);
};

onMounted(() => {
  getBlogData(id);
  updateViewCount(id);
});
</script>

<template>
  <div v-if="isLoading">Đang tải dữ liệu...</div>

  <div v-else class="container text-center">
    <img
      :src="blog.image"
      alt="Image"
      style="width: 50em; max-height: 50vh; object-fit: cover"
    />
    <h1 class="my-5 fw-bold">{{ blog.title }}</h1>
    <div id="info" class="d-flex justify-content-around">
      <u class="text-start link-offset-2">Tác giả: {{ blog.user.fullname }}</u>
      <div class="text-end">
        <u class="link-offset-2">
          Ngày đăng: {{ blog.createdAt.dayOfMonth }}/{{
            blog.createdAt.monthValue
          }}/{{ blog.createdAt.year }} </u
        ><br />
        <u class="link-offset-2"> Lượt xem: {{ blog.view }} </u>
      </div>
    </div>
    <div id="content">
      <hr />
      <p class="text-start">{{ blog.content }}</p>
    </div>
    <div id="comment" class="my-5">
      <Comment :id="blog.id" />
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
