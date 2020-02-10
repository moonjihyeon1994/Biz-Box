<template>
  <div id="bz-container">
    <div id='bizus-title'>Bizbox Member</div>

    <div id="row-container">
      <div class='bz-row'>
        <person-card name="음영현" role="Backend Developer" img="eyh.jpg" git='https://github.com/munyohan/MoonNote'></person-card>
        <person-card name="오승완" role="Backend Developer" img="osw.jpg"></person-card>
        <person-card name="문지현" role="Backend Developer" img="mjh.jpg"></person-card>
      </div>

      <div class='bz-row'>
       <person-card name="양희철" role="Frontend Developer" img="yhc.jpg"></person-card>
        <person-card name="김재현" role="Frontend Developer" img="kjh.jpg"></person-card>
        <person-card name="문요한" role="Frontend Developer" img="myh.jpg"></person-card>
      </div>
    </div>
  </div>

</template>

<script>
import PersonCard from './personcard/PersonCard'

export default {
  components: {
    PersonCard
  },
  data () {
    return {
      names: ['문요한']
    }
  }
}
</script>

<style scoped src="./BizUs.scss" lang="scss"></style>
