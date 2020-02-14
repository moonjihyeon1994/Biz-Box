<template>
  <div class="modal-container">
    <button id="show-modal" @click="change">{{ this.comment }}</button>
    <UseDetail v-if="showModal" @close="showModal = false">
    </UseDetail>
  </div>
</template>

<script>
import UseDetail from '@/components/howToUse/detail/UseDetail.vue'
import './howToUse.css'
export default {
  data: () => {
    return {
      showModal: false,
      comment: "bizbox 사용방법 익히기"
    }
  },
  components: {
    UseDetail,
    temp
  },
  methods: {
    change () {
      this.showModal = !this.showModal
      if (this.showModal) {
        this.comment = "숨기기"
      } else {
        this.comment = 'bizbox 사용방법 익히기'
      }
    }
  }
}
</script>
<style>
</style>
