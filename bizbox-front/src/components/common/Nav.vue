<template>
  <nav class="main-nav">
    <div class="main-nav__inner">
      <router-link to="/" class="main-nav__logo">
        <h1>BizBox</h1>
      </router-link>

      <input
        class="main-nav__mobile-checkbox"
        id="mobile-checkbox"
        type="checkbox"
      />

      <label class="main-nav__mobile-toggle" for="mobile-checkbox">
        <span></span>
        <span></span>
        <span></span>
      </label>

      <ul class="main-nav__menu">
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="About">About</router-link></li>
        <li><router-link to="Result">Result</router-link></li>
        <li><router-link to="Login">Login</router-link></li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {}
</script>

<style scoped src="@/components/common/Nav.css"></style>
