<template>
  <v-app id="inspire">
    <Nav></Nav>
    <div class="viewcontainer">
      <router-view />
      <v-overlay
      v-if="isPopup"
      >
        <TotalForm></TotalForm>
      </v-overlay>
    </div>
  </v-app>
</template>

<script>
import '@/assets/css/reset.css'
import '@/assets/css/common.css'
import '@/assets/css/responsive.css'

import Nav from './nav/Nav'
import TotalForm from './totalform/TotalForm'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'app',
  data () {
    return {
      snack: true
    }
  },
  components: {
    Nav,
    TotalForm
  },
  methods: {
    ...mapActions(['closeDialog'])
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

@import url('https://fonts.googleapis.com/css?family=Do+Hyeon|Gugi|Nanum+Gothic|Noto+Sans+KR&display=swap');

#inspire {
  font-family: 'OG';
  /* font-family: 'Nanum Gothic', sans-serif; */
  /* font-family: 'Noto Sans KR', sans-serif; */
  /* font-family: 'Do Hyeon', sans-serif; */
  /* font-family: 'Gugi', cursive; */
}
</style>
