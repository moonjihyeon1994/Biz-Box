<template>
<div class="form-container">
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
  <button class="closeButton" @click="closeDialog">닫기</button>
</div>
</template>

<script>
import LoginForm from './loginform/LoginForm'
import SignupForm from './signupform/SignupForm'
import { mapActions } from 'vuex'
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
  },
  methods: {
    ...mapActions(['popupDialog', 'closeDialog'])
  }
}
</script>
<style scoped src="./TotalForm.css"></style>
