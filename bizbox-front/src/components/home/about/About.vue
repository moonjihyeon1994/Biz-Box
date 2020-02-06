<template>
  <div id="bz-container">
    <div id="bz-about1" v-scrollanimation>
      <div class="bz-imgBox" v-scrollanimation>
        <img src="@/assets/home_img1.png">
      </div>
      <div class="colorBox" v-scrollanimation></div>
      <div class="bz-aboutTitle" v-scrollanimation>
        믿을 수 있는 데이터
      </div>
      <div class="bz-aboutText" v-scrollanimation>
        대한민국 정부에서 제공하는 공공 데이터를 기반으로 만들었기에 믿을 수 있습니다.
      </div>
    </div>

    <div id="bz-about2" v-scrollanimation>
      <div class="bz-imgBox" v-scrollanimation>
        <img src="@/assets/home_img2.png">
      </div>
      <div class="colorBox" v-scrollanimation></div>
      <div class="bz-aboutTitle" v-scrollanimation>
      <span style="color:#ddd">방대</span>한 양의 상권 정보
      </div>
      <div class="bz-aboutText" v-scrollanimation>
        약 200만건에 해당하는 탄탄한 데이터를 바탕으로 만들어졌습니다. 더욱 자세하고 정확한 정보를 얻을 수 있습니다.
      </div>
    </div>

    <div id="bz-about3" v-scrollanimation>
      <div class="bz-imgBox" v-scrollanimation>
        <img src="@/assets/home_img3.png">
      </div>
      <div class="colorBox" v-scrollanimation></div>
      <div class="bz-aboutTitle" v-scrollanimation>
        분석하기 쉬운 디자인
      </div>
      <div class="bz-aboutText" v-scrollanimation>
        지도와 차트를 이용하여 누구라도 쉽게 분석할 수 있도록 구성했습니다.
      </div>
    </div>

  </div>
</template>

<script>
export default {

}
</script>

<style scoped src='./About.scss' lang='scss'></style>
