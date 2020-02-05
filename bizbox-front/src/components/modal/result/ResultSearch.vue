<template>
  <div id="container">
    <div id="selBoxes">
      <select id="sel1">
        <option value="title">시/도</option>
        <option value="seoul">서울</option>
        <option value="incheon">인천</option>
        <option value="gyeonggi">경기</option>
      </select>

      <select id="sel2">
        <option value="title">구/군</option>
        <option value="gangnam">강남구</option>
        <option value="mapo">마포구</option>
      </select>

      <select id="sel3">
        <option value="title">업종</option>
        <option value="cafe">카페</option>
        <option value="karaoke">노래방</option>
        <option value="chicken">치킨</option>
      </select>
    </div>

    <input id="searchBox" placeholder="도로명 주소를 입력해주세요.">

    <div id="btns">
      <button id="resetBtn">초기화</button>
      <button id="searchBtn">검색</button>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {

    }
  }
}
</script>

<style scoped src="./ResultSearch.scss" lang="scss"></style>
