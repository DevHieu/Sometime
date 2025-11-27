<script setup>
defineProps({
  comment: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div class="d-flex align-items-center m-3 p-3 border rounded-3 bg-light">
    <div
      class="me-3 rounded-circle d-flex align-items-center justify-content-center p-0 overflow-hidden bg-dark"
      style="width: 40px; height: 40px"
    >
      <img
        v-if="comment.avatar !== null"
        :src="comment.avatar"
        alt="Avatar"
        class="w-100 h-100 object-fit-cover"
      />
      <span v-else class="pi pi-user text-white"></span>
    </div>

    <div class="flex-grow-1">
      <h6 class="mb-1 fw-bold">
        {{ comment.fullname || "Người dùng ẩn danh" }}
      </h6>

      <p class="mb-0 text-break">
        {{ comment.content }}
      </p>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
