<template>
  <v-app id="inspire">
    <Nav :navColor="navColor"></Nav>
    <div class="viewcontainer" v-scroll="onScroll">
      <router-view />
      <v-dialog
        v-model="isPopup"
        max-width="300"
      >
        <TotalForm></TotalForm>
      </v-dialog>
    </div>
  </v-app>
</template>

<script>
import '@/assets/css/reset.css'
import '@/assets/css/common.css'
import Nav from './nav/Nav'
import TotalForm from './totalform/TotalForm'
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'app',
  components: {
    Nav,
    TotalForm
  },
  data: () => ({
    navColor: 'transparent'
  }),
  methods: {
    ...mapActions(['initialDialog']),
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
  },
  computed: {
    ...mapGetters(['isPopup'])
  }
}
</script>

<style>
@font-face {
  font-family: 'OG';
  src: url('../assets/fonts/OmniGothicL.woff');
}
</style>
