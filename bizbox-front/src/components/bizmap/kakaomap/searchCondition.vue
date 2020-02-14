<template>
  <v-row class="mapcondition" justify="space-around">
      <v-sheet id="sheet"  class="mapbtns" elevation="14">
        <div class="btnRow">
          <div class='mapBtn' @click="choice1()">
            <img src="@/assets/icons/mapIcon2/circle.png">
            <span class='mapBtnText'>원으로 검색</span>
          </div>

          <div class='mapBtn' @click="choice2()">
            <img src="@/assets/icons/mapIcon2/greenpeople.png">
            <span class='mapBtnText'>유동인구(시간)</span>
          </div>

          <div class='mapBtn' @click="choice3()">
            <img src="@/assets/icons/mapIcon2/report.png">
            <span class='mapBtnText'>상권변화지수</span>
          </div>
          <div class='mapBtn' @click="choice4()">
            <img src="@/assets/icons/mapIcon2/greygraph.png">
            <span class='mapBtnText'>매출정보 (연령)</span>
          </div>
        </div>

        <div class="btnRow">
          <div class='mapBtn' @click="choice5()">
            <img src="@/assets/icons/mapIcon2/redpeople.png">
            <span class='mapBtnText'>유동인구(연령)</span>
          </div>

          <div class='mapBtn' @click="choice6()">
            <img src="@/assets/icons/mapIcon2/bluepeople.png">
            <span class='mapBtnText'>유동인구(요일)</span>
          </div>

          <div class='mapBtn' @click="choice7()">
            <img src="@/assets/icons/mapIcon2/yellowgraph.png">
            <span class='mapBtnText'>매출정보 (시간)</span>
          </div>

          <div class='mapBtn' @click="choice8()">
            <img src="@/assets/icons/mapIcon2/bluegraph.png">
            <span class='mapBtnText'>매출정보 (요일)</span>
          </div>
        </div>
      </v-sheet>
    </v-row>
</template>

<script>
export default {
  data: () => ({
    selected: 0
  }),
  methods: {
    choice1: function (event) {
      this.selected = this.$store.state.mode
      if (this.selected === 1) {
        this.selected = 0; alert(this.selected)
      } else {
        this.selected = 1
        alert(this.selected)
      }
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: this.selected })
    },
    choice2: function (event) {
      this.selected = 2
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 2 })
      this.$emit('myevent')
    },
    choice3: function (event) {
      this.selected = 3
      // alert('clicked : ' + this.selected)
      // alert("mode : "+ this.$store.state.mode)
      this.$store.dispatch('ChangeMode', { mode: 3 })
      this.$emit('myevent')
    },
    choice4: function (event) {
      this.selected = 4
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 4 })
      this.$emit('myevent')
    },
    choice5: function (event) {
      this.selected = 5
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 5 })
      this.$emit('myevent')
    },
    choice6: function (event) {
      this.selected = 6
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 6 })
      this.$emit('myevent')
    },
    choice7: function (event) {
      this.selected = 7
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 7 })
      this.$emit('myevent')
    },
    choice8: function (event) {
      this.selected = 8
      // alert('clicked : ' + this.selected)
      this.$store.dispatch('ChangeMode', { mode: 8 })
      this.$emit('myevent')
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
  width: 360px;
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
