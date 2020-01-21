<template>
  <div id="app">
    <div id="nav">
      <router-link to="/" class="link1">Home</router-link>|
      <router-link to="/about" class="link1">About</router-link>|
      <router-link to="/result" class="link1">Result</router-link>
    </div>
    <router-view />
  </div>
</template>
<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #011627;
  background-color: #ECE6CD;
}
#nav {
  padding: 30px;
  background: #3A3F58;
}
#nav a {
  font-weight: bold;
  color: #FFFFFF;
}
#nav a.router-link-exact-active {
  color: #42B983;
}
.link-1 {
  transition: 0.3s ease;
  background: #3FA46A;
  color: #FFFFFF;
  font-size: 20px;
  text-decoration: none;
  border-top: 4px solid #3FA46A;
  border-bottom: 4px solid #3FA46A;
  padding: 20px 0;
  margin: 0 20px;
}
.link-1:hover {
  border-top: 4px solid #FFFFFF;
  border-bottom: 4px solid #FFFFFF;
  padding: 6px 0;
}
</style>