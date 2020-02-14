<template>
  <div class="modal-container">
    <button id="show-modal" @click="showModal = true">Show Modal</button>
    <!-- use the modal component, pass in the prop -->
    <Detail v-if="showModal" @close="showModal = false">
      <!--
      you can use custom content here to overwrite
      default content
    -->
    </Detail>
    <select-box :list='list'></select-box>
  </div>
</template>

<script>
import Detail from '@/components/modal/modal_view/modal_view.vue'
import SelectBox from '@/components/modal/modal_view/tab_menu/tab_contents/selectbox/SelectBox.vue'
import './Modal.css'
export default {
  data: () => {
    return {
      showModal: false,
      list: [
        {
          name: 'Sol',
          data: 80
        },
        {
          name: 'Terra',
          data: 75
        },
        {
          name: 'Kilian',
          data: 30
        },
        {
          name: 'Corel',
          data: 45
        }
      ]
    }
  },
  components: {
    Detail,
    SelectBox
  }
}
</script>
<style>
</style>
