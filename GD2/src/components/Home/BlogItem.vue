<script setup>
const props = defineProps({
  post: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div
    class="card h-100 border-0 shadow-sm rounded-4 d-flex flex-column custom-card-bs5"
  >
    <div class="position-relative overflow-hidden custom-img-container">
      <img
        :src="post.image"
        class="card-img-top position-absolute w-100 h-100 object-fit-cover top-0 start-0"
        :alt="post.title"
      />
    </div>

    <div class="card-body d-flex flex-column flex-grow-1 p-3">
      <div class="d-flex justify-content-between mb-1">
        <p class="card-date text-secondary small">
          {{ post.createdAt.dayOfMonth }}/{{ post.createdAt.monthValue }}/{{
            post.createdAt.year
          }}
        </p>
        <p class="card-date text-secondary small">{{ post.view }} lượt xem</p>
      </div>

      <h4
        class="card-title fw-bold mb-2 limit-multi-line"
        style="-webkit-line-clamp: 2"
      >
        {{ post.title }}
      </h4>

      <p
        class="card-text text-muted mb-0 flex-grow-1 limit-multi-line"
        style="-webkit-line-clamp: 4"
      >
        {{ post.content }}
      </p>
    </div>
    <RouterLink :to="`/blog?id=${post.id}`" class="stretched-link"></RouterLink>
  </div>
</template>

<style lang="scss" scoped>
.limit-multi-line {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.custom-card-bs5 {
  height: 30rem !important;
  transition: all 0.3s ease-in-out;
  &:hover {
    box-shadow: 0 0 15px 2px black !important;
    transform: translateY(-5px);
  }
}

.custom-img-container {
  padding-top: 60%;
}
</style>
