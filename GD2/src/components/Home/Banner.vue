<script setup>
const props = defineProps({
  bannerList: {
    type: Array,
    required: true,
  },
});
</script>

<template>
  <div
    id="carousel"
    class="carousel slide d-flex align-items-center"
    data-bs-ride="carousel"
  >
    <button
      class="btn"
      type="button"
      data-bs-target="#carousel"
      data-bs-slide="prev"
    >
      <i class="pi pi-angle-left" style="font-size: 2rem"></i>
      <span class="visually-hidden">Previous</span>
    </button>
    <div class="carousel-inner position-relative">
      <div
        v-for="(item, index) in props.bannerList"
        :key="item.id || index"
        :class="['carousel-item', { active: index === 0 }]"
      >
        <div class="row" style="height: 60vh">
          <div class="col-sm-8 p-0 h-100">
            <img
              :src="item.image"
              :alt="item.title"
              class="w-100 h-100 object-fit-cover"
            />
          </div>
          <div
            class="col-sm-4 h-100 py-5 d-flex flex-column justify-content-center"
            style="background-color: #ebebeb7a"
          >
            <h1
              class="fw-bold limit-multi-line"
              style="font-size: 50px; height: 80%; -webkit-line-clamp: 5"
            >
              {{ item.title }}
            </h1>
            <p class="pe-2 limit-multi-line">
              {{ item.content }}
            </p>
          </div>
        </div>
        <RouterLink
          :to="`/blog?id=${item.id}`"
          class="stretched-link"
        ></RouterLink>
      </div>
    </div>

    <button
      type="button"
      data-bs-target="#carousel"
      data-bs-slide="next"
      class="btn"
    >
      <i class="pi pi-angle-right" style="font-size: 2rem"></i>
      <span class="visually-hidden">Next</span>
    </button>
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
</style>
