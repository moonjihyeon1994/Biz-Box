<template>
  <nav>
    <v-app-bar app :color="navColor" flat>
      <v-toolbar-title style="width: 300px">
        <span>BizBox</span>
      </v-toolbar-title>
      <v-spacer />
      <v-list dense v-for="(nav, index) in navList" :key="'N' + index">
        <v-list-item link :to="nav.routeto">
          <v-list-item-content>
            <v-list-item-title>{{ nav.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-list dense v-if="isLoggedIn">
        <v-list-item link>
          <v-list-item-content @click.prevent="logout">
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-list dense v-else v-for="(item, index) in logList" :key="'L' + index">
        <v-list-item link :to="item.routeto">
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-app-bar>
  </nav>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'app',
  data: () => ({
    navList: [
      { routeto: '/', icon: 'mdi-card-search-outline', title: 'Home' },
      { routeto: '/bizmap', icon: 'mdi-information-outline', title: 'Search' },
      { routeto: '/modal', icon: 'mdi-information-outline', title: 'Modal' }
    ],
    logList: [{ routeto: '/login', icon: 'mdi-account', title: 'Login' }]
  }),
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  methods: {
    ...mapActions(['logout'])
  },
  props: {
    navColor: {
      type: String
    }
  }
}
</script>

<style scoped src="./Nav.css"></style>
