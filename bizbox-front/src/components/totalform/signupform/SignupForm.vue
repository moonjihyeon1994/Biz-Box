<template>
  <form action="" @submit.prevent="signup(credentials)">
    <input v-model="credentials.name"
      type="text"
      name="name"
      required
      placeholder="username"
      minlength="4"
    />
    <input
      type="email" v-model="credentials.email"
      name="email"
      placeholder="Email"
      required
      pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
    />
    <input
      type="password" v-model="credentials.pw"
      name="password"
      required
      placeholder="Password"
      minlength="4"
    />
    <!-- <p>The password must be > 4 char</p> -->

    <input type="submit" value="Signup" disabled>
    <input type="submit" value="Signup"/>
  </form>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script>
import axios from 'axios'
import router from'@/router'
import { mapActions } from 'vuex'

export default {
  data: () => {
    return {
      credentials: {
        name: '',
        email: '',
        password: ''
      }
    }
  },
  methods: {
    ...mapActions(['signup'])
  }
}

</script>

<style scoped src="./SignupForm.css"></style>
