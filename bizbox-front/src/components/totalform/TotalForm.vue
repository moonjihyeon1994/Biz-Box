<template>
<div class="total-form">
  <v-tabs  grow v-model="tab">
    <v-tab v-for="form in formList" :key="form">
      <span>{{ form }}</span>
    </v-tab>
  </v-tabs>
  <v-tabs-items v-model="tab">
    <v-tab-item>
      <LoginForm></LoginForm>
    </v-tab-item>
    <v-tab-item>
      <SignupForm></SignupForm>
    </v-tab-item>
  </v-tabs-items>
</div>
</template>

<script>
import LoginForm from './loginform/LoginForm'
import SignupForm from './signupform/SignupForm'
export default {
  data: () => {
    return {
      tab: null,
      formList: [
        'LOG IN', 'SIGN UP'
      ]
    }
  },
  components: {
    LoginForm,
    SignupForm
  }
}
</script>
<style scoped src="./TotalForm.css"></style>
