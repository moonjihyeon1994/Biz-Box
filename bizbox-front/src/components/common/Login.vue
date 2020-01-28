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
      <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark">
        <img src="@/assets/btn_google_dark.png" alt="" />
      </div>
      <!-- <a id="google_btn" href="">
        <img src="@/assets/btn_google_dark.png" alt="" />
      </a> -->
      <!-- <a id="kakao_btn" href="https://kauth.kakao.com/oauth/authorize?client_id=64c7963937495c25ab3d30bc9f6e65e7&redirect_uri=http://70.12.246.137:8080/kakao/login&response_type=code">
        <img src="@/assets/btn_kakao.png" alt="" />
      </a> -->
      <!-- <button @click="loginWithKakao">
        <img src="@/assets/btn_kakao.png" alt="" />
      </button> -->
    </form>
    <a id="kakao-login-btn" @click="loginWithKakao">
      <img src="@/assets/btn_kakao.png" alt="" />
    </a>
  </div>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>
import axios from 'axios'

export default {
  name: 'login_test',
  data: () => {
    return {
      res: ''
    }
  },
  methods: {
    loginWithKakao () {
      // console.log('asdf')
      // axios.get('https://kauth.kakao.com/oauth/authorize?client_id=64c7963937495c25ab3d30bc9f6e65e7&redirect_uri=http://70.12.246.137:8080/kakao/login&response_type=code', (req, res) => {
      //   res.header('Access-Control-Allow-Origin', '*')
      // })
      //   .then(response => {
      //     // response.header('Access-Control-Allow-Origin', '*')
      //     // console.log(this.response)
      //     console.log(response.data)
      //   })
      //   .catch(e => {
      //     console.log('error: ', e)
      //   })
      console.log("click")
      Kakao.init('0574c7ce26ff4134a0dc5f831d6edd37');
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
          const accessToken = authObj.access_token;
          // console.log(accessToken)
          // alert(JSON.stringify(authObj));
          const geturl = 'http://70.12.246.137:8080/kakao/login?code=' + accessToken
          console.log(geturl)
          axios.get(geturl)
            .then(res => {
              console.log(res)
            })
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    },
    onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
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
  /* background-color: #8bc34a; */
  color: #fff;
}

#google_btn {
  /* background-color: #5135ee; */
  height: 49px;
  width: 222px;
}
</style>
