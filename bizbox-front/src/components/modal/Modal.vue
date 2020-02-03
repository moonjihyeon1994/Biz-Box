<template>
  <div>
    <button id="show-modal" @click="showModal = true">Show Modal</button>
    <!-- use the modal component, pass in the prop -->
    <Detail v-if="showModal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
      <h3 slot="header">custom header</h3>
    </Detail>
  </div>
</template>

<script>
import Detail from '@/components/modal/Detail.vue'
import './Modal.css'
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
