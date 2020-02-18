!<template>
  <div class="howToUse">
      <div id="unsigned">
          <div class="TitleBox">
            <span id="TitleText">비회원 서비스</span>
          </div>
          <div id="DescriptionBox">
              <div class="Step">
                <span id="StepTitle">Step 1.</span><br/>
                <div id="StepBody">
                    <span id="StepDescription">
                        시작하기 버튼을 눌러 <br/>서비스를 이용하실 수 있습니다.</span><br/>
                    <img id="StepImage" src="@/assets/icons/howToUse/step1.png">
                </div>
              </div>
              <div class="Step">
                <span id="StepTitle">Step 2.</span><br/>
                <div id="StepBody">
                    <span id="StepDescription">
                        상권 변화 지표를 <br/>한눈에 확인합니다.</span><br/>
                    <img id=  "StepImage" src="@/assets/icons/howToUse/step2.png">
                    <span id="subDescription"><span id="subDescriptionTitle"><br/>상권 상태<br/></span><br/> 확장-파랑<br/> 급변-초록<br/> 정체-주황<br/>축소-빨강</span>
                </div>
              </div>
          </div>
      </div>
      <div id="signed">

      </div>
  </div>
</template>

<script>
import '@/components/howToUse/howToUse.scss'
export default {

}
</script>

<style>

</style>
