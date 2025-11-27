<script setup>
import { RouterLink } from "vue-router";

const props = defineProps({
  postData: {
    type: Object,
    required: true,
  },
});

const emits = defineEmits(["handledToogle"]);
</script>

<template>
  <div
    class="list-group-item-action card mb-4 border border-black rounded custom-card-bs5"
  >
    <div class="row g-0 h-100">
      <div class="col-md-4">
        <img
          :src="item.image"
          class="img-fluid rounded-start h-100 w-100 object-fit-cover"
          alt="image"
        />
      </div>
      <div class="col-md-7">
        <div class="card-body">
          <h3
            class="card-title fw-bold limit-multi-line"
            style="-webkit-line-clamp: 1"
          >
            {{ item.title }}
          </h3>
          <p class="card-text limit-multi-line">{{ item.content }}</p>
          <small class="card-text">
            <p class="text-body-secondary">
              Ngày đăng: {{ item.createdAt.dayOfMonth }}/{{
                item.createdAt.monthValue
              }}/{{ item.createdAt.year }}
            </p>
            <p class="text-body-secondary">Lượt xem: {{ item.view }}</p>
          </small>
        </div>
      </div>
      <div class="col-md-1">
        <div class="card-body d-flex flex-column gap-2">
          <button @click.stop="deleteItem" class="bg-danger text-white z-2">
            <i class="pi pi-trash"></i>
          </button>
          <button class="bg-success text-white">
            <RouterLink :to="`/blog?id=${item.id}`" class="text-white"
              ><i class="pi pi-eye"></i>
            </RouterLink>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
