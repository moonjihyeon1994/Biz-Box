<template>
  <div>
    <h1>{{ sgName }}</h1>
    <h2>성장성: {{ score.성장성.점수 }}</h2>
    <h2>안정성: {{ score.안정성.점수 }}</h2>
    <h2>영업력: {{ score.영업력.점수 }}</h2>
    <h2>구매력: {{ score.구매력.점수 }}</h2>
    <h2>집객력: {{ score.집객력.점수 }}</h2>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      sgCode: '',
      sgName: '',
      dong: '진관동',
      key: this.$store.state.modalsearch,
      sales_2018: {
        'q1': 0,
        'q2': 0,
        'q3': 0,
        'q4': 0,
        'n1': 0,
        'n2': 0,
        'n3': 0,
        'n4': 0,
        'selng_co': 0,
        'classification': {
          'CS100001': { 'name': '한국음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '카페', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300001': { 'name': '슈퍼마켓', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300002': { 'name': '편의점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300007': { 'name': '의류점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300008': { 'name': '패션용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300010': { 'name': '서적·문구', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300011': { 'name': '화장품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300012': { 'name': '오락·운동', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300013': { 'name': '섬유제품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300016': { 'name': '주방·가정용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100010': { 'name': '호프·간이주점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200013': { 'name': '세탁소', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200014': { 'name': '스포츠클럽', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200017': { 'name': '네일숍', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300004': { 'name': '핸드폰', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300015': { 'name': '가구·가전', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300009': { 'name': '의약·의료용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300017': { 'name': '통신판매업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200003': { 'name': '예체능학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200012': { 'name': '당구장', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300005': { 'name': '식료품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300006': { 'name': '건강보조식품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300014': { 'name': '화초·애완', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100007': { 'name': '치킨전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200004': { 'name': '치과의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200006': { 'name': '일반의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200007': { 'name': '가전제품수리', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200011': { 'name': 'PC방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200002': { 'name': '외국어학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200015': { 'name': '자동차수리·세차', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200018': { 'name': '피부관리실', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200010': { 'name': '노래방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200008': { 'name': '부동산중개업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300003': { 'name': '컴퓨터·주변기기', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        }
      },
      sales_2017: {
        'q1': 0,
        'q2': 0,
        'q3': 0,
        'q4': 0,
        'n1': 0,
        'n2': 0,
        'n3': 0,
        'n4': 0,
        'selng_co': 0,
        'classification': {
          'CS100001': { 'name': '한국음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '카페', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300001': { 'name': '슈퍼마켓', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300002': { 'name': '편의점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300007': { 'name': '의류점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300008': { 'name': '패션용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300010': { 'name': '서적·문구', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300011': { 'name': '화장품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300012': { 'name': '오락·운동', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300013': { 'name': '섬유제품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300016': { 'name': '주방·가정용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100010': { 'name': '호프·간이주점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200013': { 'name': '세탁소', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200014': { 'name': '스포츠클럽', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200017': { 'name': '네일숍', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300004': { 'name': '핸드폰', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300015': { 'name': '가구·가전', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300009': { 'name': '의약·의료용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300017': { 'name': '통신판매업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200003': { 'name': '예체능학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200012': { 'name': '당구장', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300005': { 'name': '식료품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300006': { 'name': '건강보조식품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300014': { 'name': '화초·애완', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100007': { 'name': '치킨전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200004': { 'name': '치과의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200006': { 'name': '일반의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200007': { 'name': '가전제품수리', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200011': { 'name': 'PC방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200002': { 'name': '외국어학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200015': { 'name': '자동차수리·세차', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200018': { 'name': '피부관리실', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200010': { 'name': '노래방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200008': { 'name': '부동산중개업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300003': { 'name': '컴퓨터·주변기기', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        }
      },
      sales_2016: {
        'q1': 0,
        'q2': 0,
        'q3': 0,
        'q4': 0,
        'n1': 0,
        'n2': 0,
        'n3': 0,
        'n4': 0,
        'selng_co': 0,
        'classification': {
          'CS100001': { 'name': '한국음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '카페', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300001': { 'name': '슈퍼마켓', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300002': { 'name': '편의점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300007': { 'name': '의류점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300008': { 'name': '패션용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300010': { 'name': '서적·문구', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300011': { 'name': '화장품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300012': { 'name': '오락·운동', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300013': { 'name': '섬유제품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300016': { 'name': '주방·가정용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100010': { 'name': '호프·간이주점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200013': { 'name': '세탁소', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200014': { 'name': '스포츠클럽', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200017': { 'name': '네일숍', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300004': { 'name': '핸드폰', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300015': { 'name': '가구·가전', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300009': { 'name': '의약·의료용품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300017': { 'name': '통신판매업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200003': { 'name': '예체능학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200012': { 'name': '당구장', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300005': { 'name': '식료품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300006': { 'name': '건강보조식품', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300014': { 'name': '화초·애완', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100007': { 'name': '치킨전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200004': { 'name': '치과의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200006': { 'name': '일반의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200007': { 'name': '가전제품수리', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200011': { 'name': 'PC방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200002': { 'name': '외국어학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200015': { 'name': '자동차수리·세차', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200018': { 'name': '피부관리실', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200010': { 'name': '노래방', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200008': { 'name': '부동산중개업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS300003': { 'name': '컴퓨터·주변기기', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        }
      },
      sales_change_2018: { 'c1': 0, 'c2': 0, 'c3': 0, 'c4': 0 },
      sales_change_2017: { 'c1': 0, 'c2': 0, 'c3': 0, 'c4': 0 },
      growth_rate: {
        'average_rate': 0
      },
      years: {
        continuousYears: 0,
        avgSeoul: 0
      },
      populations: {
        floating: 0,
        living: 0,
        working: 0
      },
      closure_rate: {
        average: 0
      },
      score: {
        '성장성': {
          '점수': 0,
          '매출증감률': 0,
          '예상성장률': 0
        },
        '안정성': {
          '점수': 0,
          '점포변동률': 0,
          '매출변동률': 0,
          '운영연수': 0,
          '폐업률': 0
        },
        '영업력': {
          '점수': 0,
          '공급대비수요': 0,
          '분기별매출편차': 0,
          '요일별매출편차': 0
        },
        '구매력': {
          '점수': 0,
          '상권매출규모': 0,
          '건당결제금액': 0,
          '소비수준': 0
        },
        '집객력': {
          '점수': 0,
          '유동인구': 0,
          '주거인구': 0,
          '직장인구': 0
        }
      }
    }
  },
  created () {
    // 업종 및 분기별 매출 + 점포 수
    let vm = this
    let requestSalesUrl = 'http://70.12.246.137:8080//predict/findBusiness/' + this.$store.state.Coords.lng + '/' + this.$store.state.Coords.lat + '/'
    axios.get(requestSalesUrl)
      .then(res => {
        let data2018 = res.data['2018']
        let data2017 = res.data['2017']
        let data2016 = res.data['2016']

        vm.sgCode = data2018[0].trdar_cd
        vm.sgName = data2018[0].trdar_cd_nm

        for (let index = 0; index < data2018.length; index++) {
          let temp2018 = data2018[index]
          let serviceCode2018 = temp2018.svc_induty_cd // 업종 코드

          vm.selng_co += Number(temp2018.thsmon_selng_co)

          if (temp2018.stdr_qu_cd === '1') {
            vm.sales_2018.q1 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.n1 += Number(temp2018.stor_co)
            vm.sales_2018.classification[serviceCode2018].q1 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.classification[serviceCode2018].n1 += Number(temp2018.stor_co)
          } else if (temp2018.stdr_qu_cd === '2') {
            vm.sales_2018.q2 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.n2 += Number(temp2018.stor_co)
            vm.sales_2018.classification[serviceCode2018].q2 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.classification[serviceCode2018].n2 += Number(temp2018.stor_co)
          } else if (temp2018.stdr_qu_cd === '3') {
            vm.sales_2018.q3 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.n3 += Number(temp2018.stor_co)
            vm.sales_2018.classification[serviceCode2018].q3 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.classification[serviceCode2018].n3 += Number(temp2018.stor_co)
          } else {
            vm.sales_2018.q4 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.n4 += Number(temp2018.stor_co)
            vm.sales_2018.classification[serviceCode2018].q4 += Number(temp2018.thsmon_selng_amt)
            vm.sales_2018.classification[serviceCode2018].n4 += Number(temp2018.stor_co)
          }
        }

        for (let index = 0; index < data2017.length; index++) {
          let temp2017 = data2017[index]
          let serviceCode2017 = temp2017.svc_induty_cd

          vm.selng_co += Number(temp2017.thsmon_selng_co)

          if (temp2017.stdr_qu_cd === '1') {
            vm.sales_2017.q1 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.n1 += Number(temp2017.stor_co)
            vm.sales_2017.classification[serviceCode2017].q1 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.classification[serviceCode2017].n1 += Number(temp2017.stor_co)
          } else if (temp2017.stdr_qu_cd === '2') {
            vm.sales_2017.q2 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.n2 += Number(temp2017.stor_co)
            vm.sales_2017.classification[serviceCode2017].q2 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.classification[serviceCode2017].n2 += Number(temp2017.stor_co)
          } else if (temp2017.stdr_qu_cd === '3') {
            vm.sales_2017.q3 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.n3 += Number(temp2017.stor_co)
            vm.sales_2017.classification[serviceCode2017].q3 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.classification[serviceCode2017].n3 += Number(temp2017.stor_co)
          } else {
            vm.sales_2017.q4 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.n4 += Number(temp2017.stor_co)
            vm.sales_2017.classification[serviceCode2017].q4 += Number(temp2017.thsmon_selng_amt)
            vm.sales_2017.classification[serviceCode2017].n4 += Number(temp2017.stor_co)
          }
        }

        for (let index = 0; index < data2016.length; index++) {
          let temp2016 = data2016[index]
          let serviceCode2016 = temp2016.svc_induty_cd

          vm.selng_co += Number(temp2018.thsmon_selng_co)

          if (temp2016.stdr_qu_cd === '1') {
            vm.sales_2016.q1 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.n1 += Number(temp2016.stor_co)
            vm.sales_2016.classification[serviceCode2016].q1 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.classification[serviceCode2016].n1 += Number(temp2016.stor_co)
          } else if (temp2016.stdr_qu_cd === '2') {
            vm.sales_2016.q2 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.n2 += Number(temp2016.stor_co)
            vm.sales_2016.classification[serviceCode2016].q2 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.classification[serviceCode2016].n2 += Number(temp2016.stor_co)
          } else if (temp2016.stdr_qu_cd === '3') {
            vm.sales_2016.q3 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.n3 += Number(temp2016.stor_co)
            vm.sales_2016.classification[serviceCode2016].q3 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.classification[serviceCode2016].n3 += Number(temp2016.stor_co)
          } else {
            vm.sales_2016.q4 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.n4 += Number(temp2016.stor_co)
            vm.sales_2016.classification[serviceCode2016].q4 += Number(temp2016.thsmon_selng_amt)
            vm.sales_2016.classification[serviceCode2016].n4 += Number(temp2016.stor_co)
          }
        }
        let resquestHistoryUrl = 'http://70.12.247.78:8080/change/getHistory/' + vm.dong
        axios.get(resquestHistoryUrl).then(res => {
          vm.years.continuousYears = Number(res.data.cblist[5].g)
          vm.years.avgSeoul = Number(res.data.cblist[5].i)
        })
        let requestFloatingPopulationUrl = 'http://70.12.247.78:8080/population/getPopulationByDong/' + vm.dong
        axios.get(requestFloatingPopulationUrl).then(res => {
          console.log(res.data)
          // vm.populations.floating =
        })

        // let requestPopulationUrl = 'http://70.12.247.78:8080/' + vm.sgCode
        // axios.get(requestPopulationUrl).then(res => {
        //   console.log(res.data)
        // })
      })
      .then(() => {
        console.log(typeof vm.sales_2018.q1)
        // 안정성 > 점포 변동률
        let dn1 = Number((Math.abs(vm.sales_2018.n4 - vm.sales_2018.n3) / vm.sales_2018.n3).toFixed(2))
        let dn2 = Number((Math.abs(vm.sales_2018.n3 - vm.sales_2018.n2) / vm.sales_2018.n2).toFixed(2))
        let dn3 = Number((Math.abs(vm.sales_2018.n2 - vm.sales_2018.n1) / vm.sales_2018.n1).toFixed(2))
        let dn4 = Number((Math.abs(vm.sales_2018.n1 - vm.sales_2017.n4) / vm.sales_2017.n4).toFixed(2))
        let dn5 = Number((Math.abs(vm.sales_2017.n4 - vm.sales_2017.n3) / vm.sales_2017.n3).toFixed(2))
        let dn6 = Number((Math.abs(vm.sales_2017.n3 - vm.sales_2017.n2) / vm.sales_2017.n2).toFixed(2))
        let dn7 = Number((Math.abs(vm.sales_2017.n2 - vm.sales_2017.n1) / vm.sales_2017.n1).toFixed(2))
        let dn8 = Number((Math.abs(vm.sales_2017.n1 - vm.sales_2016.n4) / vm.sales_2016.n4).toFixed(2))
        vm.score.안정성.점포변동률 = Number(((dn1 + dn2 + dn3 + dn4 + dn5 + dn6 + dn7 + dn8) / 8).toFixed(3)) * 100
        console.log('점포변동률 : ', vm.score.안정성.점포변동률)

        // 안정성 > 매출 변동률
        let ds1 = Number((Math.abs(vm.sales_2018.q4 - vm.sales_2018.q3) / vm.sales_2018.q3).toFixed(2))
        let ds2 = Number((Math.abs(vm.sales_2018.q3 - vm.sales_2018.q2) / vm.sales_2018.q2).toFixed(2))
        let ds3 = Number((Math.abs(vm.sales_2018.q2 - vm.sales_2018.q1) / vm.sales_2018.q1).toFixed(2))
        let ds4 = Number((Math.abs(vm.sales_2018.q1 - vm.sales_2017.q4) / vm.sales_2017.q4).toFixed(2))
        let ds5 = Number((Math.abs(vm.sales_2017.q4 - vm.sales_2017.q3) / vm.sales_2017.q3).toFixed(2))
        let ds6 = Number((Math.abs(vm.sales_2017.q3 - vm.sales_2017.q2) / vm.sales_2017.q2).toFixed(2))
        let ds7 = Number((Math.abs(vm.sales_2017.q2 - vm.sales_2017.q1) / vm.sales_2017.q1).toFixed(2))
        let ds8 = Number((Math.abs(vm.sales_2017.q1 - vm.sales_2016.q4) / vm.sales_2016.q4).toFixed(2))
        vm.score.안정성.매출변동률 = Number(((ds1 + ds2 + ds3 + ds4 + ds5 + ds6 + ds7 + ds8) / 8).toFixed(3)) * 100
        console.log('매출변동률 : ', vm.score.안정성.매출변동률)

        // 영업력 > 분기별 매출 편차
        let qmax = Math.max(vm.sales_2018.q1, vm.sales_2018.q2, vm.sales_2018.q3, vm.sales_2018.q4)
        let qmin = Math.min(vm.sales_2018.q1, vm.sales_2018.q2, vm.sales_2018.q3, vm.sales_2018.q4)
        vm.score.영업력.분기별매출편차 = 5 - Number(((qmax - qmin) * 10 / qmin).toFixed(1))
        console.log('분기별매출편차 : ', vm.score.영업력.분기별매출편차)

        // 성장성 > 매출 증감률
        let total2018 = vm.sales_2018.q1 + vm.sales_2018.q2 + vm.sales_2018.q3 + vm.sales_2018.q4
        let total2017 = vm.sales_2017.q1 + vm.sales_2017.q2 + vm.sales_2017.q3 + vm.sales_2017.q4
        let total2016 = vm.sales_2016.q1 + vm.sales_2016.q2 + vm.sales_2016.q3 + vm.sales_2016.q4
        vm.score.성장성.매출증감률 = 5 + Number(((total2018 - total2017) * 1000 / total2017).toFixed(1))
        console.log('매출증감률 : ', vm.score.성장성.매출증감률)

        // 성장성 > 예상 성장률
        vm.growth_rate.average_rate = ((total2018 / total2016) ** (1 / 3) - 1).toFixed(2)
        vm.score.성장성.예상성장률 = vm.growth_rate.average_rate * 10 + 3
        console.log('예상성장률 : ', vm.score.성장성.예상성장률)

        // 안정성 > 운영연수
        vm.score.안정성.운영연수 = ((vm.years.continuousYears / vm.years.avgSeoul) * 5 - 2).toFixed(2)
        console.log('운영연수 : ', vm.score.안정성.운영연수)

        // 안정성 > 폐업률
      })
      .then(() => {
        if (vm.score.성장성.매출증감률 > 10) {
          vm.score.성장성.매출증감률 = 10
        } else if (vm.score.성장성.매출증감률 < 0) {
          vm.score.성장성.매출증감률 = 0
        }
        if (vm.score.성장성.예상성장률 > 10) {
          vm.score.성장성.예상성장률 = 10
        } else if (vm.score.성장성.예상성장률 < 0) {
          vm.score.성장성.예상성장률 = 0
        }
      })
      .catch(err => console.log(err))
  }
}

</script>

<style></style>
