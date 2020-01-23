<template>
  <div id="loginForm">
    <form action="">
      <input type="text" name="username" placeholder="Username" required />
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
        placeholder="Password"
        required
        minlength="4"
      />
      <p>The password must be > 4 char</p>
      <!-- You also can use only one input and use "pointer-events: none;", but you still can select it using tab -->

      <input type="submit" value="Login" disabled />
      <input type="submit" value="Login" />
      <a href="">
        <button id="googlebutton">Google Login</button>
      </a>
      <a href="">
        <button id="kakaobutton">Kakao Login</button>
      </a>
    </form>
  </div>
</template>

<script>

</script>

<style>
#loginForm {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: sans-serif;
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
  margin-bottom: 15px;
  padding: 10px;
  box-sizing: border-box;
  font-size: 15px;
  border: 1px solid #bbb;
  border-radius: 3px;
}
a { text-decoration:none }
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
  margin-bottom: 15px;
  padding: 10px;
  box-sizing: border-box;
  font-size: 15px;
  border: 1px solid #bbb;
  border-radius: 3px;
  border: none;
  /* background-color: #8bc34a; */
  color: #fff;
}

#googlebutton {
  background-color: #ee4035
}

#kakaobutton {
  background-color: #ffcc5c
}

</style>
