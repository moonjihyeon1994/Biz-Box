<template>
  <form action="" @submit.prevent="login(credentials)">
    <input v-model="credentials.email"
      type="email"
      name="email"
      placeholder="Email"
      required
      pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
    />
    <input v-model="credentials.pw"
      type="password"
      name="pw"
      required
      placeholder="Password"
      minlength="4"
    />
    <!-- <p>The password must be > 4 char</p> -->

    <!-- You also can use only one input and use "pointer-events: none;", but you still can select it using tab -->
    <input type="submit" value="Login" disabled>
    <input type="submit" value="Login">

    <a id="kakao_btn" @click="requestKakao">
      <img src="@/assets/btn_kakao.png" alt="" />
    </a>
  </form>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>
import router from'@/router'
import { mapActions } from 'vuex'

export default {
  name: 'login',
  data: () => {
    return {
      credentials: {
        email: '',
        pw: ''
      }
    }
  },
  methods: {
    ...mapActions(['login', 'loginWithKakao']),
    requestKakao () {
      Kakao.Auth.loginForm({
        success: (authObj) => {
          const refreshToken = authObj.refresh_token
          const getUrl = 'http://localhost:8080/kakao/login?refresh_token=' + refreshToken
          this.loginWithKakao(getUrl)
        },
        fail: function (err) {
          alert(JSON.stringify(err))
        },
        persistAccessToken: false
      })
    }
  }
}
</script>

<style scoped src="./LoginForm.css"></style>
