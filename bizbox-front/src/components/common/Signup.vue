<template>
  <div id="signupForm">
    <form action="" v-on:submit.prevent>
      <input type="text" v-model="name"
        name="name"
        required
        placeholder="username"
        minlength="4"
      />
      <input
        type="email" v-model="email"
        name="email"
        placeholder="Email"
        required
        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
      />
      <input
        type="password" v-model="password"
        name="password"
        required
        placeholder="Password"
        minlength="4"
      />
      <!-- <p>The password must be > 4 char</p> -->
      <!-- <input
        type="password"
        name="confirmpassword"
        required
        placeholder="Confirm Password"
        minlength="4"
      /> -->
      <!-- <p id="are_same">여기서 password confirm check</p> -->
      <input type="submit" value="Signup" @click="requestSignup"/>
      <!-- <button type="submit">Sign up</button> -->
    </form>
  </div>
</template>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

<script>
import axios from 'axios'
import router from'../../router'

export default {
  name: 'signup_here',
  data: () => {
    return {
      name: '',
      email: '',
      password: ''
    }
  },
  methods: {
    requestSignup() {
      const postUrl = 'http://70.12.246.137:8080/user/signup'
      axios.post(postUrl, {
        name: this.name,
        email: this.email,
        pw: this.password
      }).then(res => {
        console.log(res)
        router.push('/login')
      })
    }
  }

}

</script>

<style>
#signupForm {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: sans-serif;
}

#signupForm form input {
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
