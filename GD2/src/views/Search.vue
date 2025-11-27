<script setup>
import { ref } from "vue";
import { useRoute, RouterLink } from "vue-router";

const route = useRoute();

const resultList = ref([]);
const query = route.query.query;
</script>

<template>
  <h1 class="my-5 fw-bold">
    Kết quả tìm kiếm cho: <u class="link-offset-2">{{ query }}</u>
  </h1>
  <div>
    <div
      class="card mb-4 border border-black rounded custom-card-bs5"
      v-for="post in resultList"
    >
      <div class="row g-0 h-100">
        <div class="col-md-4 h-100">
          <img
            :src="post.image"
            class="img-fluid rounded-start h-100 w-100 object-fit-cover"
            :alt="post.title"
          />
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h3
              class="card-title fw-bold limit-multi-line"
              style="-webkit-line-clamp: 1"
            >
              {{ post.title }}
            </h3>
            <p class="card-text limit-multi-line">
              {{ post.content }}
            </p>
            <small class="card-text">
              <p class="text-body-secondary">
                Ngày đăng: {{ post.createdAt.dayOfMonth }}/{{
                  post.createdAt.monthValue
                }}/{{ post.createdAt.year }}
              </p>
              <p class="text-body-secondary">Lượt xem: {{ post.view }}</p>
            </small>
          </div>
        </div>
      </div>
      <RouterLink
        :to="`/blog?id=${post.id}`"
        class="stretched-link"
      ></RouterLink>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.custom-card-bs5 {
  height: 15em !important;
  transition: all 0.3s ease-in-out;
  &:hover {
    box-shadow: 0 0 15px 2px black !important;
  }
}
.limit-multi-line {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>
