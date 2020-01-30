<template>
  <div id="loginForm">
    <form action="" v-on:submit.prevent>
      <!-- <input type="text" name="username" placeholder="Username" required /> -->
      <input
        type="email"
        name="email"
        placeholder="Email"
        required
        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
      />
      <input
        type="password"
        name="password"
        required
        placeholder="Password"
        minlength="4"
      />
      <p>The password must be > 4 char</p>
      <!-- You also can use only one input and use "pointer-events: none;", but you still can select it using tab -->

      <input type="submit" value="Login" disabled />
      <input type="submit" value="Login" />
      <a id="signup" href="">
        <button>SIGN UP</button>
      </a>
      <a id="kakao_btn" @click="loginWithKakao">
        <img src="@/assets/btn_kakao.png" alt="" />
      </a>
    </form>
    <a @click="getInfo">
      <button>get infomation, check console</button>
    </a>
  </div>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>
import axios from 'axios'
const storage = window.sessionStorage
const ai = axios.create({
  baseURL: 'http://70.12.247.78:8080/user/'
})

export default {
  name: 'login',
  data: () => {
    return {
      mydata: '',
      token: ''
    }
  },
  methods: {
    loginWithKakao () {
      console.log("Kakao login button clicked")
      Kakao.init('0574c7ce26ff4134a0dc5f831d6edd37');
      Kakao.Auth.login({
        success: function(authObj) {
          // const accessToken = authObj.access_token;
          const refreshToken = authObj.refresh_token;
          const getUrl = 'http://70.12.247.78:8080/kakao/login?refresh_token=' + refreshToken
          console.log('Login success')

          storage.setItem('jwt-auth-token', '')
          storage.setItem('login_user_name', '')
          storage.setItem('login_user_email', '')
          console.log(storage)

          axios.get(getUrl)
            .then(res => {
              console.log(res.data)
              if (res.data.status) {
                storage.setItem('jwt-auth-token', res.headers['jwt-auth-token'])
                storage.setItem('login_user_name', res.data.name)
                storage.setItem('login_user_email', res.data.email)
                console.log(storage)
              }
              // router.push('/')
            })
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    },
    getInfo() {
      ai.post(
        '/info',
        {
          email: storage.getItem('login_user_email')
        },
        {
          headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token')
          }
        }
      ).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style>
#loginForm {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: sans-serif;
}

#loginForm form input {
  height: 49px;
}

form {
  width: 300px;
  padding: 39px;
  box-sizing: border-box;
  border-radius: 3px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
input {
  display: block;
  width: 100%;
  margin-bottom: 5px;
  padding: 10px;
  box-sizing: border-box;
  font-size: 15px;
  border: 1px solid #bbb;
  border-radius: 3px;
}
a {
  text-decoration: none;
}
p {
  display: none;
  font-size: 12px;
  margin: 0;
  transform: translateY(-10px);
  color: #666;
}
input:focus + p {
  display: block;
}

input[type='submit'] {
  border: none;
  background-color: #8bc34a;
  color: #fff;
  display: none;
}
input[type='submit']:disabled {
  background-color: #cbdadb;
  display: block;
  opacity: 0.9;
}
input:valid ~ input:valid ~ input:valid ~ input[type='submit']:disabled {
  display: none;
}
input:valid ~ input:valid ~ input:valid ~ input[type='submit']:not(:disabled) {
  display: block;
  cursor: pointer;
}

button {
  display: block;
  width: 100%;
  margin-bottom: 5px;
  padding: 10px;
  box-sizing: border-box;
  font-size: 15px;
  border: 1px solid #bbb;
  border-radius: 3px;
  border: none;
  background-color: #8bc34a;
  color: #fff;
}

#google_btn {
  /* background-color: #5135ee; */
  height: 49px;
  width: 222px;
}
</style>
