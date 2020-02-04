<template>
  <v-app id="inspire">
    <v-app-bar app :color="navColor" flat>
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
    <div class="viewcontainer" v-scroll="onScroll">
      <router-view />
    </div>
  </v-app>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import '@/assets/css/reset.css'
import '@/assets/css/common.css'
export default {
  name: 'app',
  data: () => ({
    drawer: null,
    routeon: true,
    navList: [
      { routeto: '/login', icon: 'mdi-account', title: 'Login' },
      { routeto: '/', icon: 'mdi-card-search-outline', title: 'Home' },
      { routeto: '/bizmap', icon: 'mdi-information-outline', title: 'Search' }
    ],
    navColor: 'transparent'
  }),
  methods: {
    ...mapActions(['logout']),
    onScroll () {
      if (document.documentElement.scrollTop === 0) {
        this.navColor = 'transparent'
      } else {
        this.navColor = '#324043'
      }
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  }
}
</script>
