<template>
  <div class="modal-container">
    <button id="show-modal" @click="showModal = true">Show Modal</button>
    <Detail v-if="showModal" @close="showModal = false">
    </Detail>
  </div>
</template>

<script>
import Detail from '@/components/howToUse/detail/Detail.vue'
import './howToUse.css'
export default {
  data: () => {
    return {
      showModal: false
    }
  },
  components: {
    Detail
  }
}
</script>
<style>
</style>
