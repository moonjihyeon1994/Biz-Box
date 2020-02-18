<template>
  <v-row class="mapcondition" justify="space-around">
      <v-sheet id="sheet"  class="mapbtns" elevation="14">
        <div class="btnRow">
          <div class='mapBtn' @click="choice1()">
            <div v-show='choose1'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose1'>
              <img src="@/assets/icons/mapIcon2/circle.png">
            </div>
            <span class='mapBtnText'>원으로 검색</span>
          </div>
          <div class='mapBtn' @click="choice2()">
            <div v-show='choose2'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose2'>
              <img src="@/assets/icons/mapIcon2/yellowpeople.png">
            </div>
            <span class='mapBtnText'>유동인구(시간)</span>
          </div>

          <div class='mapBtn' @click="choice3()">
            <div v-show='choose3'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose3'>
              <img src="@/assets/icons/mapIcon2/report.png">
            </div>
            <span class='mapBtnText'>상권변화지수</span>
          </div>
          <div class='mapBtn' @click="choice4()">
            <div v-show='choose4'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose4'>
              <img src="@/assets/icons/mapIcon2/greengraph.png">
            </div>
            <span class='mapBtnText'>매출정보 (연령)</span>
          </div>
        </div>

        <div class="btnRow">
          <div class='mapBtn' @click="choice5()">
            <div v-show='choose5'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose5'>
              <img src="@/assets/icons/mapIcon2/redpeople.png">
            </div>
            <span class='mapBtnText'>유동인구(연령)</span>
          </div>

          <div class='mapBtn' @click="choice6()">
            <div v-show='choose6'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose6'>
              <img src="@/assets/icons/mapIcon2/bluepeople.png">
            </div>
            <span class='mapBtnText'>유동인구(요일)</span>
          </div>

          <div class='mapBtn' @click="choice7()">
            <div v-show='choose7'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose7'>
              <img src="@/assets/icons/mapIcon2/redgraph.png">
            </div>
            <span class='mapBtnText'>매출정보 (시간)</span>
          </div>

          <div class='mapBtn' @click="choice8()">
            <div v-show='choose8'>
              <img src="@/assets/icons/mapIcon2/check.png">
            </div>
            <div v-show='!choose8'>
              <img src="@/assets/icons/mapIcon2/bluegraph.png">
            </div>
            <span class='mapBtnText'>매출정보 (요일)</span>
          </div>
        </div>
      </v-sheet>
    </v-row>
</template>

<script>
export default {
  data: () => ({
    selected: 0,
    choose1: false,
    choose2: false,
    choose3: false,
    choose4: false,
    choose5: false,
    choose6: false,
    choose7: false,
    choose8: false
  }),
  methods: {
    choice1: function (event) {
      if (this.selected === 1) this.selected = 0
      else {
        this.selected = 1
        this.setFalse()
        this.choose1 = true
        // alert('clicked : ' + this.selected)
        this.$store.dispatch('ChangeMode', { mode: 1 })
      }
    },
    choice2: function (event) {
      this.selected = 2
      this.setFalse()
      this.choose2 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 2 })
      this.$emit('myevent')
    },
    choice3: function (event) {
      this.selected = 3
      this.setFalse()
      this.choose3 = true
      // alert('clicked : ' + this.selected)
      // alert("mode : "+ this.$store.state.mode)
      this.$store.dispatch('ChangeMode', { mode: 3 })
      this.$emit('myevent')
    },
    choice4: function (event) {
      this.selected = 4
      this.setFalse()
      this.choose4 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 4 })
      this.$emit('myevent')
    },
    choice5: function (event) {
      this.selected = 5
      this.setFalse()
      this.choose5 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 5 })
      this.$emit('myevent')
    },
    choice6: function (event) {
      this.selected = 6
      this.setFalse()
      this.choose6 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 6 })
      this.$emit('myevent')
    },
    choice7: function (event) {
      this.selected = 7
      this.setFalse()
      this.choose7 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 7 })
      this.$emit('myevent')
    },
    choice8: function (event) {
      this.selected = 8
      this.setFalse()
      this.choose8 = true
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 8 })
      this.$emit('myevent')
    },
    setFalse: function () {
      this.choose1 = false
      this.choose2 = false
      this.choose3 = false
      this.choose4 = false
      this.choose5 = false
      this.choose6 = false
      this.choose7 = false
      this.choose8 = false
    }
  }
}
</script>

<style scoped lang="scss">
.mapcondition {
  z-index: 2;
  position: fixed;
  top: 180px;
  left: 64px;
}
#sheet {
  width: 370px;
  height: 180px;
  background-color: white;
  border-radius: 3px;
  position: fixed;
  top: 170px;
  left: 49px;
}
.mapbtns{
  display: flex;
  z-index: 3;
  align-items: center;

  .mapRow {
    display: flex;
    justify-content: space-around;
  }

  .mapBtn {
    top: 10px;
    // border: 1px solid black;
    position: relative;
    width: 90px;
    height: 75px;
    display: inline-block;
    img {
      display: block;
      width: 40px;
      height: 40px;
      margin: auto;
    }

    .mapBtnText {
      display: block;
      font-size: 12px;
      width: 100%;
      text-align: center;
    }

    &:hover {
      cursor: pointer;
    }
  }
}
</style>
