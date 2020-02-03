<template>
  <div id="loginForm" class="biz-container">
    <form action="" v-on:submit.prevent>
      <!-- <input type="text" name="username" placeholder="Username" required /> -->
      <input v-model="email"
        type="email"
        name="email"
        placeholder="Email"
        required
        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
      />
      <input v-model="password"
        type="password"
        name="password"
        required
        placeholder="Password"
        minlength="4"
      />
      <!-- <p>The password must be > 4 char</p> -->

      <!-- You also can use only one input and use "pointer-events: none;", but you still can select it using tab -->
      <input type="submit" value="Login" disabled>
      <input type="submit" value="Login" @click="requestLogin">
      <!-- <a id="login_btn" @click="requestLogin">
        <button>Login</button>
      </a> -->

      <a id="signup" @click="signup">
        <button>SIGN UP</button>
      </a>
      <a id="kakao_btn" @click="loginWithKakao" :disabled="loading">
        <img src="@/assets/btn_kakao.png" alt="" />
      </a>
    </form>
    <!-- <a @click="getInfo">
      <button>get infomation, check console</button>
    </a> -->
  </div>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

<script>
import axios from 'axios'
import router from'../../router'
const storage = window.sessionStorage
// const ai = axios.create({
//   baseURL: 'http://70.12.247.78:8080/user/'
// })

export default {
  name: 'login',
  data: () => {
    return {
      email: '',
      password: '',
      loading: false
    }
  },
  methods: {
    loginWithKakao () {
      console.log("Kakao login button clicked")

      Kakao.init('0574c7ce26ff4134a0dc5f831d6edd37');
      Kakao.Auth.login({
        success: function(authObj) {
          this.loading = true;
          // const accessToken = authObj.access_token;
          const refreshToken = authObj.refresh_token;
          const getUrl = 'http://70.12.246.137:8080/kakao/login?refresh_token=' + refreshToken
          console.log('Login success')

          // storage.setItem('jwt-auth-token', '')
          // storage.setItem('login_user_name', '')
          // storage.setItem('login_user_email', '')

          axios.get(getUrl)
            .then(res => {
              // console.log(res.data)
              if (res.data.status) {
                storage.setItem('jwt-auth-token', res.headers['jwt-auth-token'])
                storage.setItem('login_user_name', res.data.data.name)
                storage.setItem('login_user_email', res.data.data.email)
                console.log(storage)
              }
            })
            .finally(() => {
              this.loading = false;
              router.push('/')
            })
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    },
    signup() {
      router.push('/signup')
    },
    requestLogin() {
      const postUrl = 'http://70.12.246.137:8080/user/login'
      axios.post(postUrl, {
        email: this.email,
        pw: this.password
      }).then(res => {
        console.log(res.data.status)
        if (res.data.status === true) {
          // console.log(res)
          storage.setItem('jwt-auth-token', res.headers['jwt-auth-token'])
          storage.setItem('login_user_name', '')
          storage.setItem('login_user_email', res.data.data.email)
          console.log(storage)
          router.push('/')
        } else {
          alert('email 이나 password 를 확인하세요')
        }
      })
    }
    // getInfo() {
    //   ai.post(
    //     '/info',
    //     {
    //       email: storage.getItem('login_user_email')
    //     },
    //     {
    //       headers: {
    //         'jwt-auth-token': storage.getItem('jwt-auth-token')
    //       }
    //     }
    //   ).then(res => {
    //     console.log(res)
    //   })
    // }
  }
}
</script>

<style scoped src="./Login.css"></style>
