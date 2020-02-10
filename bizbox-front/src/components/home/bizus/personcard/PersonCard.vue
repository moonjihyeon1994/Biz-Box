<template>
  <div class="card">
    <div class='common front'>
      <img src="../../../../assets/images/logo/bizbox_White.png">
      <div class='fName'>{{ name }}</div>
      <div class='fRole'>{{ role }}</div>
    </div>
    <div class='common back'>
      <img :src="imgSrc">
      <ul>
        <li><a :href='git' target='_blank'><i class="fab fa-github"></i></a></li>
        <li><a :href='insta' target='_blank'><i class="fab fa-instagram"></i></a></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    name: String,
    role: String,
    git: String,
    insta: String,
    img: String
  },
  mounted () {
    this.imgSrc = require('@/assets/images/member/' + this.img)
  },
  data () {
    return {
      imgSrc: ''
    }
  }
}
</script>

<style scoped src='./PersonCard.scss' lang='scss'></style>
