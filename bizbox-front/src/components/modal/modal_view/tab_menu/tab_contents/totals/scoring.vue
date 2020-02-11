<template>
  <div>
    <h1>2017년 분기별 매출</h1>
    <p>q1: {{ sales_2017.q1 }} n1: {{ sales_2017.n1 }}</p>
    <p>q2: {{ sales_2017.q2 }} n2: {{ sales_2017.n2 }}</p>
    <p>q3: {{ sales_2017.q3 }} n3: {{ sales_2017.n3 }}</p>
    <p>q4: {{ sales_2017.q4 }} n4: {{ sales_2017.n4 }}</p><hr>
    <!-- <h1>연 평균 성장률</h1>
    <h1>{{ growth_rate.average_rate }}</h1> -->

  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
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
    let requestSalesUrl = 'http://70.12.247.78:8080/sales/' + vm.key
    axios.get(requestSalesUrl)
      .then(res => {
        for (let index = 0; index < res.data.length; index++) {
          let temp = res.data[index]
          let serviceCode = temp.e // 업종 코드

          if (temp.a === '2018') { // 연도, 분기
            if (temp.b === '1') {
              this.sales_2018.q1 += Number(temp.g)
              this.sales_2018.n1 += Number(temp.af)
              this.sales_2018.classification[serviceCode].q1 += Number(temp.g)
              this.sales_2018.classification[serviceCode].n1 += Number(temp.af)
            } else if (temp.b === '2') {
              this.sales_2018.q2 += Number(temp.g)
              this.sales_2018.n2 += Number(temp.af)
              this.sales_2018.classification[serviceCode].q2 += Number(temp.g)
              this.sales_2018.classification[serviceCode].n2 += Number(temp.af)
            } else if (temp.b === '3') {
              this.sales_2018.q3 += Number(temp.g)
              this.sales_2018.n3 += Number(temp.af)
              this.sales_2018.classification[serviceCode].q3 += Number(temp.g)
              this.sales_2018.classification[serviceCode].n3 += Number(temp.af)
            } else {
              this.sales_2018.q4 += Number(temp.g)
              this.sales_2018.n4 += Number(temp.af)
              this.sales_2018.classification[serviceCode].q4 += Number(temp.g)
              this.sales_2018.classification[serviceCode].n4 += Number(temp.af)
            }
          } else if (temp.a === '2017') {
            if (temp.b === '1') {
              this.sales_2017.q1 += Number(temp.g)
              this.sales_2017.n1 += Number(temp.af)
              this.sales_2017.classification[serviceCode].q1 += Number(temp.g)
              this.sales_2017.classification[serviceCode].n1 += Number(temp.af)
            } else if (temp.b === '2') {
              this.sales_2017.q2 += Number(temp.g)
              this.sales_2017.n2 += Number(temp.af)
              this.sales_2017.classification[serviceCode].q2 += Number(temp.g)
              this.sales_2017.classification[serviceCode].n2 += Number(temp.af)
            } else if (temp.b === '3') {
              this.sales_2017.q3 += Number(temp.g)
              this.sales_2017.n3 += Number(temp.af)
              this.sales_2017.classification[serviceCode].q3 += Number(temp.g)
              this.sales_2017.classification[serviceCode].n3 += Number(temp.af)
            } else {
              this.sales_2017.q4 += Number(temp.g)
              this.sales_2017.n4 += Number(temp.af)
              this.sales_2017.classification[serviceCode].q4 += Number(temp.g)
              this.sales_2017.classification[serviceCode].n4 += Number(temp.af)
            }
          } else {
            if (temp.b === '1') {
              this.sales_2016.q1 += Number(temp.g)
              this.sales_2016.n1 += Number(temp.af)
              this.sales_2016.classification[serviceCode].q1 += Number(temp.g)
              this.sales_2016.classification[serviceCode].n1 += Number(temp.af)
            } else if (temp.b === '2') {
              this.sales_2016.q2 += Number(temp.g)
              this.sales_2016.n2 += Number(temp.af)
              this.sales_2016.classification[serviceCode].q2 += Number(temp.g)
              this.sales_2016.classification[serviceCode].n2 += Number(temp.af)
            } else if (temp.b === '3') {
              this.sales_2016.q3 += Number(temp.g)
              this.sales_2016.n3 += Number(temp.af)
              this.sales_2016.classification[serviceCode].q3 += Number(temp.g)
              this.sales_2016.classification[serviceCode].n3 += Number(temp.af)
            } else {
              this.sales_2016.q4 += Number(temp.g)
              this.sales_2016.n4 += Number(temp.af)
              this.sales_2016.classification[serviceCode].q4 += Number(temp.g)
              this.sales_2016.classification[serviceCode].n4 += Number(temp.af)
            }
          }
        }
        let resquestHistoryUrl = 'http://70.12.247.78:8080/change/getHistory/' + vm.key
        axios.get(resquestHistoryUrl).then(res => {
          this.years.continuousYears = res.data.cblist[5].g
          this.years.avgSeoul = res.data.cblist[5].i
        })
      }).then(() => {
        let dn1 = (Math.abs(this.sales_2018.n4 - this.sales_2018.n3) / this.sales_2018.n3).toFixed(4)
        let dn2 = (Math.abs(this.sales_2018.n3 - this.sales_2018.n2) / this.sales_2018.n2).toFixed(4)
        let dn3 = (Math.abs(this.sales_2018.n2 - this.sales_2018.n1) / this.sales_2018.n1).toFixed(4)
        let dn4 = (Math.abs(this.sales_2018.n1 - this.sales_2017.n4) / this.sales_2017.n4).toFixed(4)
        let dn5 = (Math.abs(this.sales_2017.n4 - this.sales_2017.n3) / this.sales_2017.n3).toFixed(4)
        let dn6 = (Math.abs(this.sales_2017.n3 - this.sales_2017.n2) / this.sales_2017.n2).toFixed(4)
        let dn7 = (Math.abs(this.sales_2017.n2 - this.sales_2017.n1) / this.sales_2017.n1).toFixed(4)
        let dn8 = (Math.abs(this.sales_2017.n1 - this.sales_2016.n4) / this.sales_2016.n4).toFixed(4)
        this.score.안정성.점포변동률 = ((dn1 + dn2 + dn3 + dn4 + dn5 + dn6 + dn7 + dn8) / 8).toFixed(4) * 100
        console.log('점포변동률 : ', this.score.안정성.점포변동률)

        let ds1 = (Math.abs(this.sales_2018.q4 - this.sales_2018.q3) / this.sales_2018.q3).toFixed(4)
        let ds2 = (Math.abs(this.sales_2018.q3 - this.sales_2018.q2) / this.sales_2018.q2).toFixed(4)
        let ds3 = (Math.abs(this.sales_2018.q2 - this.sales_2018.q1) / this.sales_2018.q1).toFixed(4)
        let ds4 = (Math.abs(this.sales_2018.q1 - this.sales_2017.q4) / this.sales_2017.q4).toFixed(4)
        let ds5 = (Math.abs(this.sales_2017.q4 - this.sales_2017.q3) / this.sales_2017.q3).toFixed(4)
        let ds6 = (Math.abs(this.sales_2017.q3 - this.sales_2017.q2) / this.sales_2017.q2).toFixed(4)
        let ds7 = (Math.abs(this.sales_2017.q2 - this.sales_2017.q1) / this.sales_2017.q1).toFixed(4)
        let ds8 = (Math.abs(this.sales_2017.q1 - this.sales_2016.q4) / this.sales_2016.q4).toFixed(4)
        this.score.안정성.매출변동률 = ((ds1 + ds2 + ds3 + ds4 + ds5 + ds6 + ds7 + ds8) / 8).toFixed(4) * 100
        console.log('매출변동률 : ', this.score.안정성.매출변동률)

        let total2018 = this.sales_2018.q1 + this.sales_2018.q2 + this.sales_2018.q3 + this.sales_2018.q4
        let total2017 = this.sales_2017.q1 + this.sales_2017.q2 + this.sales_2017.q3 + this.sales_2017.q4
        let total2016 = this.sales_2016.q1 + this.sales_2016.q2 + this.sales_2016.q3 + this.sales_2016.q4
        this.score.성장성.매출증감률 = (((total2018 - total2017) / total2017) * 10 + 5).toFixed(1)
        console.log('매출증감률 : ', this.score.성장성.매출증감률)

        this.growth_rate.average_rate = ((total2018 / total2016) ** (1 / 3) - 1).toFixed(2)
        this.score.성장성.예상성장률 = this.growth_rate.average_rate * 10 + 3
        console.log('예상성장률 : ', this.score.성장성.예상성장률)
      })
      .catch(err => console.log(err))
      .finally(() => {
        // 연 평균 성장률, ( + 연도끼리 비교해야함 )

        // this.growth_rate.average_rate = (total2018 / total2016) ** (1 / 3) - 1
        console.log(this.sales_2017.classification)
        // 매출 증감률 ( 작년 대비 )
      })
      // last year
  }
}

</script>

<style></style>
