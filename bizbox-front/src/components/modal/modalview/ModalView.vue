<template>
  <transition name="modal">
    <div class="modal-mask">
        <div class="modal-container" >
          <button class="close-button" @click="$emit('close')"><v-icon>mdi-close-circle-outline</v-icon></button>
          <TabMenu :clickEvent='clickEvent'/>
          <hr />
        </div>
    </div>
  </transition>
</template>
<script>
import TabMenu from './tabmenu/TabMenu.vue'
export default {
  components: {
    TabMenu
  },
  props: {
    clickEvent: Boolean
  }
}
</script>

<style></style>
