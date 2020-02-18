<template>
  <div id='bz-container'>
    <table>
      <caption>{{ sgName }}</caption>
      <tbody>
        <tr>
          <th>성장성</th>
          <td>{{ score.성장성.점수 }}</td>
        </tr>

        <tr>
          <th>안정성</th>
          <td>{{ score.안정성.점수 }}</td>
        </tr>

        <tr>
          <th>영업력</th>
          <td>{{ score.영업력.점수 }}</td>
        </tr>

        <tr>
          <th>구매력</th>
          <td>{{ score.구매력.점수 }}</td>
        </tr>

        <tr>
          <th>집객력</th>
          <td>{{ score.집객력.점수 }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'
import { eventBus } from '@/js/bus'

export default {
  computed: {
    key () {
      return this.$store.state.modalsearch
    }
  },
  props: {
    clickEvent: Boolean
  },
  watch: {
    clickEvent () {
      this.getScoreData()
    }
  },
  data () {
    return {
      sgCode: '',
      sgName: '',
      sales_2018: {
        'q1': 0,
        'q2': 0,
        'q3': 0,
        'q4': 0,
        'n1': 0,
        'n2': 0,
        'n3': 0,
        'n4': 0,
        'inWeek': {
          'mon': 0,
          'tue': 0,
          'wed': 0,
          'thu': 0,
          'fri': 0,
          'sat': 0,
          'sun': 0
        },
        'selng_co': 0,
        'category': {
          'CAT01': { 'name': '소매', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT02': { 'name': '학문/교육', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT03': { 'name': '숙박', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT04': { 'name': '생활서비스', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT05': { 'name': '음식', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT06': { 'name': '부동산', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT07': { 'name': '의료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT08': { 'name': '관광/여가/오락', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        },
        'subCategory': {
          'CS100001': { 'name': '한식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '커피·음료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '두발미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
          'CS200013': { 'name': '세탁소(가정)', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
        'category': {
          'CAT01': { 'name': '소매', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT02': { 'name': '학문/교육', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT03': { 'name': '숙박', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT04': { 'name': '생활서비스', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT05': { 'name': '음식', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT06': { 'name': '부동산', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT07': { 'name': '의료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT08': { 'name': '관광/여가/오락', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        },
        'subCategory': {
          'CS100001': { 'name': '한식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '커피·음료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '두발미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
          'CS200013': { 'name': '세탁소(가정)', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
        'category': {
          'CAT01': { 'name': '소매', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT02': { 'name': '학문/교육', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT03': { 'name': '숙박', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT04': { 'name': '생활서비스', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT05': { 'name': '음식', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT06': { 'name': '부동산', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT07': { 'name': '의료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CAT08': { 'name': '관광/여가/오락', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 }
        },
        'subCategory': {
          'CS100001': { 'name': '한식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100002': { 'name': '중식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100003': { 'name': '일식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100004': { 'name': '양식음식점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100005': { 'name': '분식전문점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100006': { 'name': '패스트푸드점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100008': { 'name': '제과점', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS100009': { 'name': '커피·음료', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200001': { 'name': '일반교습학원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200005': { 'name': '한의원', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200009': { 'name': '숙박업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
          'CS200016': { 'name': '두발미용업', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
          'CS200013': { 'name': '세탁소(가정)', 'q1': 0, 'q2': 0, 'q3': 0, 'q4': 0, 'n1': 0, 'n2': 0, 'n3': 0, 'n4': 0 },
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
        '합계': 0,
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
  mounted () {
    // this.getScoreData()
    eventBus.$on('clickmap', name => {
      this.getScoreData()
    })
  },
  methods: {
    getScoreData () {
      let vm = this
      this.$emit('childs-loading-event', true)

      let requestSalesUrl = '/predict/findBusiness/' + this.$store.state.Coords.lng + '/' + this.$store.state.Coords.lat + '/'
      axios.get(requestSalesUrl)
        .then(res => {
          let data2018 = res.data['2018']
          let data2017 = res.data['2017']
          let data2016 = res.data['2016']

          vm.sgCode = data2018[0].trdar_cd
          vm.sgName = data2018[0].trdar_cd_nm

          // 집객력 > 주거인구, 직장인구
          for (let index = 0; index < data2018.length; index++) {
            let temp2018 = data2018[index]
            let serviceCode2018 = temp2018.svc_induty_cd // 업종 코드
            vm.sales_2018.selng_co += Number(temp2018.thsmon_selng_co)

            if (temp2018.stdr_qu_cd === '1') {
              vm.sales_2018.q1 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.n1 += Number(temp2018.stor_co)
              vm.sales_2018.subCategory[serviceCode2018].q1 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.subCategory[serviceCode2018].n1 += Number(temp2018.stor_co)
            } else if (temp2018.stdr_qu_cd === '2') {
              vm.sales_2018.q2 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.n2 += Number(temp2018.stor_co)
              vm.sales_2018.subCategory[serviceCode2018].q2 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.subCategory[serviceCode2018].n2 += Number(temp2018.stor_co)
            } else if (temp2018.stdr_qu_cd === '3') {
              vm.sales_2018.q3 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.n3 += Number(temp2018.stor_co)
              vm.sales_2018.subCategory[serviceCode2018].q3 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.subCategory[serviceCode2018].n3 += Number(temp2018.stor_co)
              // 요일별 매출 => 가을 기준
              vm.sales_2018.inWeek.mon += Number(temp2018.mon_selng_amt)
              vm.sales_2018.inWeek.tue += Number(temp2018.tues_selng_amt)
              vm.sales_2018.inWeek.wed += Number(temp2018.wed_selng_amt)
              vm.sales_2018.inWeek.thu += Number(temp2018.thur_selng_amt)
              vm.sales_2018.inWeek.fri += Number(temp2018.fri_selng_amt)
              vm.sales_2018.inWeek.sat += Number(temp2018.sat_selng_amt)
              vm.sales_2018.inWeek.sun += Number(temp2018.sun_selng_amt)
            } else {
              vm.sales_2018.q4 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.n4 += Number(temp2018.stor_co)
              vm.sales_2018.subCategory[serviceCode2018].q4 += Number(temp2018.thsmon_selng_amt)
              vm.sales_2018.subCategory[serviceCode2018].n4 += Number(temp2018.stor_co)
            }
          }

          for (let index = 0; index < data2017.length; index++) {
            let temp2017 = data2017[index]
            let serviceCode2017 = temp2017.svc_induty_cd

            vm.selng_co += Number(temp2017.thsmon_selng_co)

            if (temp2017.stdr_qu_cd === '1') {
              vm.sales_2017.q1 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.n1 += Number(temp2017.stor_co)
              vm.sales_2017.subCategory[serviceCode2017].q1 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.subCategory[serviceCode2017].n1 += Number(temp2017.stor_co)
            } else if (temp2017.stdr_qu_cd === '2') {
              vm.sales_2017.q2 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.n2 += Number(temp2017.stor_co)
              vm.sales_2017.subCategory[serviceCode2017].q2 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.subCategory[serviceCode2017].n2 += Number(temp2017.stor_co)
            } else if (temp2017.stdr_qu_cd === '3') {
              vm.sales_2017.q3 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.n3 += Number(temp2017.stor_co)
              vm.sales_2017.subCategory[serviceCode2017].q3 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.subCategory[serviceCode2017].n3 += Number(temp2017.stor_co)
            } else {
              vm.sales_2017.q4 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.n4 += Number(temp2017.stor_co)
              vm.sales_2017.subCategory[serviceCode2017].q4 += Number(temp2017.thsmon_selng_amt)
              vm.sales_2017.subCategory[serviceCode2017].n4 += Number(temp2017.stor_co)
            }
          }

          for (let index = 0; index < data2016.length; index++) {
            let temp2016 = data2016[index]
            let serviceCode2016 = temp2016.svc_induty_cd

            vm.selng_co += Number(temp2016.thsmon_selng_co)

            if (temp2016.stdr_qu_cd === '1') {
              vm.sales_2016.q1 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.n1 += Number(temp2016.stor_co)
              vm.sales_2016.subCategory[serviceCode2016].q1 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.subCategory[serviceCode2016].n1 += Number(temp2016.stor_co)
            } else if (temp2016.stdr_qu_cd === '2') {
              vm.sales_2016.q2 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.n2 += Number(temp2016.stor_co)
              vm.sales_2016.subCategory[serviceCode2016].q2 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.subCategory[serviceCode2016].n2 += Number(temp2016.stor_co)
            } else if (temp2016.stdr_qu_cd === '3') {
              vm.sales_2016.q3 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.n3 += Number(temp2016.stor_co)
              vm.sales_2016.subCategory[serviceCode2016].q3 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.subCategory[serviceCode2016].n3 += Number(temp2016.stor_co)
            } else {
              vm.sales_2016.q4 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.n4 += Number(temp2016.stor_co)
              vm.sales_2016.subCategory[serviceCode2016].q4 += Number(temp2016.thsmon_selng_amt)
              vm.sales_2016.subCategory[serviceCode2016].n4 += Number(temp2016.stor_co)
            }
          }
        })
        .then(() => {
          let requestPopulationUrl = '/population/getPopulationByBusiness/' + vm.sgCode
          axios.get(requestPopulationUrl)
            .then(res => {
              vm.populations.living = Number(res.data.data[0].total_mv_co)
              vm.score.집객력.주거인구 = Number((vm.populations.living * 5 / 4400).toFixed(2))
              vm.populations.working = Number(res.data.data[0].total_bz_co)
              vm.score.집객력.직장인구 = Number((vm.populations.working * 5 / 4500).toFixed(2))
              vm.populations.floating = Number(res.data.data[0].total_co)
              vm.score.집객력.유동인구 = Number((vm.populations.floating / 20000).toFixed(2))
            })
            .then(() => {
              if (vm.score.집객력.주거인구 > 5) {
                vm.score.집객력.주거인구 = 5
              }
              if (vm.score.집객력.직장인구 > 5) {
                vm.score.집객력.직장인구 = 5
              }
              if (vm.score.집객력.유동인구 > 10) {
                vm.score.집객력.유동인구 = 10
              }
            })
            .then(() => {
              let resquestHistoryUrl = '/change/getHistory/' + vm.key
              axios.get(resquestHistoryUrl)
                .then(res => {
                  let continuousYears = Number(res.data.cblist[5].g)
                  let avgSeoul = Number(res.data.cblist[5].i)
                  vm.score.안정성.운영연수 = Number(((continuousYears / (avgSeoul + 2)) * 5).toFixed(2))
                })
                .then(() => {
                  if (vm.score.안정성.운영연수 > 5) {
                    vm.score.안정성.운영연수 = 5
                  }
                })
                .then(() => {
                // 성장성 > 매출 증감률
                  let total2018 = vm.sales_2018.q1 + vm.sales_2018.q2 + vm.sales_2018.q3 + vm.sales_2018.q4
                  let total2016 = vm.sales_2016.q1 + vm.sales_2016.q2 + vm.sales_2016.q3 + vm.sales_2016.q4
                  vm.score.성장성.매출증감률 = 5 + Number(((total2018 - total2016) * 100 / total2016).toFixed(2))

                  // 성장성 > 예상 성장률
                  vm.growth_rate.average_rate = (total2018 / total2016) ** (1 / 3) - 1
                  vm.score.성장성.예상성장률 = Number((vm.growth_rate.average_rate * 100 + 5).toFixed(2))

                  // 안정성 > 점포 변동률
                  let dn1 = Number((Math.abs(vm.sales_2018.n4 - vm.sales_2018.n3) / vm.sales_2018.n3).toFixed(2))
                  let dn2 = Number((Math.abs(vm.sales_2018.n3 - vm.sales_2018.n2) / vm.sales_2018.n2).toFixed(2))
                  let dn3 = Number((Math.abs(vm.sales_2018.n2 - vm.sales_2018.n1) / vm.sales_2018.n1).toFixed(2))
                  let dn4 = Number((Math.abs(vm.sales_2018.n1 - vm.sales_2017.n4) / vm.sales_2017.n4).toFixed(2))
                  let dn5 = Number((Math.abs(vm.sales_2017.n4 - vm.sales_2017.n3) / vm.sales_2017.n3).toFixed(2))
                  let dn6 = Number((Math.abs(vm.sales_2017.n3 - vm.sales_2017.n2) / vm.sales_2017.n2).toFixed(2))
                  let dn7 = Number((Math.abs(vm.sales_2017.n2 - vm.sales_2017.n1) / vm.sales_2017.n1).toFixed(2))
                  let dn8 = Number((Math.abs(vm.sales_2017.n1 - vm.sales_2016.n4) / vm.sales_2016.n4).toFixed(2))
                  vm.score.안정성.점포변동률 = 5 - Number(((dn1 + dn2 + dn3 + dn4 + dn5 + dn6 + dn7 + dn8) * 100 / 8).toFixed(2))

                  // 안정성 > 매출 변동률
                  let ds1 = Number((Math.abs(vm.sales_2018.q4 - vm.sales_2018.q3) / vm.sales_2018.q3).toFixed(2))
                  let ds2 = Number((Math.abs(vm.sales_2018.q3 - vm.sales_2018.q2) / vm.sales_2018.q2).toFixed(2))
                  let ds3 = Number((Math.abs(vm.sales_2018.q2 - vm.sales_2018.q1) / vm.sales_2018.q1).toFixed(2))
                  let ds4 = Number((Math.abs(vm.sales_2018.q1 - vm.sales_2017.q4) / vm.sales_2017.q4).toFixed(2))
                  let ds5 = Number((Math.abs(vm.sales_2017.q4 - vm.sales_2017.q3) / vm.sales_2017.q3).toFixed(2))
                  let ds6 = Number((Math.abs(vm.sales_2017.q3 - vm.sales_2017.q2) / vm.sales_2017.q2).toFixed(2))
                  let ds7 = Number((Math.abs(vm.sales_2017.q2 - vm.sales_2017.q1) / vm.sales_2017.q1).toFixed(2))
                  let ds8 = Number((Math.abs(vm.sales_2017.q1 - vm.sales_2016.q4) / vm.sales_2016.q4).toFixed(2))
                  vm.score.안정성.매출변동률 = 13 - Number(((ds1 + ds2 + ds3 + ds4 + ds5 + ds6 + ds7 + ds8) * 100 / 8).toFixed(2))

                  // 영업력 > 분기별 매출 편차
                  let qmax = Math.max(vm.sales_2018.q1, vm.sales_2018.q2, vm.sales_2018.q3, vm.sales_2018.q4)
                  let qmin = Math.min(vm.sales_2018.q1, vm.sales_2018.q2, vm.sales_2018.q3, vm.sales_2018.q4)
                  vm.score.영업력.분기별매출편차 = Number((qmin * 5 / qmax).toFixed(2))

                  // 영업력 > 요일별 매출 편차
                  let dmax = Math.max(vm.sales_2018.inWeek.mon, vm.sales_2018.inWeek.tue, vm.sales_2018.inWeek.wed, vm.sales_2018.inWeek.thu, vm.sales_2018.inWeek.fri, vm.sales_2018.inWeek.sat, vm.sales_2018.inWeek.sun)
                  let dmin = Math.min(vm.sales_2018.inWeek.mon, vm.sales_2018.inWeek.tue, vm.sales_2018.inWeek.wed, vm.sales_2018.inWeek.thu, vm.sales_2018.inWeek.fri, vm.sales_2018.inWeek.sat, vm.sales_2018.inWeek.sun)
                  vm.score.영업력.요일별매출편차 = Number((dmin * 5 / dmax).toFixed(2))

                  // 영업력 > 공급 대비 수요
                  let demands = Number(total2018 / 56900000000000)
                  let supplies = Number((vm.sales_2018.n1 + vm.sales_2018.n2 + vm.sales_2018.n3 + vm.sales_2018.n4) / 865566)
                  vm.score.영업력.공급대비수요 = Number((demands * 10 / supplies).toFixed(2))

                  // 구매력 > 상권 평균 매출규모
                  vm.score.구매력.상권매출규모 = 5 + Number(((total2018 - 461580856) * 10 / 461580856).toFixed(2))

                  // 구매력 > 평균 건당 결제금액
                  let avgPrice = Number((total2018 / vm.sales_2018.selng_co).toFixed(2))
                  vm.score.구매력.건당결제금액 = Number((avgPrice * 10 / 40000).toFixed(2))
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
                  if (vm.score.안정성.점포변동률 < 0) {
                    vm.score.안정성.점포변동률 = 0
                  }
                  if (vm.score.안정성.매출변동률 > 10) {
                    vm.score.안정성.매출변동률 = 10
                  } else if (vm.score.안정성.매출변동률 < 0) {
                    vm.score.안정성.매출변동률 = 0
                  }
                  if (vm.score.구매력.상권매출규모 > 10) {
                    vm.score.구매력.상권매출규모 = 10
                  } else if (vm.score.구매력.상권매출규모 < 0) {
                    vm.score.구매력.상권매출규모 = 0
                  }
                  if (vm.score.구매력.건당결제금액 > 10) {
                    vm.score.구매력.건당결제금액 = 10
                  }
                  if (vm.score.영업력.공급대비수요 > 10) {
                    vm.score.영업력.공급대비수요 = 10
                  }
                })
                .then(() => {
                  vm.score.성장성.점수 = Number((vm.score.성장성.매출증감률 + vm.score.성장성.예상성장률).toFixed(1))
                  vm.score.안정성.점수 = Number((vm.score.안정성.매출변동률 + vm.score.안정성.점포변동률 + vm.score.안정성.운영연수).toFixed(1))
                  vm.score.영업력.점수 = Number((vm.score.영업력.분기별매출편차 + vm.score.영업력.요일별매출편차).toFixed(1))
                  vm.score.구매력.점수 = Number((vm.score.구매력.상권매출규모 + vm.score.구매력.건당결제금액).toFixed(1))
                  vm.score.집객력.점수 = Number((vm.score.집객력.유동인구 + vm.score.집객력.주거인구 + vm.score.집객력.직장인구).toFixed(1))
                })
                .then(() => {
                  vm.score.합계 = Number((vm.score.성장성.점수 + vm.score.안정성.점수 + vm.score.영업력.점수 + vm.score.구매력.점수 + vm.score.집객력.점수).toFixed(1))
                  vm.$emit('childs-event', vm.score.합계, vm.sgName)
                  vm.$emit('childs-loading-event', false)
                })
            })
        })
    }
  }
}

</script>

<style scoped src='./Scoring.scss' lang='scss'></style>
