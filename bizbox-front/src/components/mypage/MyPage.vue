<template>
  <div class="mypage">
    <div class="res-margin"></div>
    <div class="biz-item-1">
      <h1>Change Settings</h1>
      <SettingBtn></SettingBtn>
    </div>
    <div class="biz-item-1">
      <h1>My Business</h1>
      <MyBiz></MyBiz>
    </div>
  </div>
</template>

<script>
import SettingBtn from './settingbtn/SettingBtn'
import MyBiz from './mybiz/MyBiz'
export default {
  components: {
    SettingBtn,
    MyBiz
  }
}
</script>

<style scoped src="./MyPage.css"></style>
