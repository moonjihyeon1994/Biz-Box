<template>
<div  class="a" >
    <input v-model="name" type="text" placeholder="검색어입력">
    <button  v-on:click="search">검색</button>
</div>
</template>

<script>
export default {
  data: () => ({
    name: ''
  }),
  methods: {
    search: function () {
      // eslint-disable-next-line no-unused-expressions
      this.$store.dispatch('ChangeCenter', name)
    }
  }
}
</script>

<style>
.a{
    height: 50px;
    width: 380px;
    background: white;
    border:1px solid coral;
}
input{
    font-size: 18px;
    width: 300px;
    float: left;
    border: 0px;
    outline: none;
}
button{
    color: white;
    width: 70px;
    height: 100%;
    background: coral;
    float: right;
    border: 0px;
    outline: none;
}
:-ms-input-placeholder{
    color:tomato;
}
</style>
