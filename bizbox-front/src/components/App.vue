<template>
  <v-app id="inspire">
    <v-app-bar app dark color="#041b2d">
      <v-toolbar-title style="width: 300px">
        <span>BizBox</span>
      </v-toolbar-title>
      <v-spacer />
      <v-list dense v-for="nav in navList" :key="nav.routeto">
        <v-list-item link :to="nav.routeto">
          <v-list-item-content>
            <v-list-item-title>{{ nav.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-app-bar>
    <div class="container">
      <router-view />
    </div>
  </v-app>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'app',
  data: () => ({
    drawer: null,
    titleon: true,
    navList: [
      { routeto: '/', icon: 'mdi-card-search-outline', title: 'Home' },
      { routeto: '/result', icon: 'mdi-information-outline', title: 'Result' },
      { routeto: '/about', icon: 'mdi-information-outline', title: 'About' },
      { routeto: '/login', icon: 'mdi-account', title: 'Login' }
    ]
  }),
  methods: {
    ...mapActions(['logout']),
    onResize () {
      if (window.innerWidth > 700) {
        this.titleon = true
      } else {
        this.titleon = false
      }
    }
  },
  created () {
    this.onResize()
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  }
}
</script>

<style src="@/components/common/common.css"></style>
<style scoped src="@/components/common/Navbar.css"></style>
