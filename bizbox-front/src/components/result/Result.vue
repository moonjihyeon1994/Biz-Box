<template>
  <section class="hero is-fullheight">
    <div class="hero-body">
      <div class="container">
        <hr />
        <chart></chart>
      </div>
    </div>
  </section>
</template>

<script>
// @ is an alias to /src
import Chart from './Chart'

export default {
  name: 'result',
  components: {
    Chart
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home {
  display: flex;
  align-items: center;
  align-content: center;
  justify-content: center;
}
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
  text-decoration: underline;
}
</style>
