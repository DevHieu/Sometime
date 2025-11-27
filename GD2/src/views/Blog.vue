<script setup>
import Comment from "../components/Comment/CommentBox.vue";
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const blog = ref({});

const isLoading = ref(false);

const id = route.query.id;
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
