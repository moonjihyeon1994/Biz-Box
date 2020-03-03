<template>
  <transition name="modal">
    <div class="modal-mask">
        <div class="modal-container" >
          <button class="close-button" @click="$emit('close')"><v-icon>mdi-close-circle-outline</v-icon></button>
          <TabMenu />
          <hr />
        </div>
    </div>
  </transition>
</template>
<script>
import TabMenu from './tab_menu/TabMenu.vue'
export default {
  components: {
    TabMenu
  }
}
</script>

<style></style>
