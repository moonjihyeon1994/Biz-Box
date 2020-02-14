<template>
  <nav>
    <v-app-bar app color="transparent" flat>
      <v-icon @click="goHome">mdi-alpha-b-box</v-icon>
      <v-spacer />
      <!-- Logout -->
      <v-list dense>
        <v-list-item v-if="isLoggedIn">
          <v-list-item-action @click="goMypage">
            <v-icon class="navIcon">mdi-store</v-icon>
          </v-list-item-action>
          <v-list-item-action @click="logout">
            <v-icon class="navIcon">mdi-account-arrow-right</v-icon>
          </v-list-item-action>
        </v-list-item>
      <!-- Login Button + Modal -->
        <v-list-item v-else>
          <v-list-item-action @click="popupDialog">
            <v-icon class="navIcon">mdi-account</v-icon>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-app-bar>
  </nav>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import router from '../../router'
export default {
  name: 'app',
  data: () => ({
    imgSrc: '../../assets/logo.jpg'
  }),
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  methods: {
    ...mapActions(['logout', 'popupDialog']),
    goHome () {
      router.push('/')
    },
    goMypage () {
      router.push('/mypage')
    }
  }
}
</script>

<style scoped src="./Nav.css"></style>
