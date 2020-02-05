<template>
  <div id="loginForm" class="biz-container">
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

      <a id="signup" @click="signup">
        <button>SIGN UP</button>
      </a>

      <a id="kakao_btn" @click="requestKakao">
        <img src="@/assets/btn_kakao.png" alt="" />
      </a>
    </form>
  </div>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

<script>
import axios from 'axios'
import router from'../../router'
import { mapActions, mapGetters } from 'vuex'

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
      Kakao.init('0574c7ce26ff4134a0dc5f831d6edd37')
      Kakao.Auth.loginForm({
        success: (authObj) => {
          console.log(authObj)
          const refreshToken = authObj.refresh_token

          const getUrl = 'http://70.12.246.137:8080/kakao/login?refresh_token=' + refreshToken
          this.loginWithKakao(getUrl)
        },
        fail: function (err) {
          alert(JSON.stringify(err))
        },
        persistAccessToken: false
      })
    },
    signup() {
      router.push('/signup')
    }
  }
}
</script>

<style scoped src="./Login.css"></style>
