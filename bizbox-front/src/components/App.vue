<template>
  <v-app id="inspire">
    <Nav :navColor="navColor"></Nav>
    <div class="viewcontainer" v-scroll="onScroll">
      <router-view />
    </div>
  </v-app>
</template>

<script>
import '@/assets/css/reset.css'
import '@/assets/css/common.css'
import Nav from './nav/Nav'
export default {
  name: 'app',
  components: {
    Nav
  },
  data: () => ({
    navColor: 'transparent'
  }),
  methods: {
    onScroll () {
      if (document.documentElement.scrollTop === 0) {
        this.navColor = 'transparent'
      } else {
        this.navColor = '#ffffff'
      }
    }
  },
  created () {
    this.$store.dispatch('initialLogin')
  }
}
</script>
