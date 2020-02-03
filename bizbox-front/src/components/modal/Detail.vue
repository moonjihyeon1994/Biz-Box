<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <TabMenu />
          <!-- <div class="modal-header">
            <slot name="header">
              default header
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              default body
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              default footer -->
              <hr />
              <!-- <div> -->
              <button class="modal-default-button" @click="$emit('close')">
                close
              </button>
              <!-- </div> -->
            <!-- </slot> -->
          <!-- </div> -->
        </div>
      </div>
    </div>
  </transition>
</template>
<script>
import TabMenu from './TabMenu.vue'
export default {
  components: {
    TabMenu
  }
}
</script>

<style></style>
