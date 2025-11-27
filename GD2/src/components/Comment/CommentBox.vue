<script setup>
const props = defineProps({
  id: {
    type: Number,
    required: true,
  },
});

import { ref } from "vue";
import CommentItem from "./CommentItem.vue";

const user = ref({});

const comment = ref("");
const commentList = ref([]);
</script>

<template>
  <div class="border text-start">
    <h4 class="m-3 fw-bold">Bình luận</h4>
    <div>
      <CommentItem
        v-for="(item, index) in commentList"
        :key="index"
        :comment="item"
      />
    </div>
    <div class="container my-3">
      <form id="commentForm" @submit.prevent="handleSendComment">
        <div class="mb-3">
          <textarea
            class="form-control"
            id="commentContent"
            rows="3"
            placeholder="Nhập bình luận tại đây..."
            required
            v-model="comment"
            :disabled="user === null"
          ></textarea>
        </div>

        <button type="submit" class="btn btn-dark" :disabled="user === null">
          Gửi Bình Luận
        </button>
        <span v-if="user === null" class="mt-2 text-danger">
          Đăng nhập để có thể gửi bình luận
        </span>
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
