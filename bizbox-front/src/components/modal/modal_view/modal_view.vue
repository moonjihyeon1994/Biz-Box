<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <button class="close-button" @click="$emit('close')">x</button>
          <TabMenu />
          <hr />
        </div>
      </div>
    </div>
  </transition>
</template>
<script>
import TabMenu from './tab_menu/TabMenu.vue'
export default {
  components: {
    TabMenu
  },
  mounted () {
    this.handleResize()
  },
  created () {
    window.addEventListener('resize', this.handleResize)
    this.handleResize()
  },
  destroyed () {
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    handleResize () {
      const boderHeight = 120
      let modalcontainer = document.getElementsByClassName('modal-container')
      let newHeight = window.innerHeight - boderHeight - 50 + 'px'
      try {
        modalcontainer[1].style.height = newHeight
      } catch (e) {
        // console.log(e)
      }
    }
  }
}
</script>

<style></style>
