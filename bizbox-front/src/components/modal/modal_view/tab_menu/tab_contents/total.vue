<template>
  <div class="main-content" v-if="seen">
    <h1><strong>상권 종합평가</strong></h1>
    <scoring></scoring>
    <div class="container1">
      <div id="map-box"></div><br>
      <div id="info-box">
        <div id="prospect-box" v-bind:style="lightcolor">{{ result.prospect }}</div>
        <span class="score">{{ result.score }}</span>
      </div>
    </div>

    <h1><strong>상권 주요정보</strong></h1>
    <div class="container2">
      <table>
        <col>
        <colgroup span="2"></colgroup>
        <colgroup span="2"></colgroup>
        <tr>
          <td rowspan="2"></td>
          <th colspan="6" scope="colgroup">업소수</th>
          <th colspan="3" scope="colgroup">인구</th>
        </tr>
        <tr>
          <th scope="col">전체</th>
          <th scope="col">음식</th>
          <th scope="col">서비스</th>
          <th scope="col">도/소매</th>
          <th scope="col">숙박</th>
          <th scope="col">여가/오락</th>
          <th scope="col">주거</th>
          <th scope="col">직장</th>
          <th scope="col">유동</th>
        </tr>
        <tr>
          <th scope="row">{{ key }}</th>
          <td>{{ stores.전체 }}</td>
          <td>{{ stores.음식 }}</td>
          <td>{{ stores.서비스 }}</td>
          <td>{{ stores.소매 }}</td>
          <td>{{ stores.숙박 }}</td>
          <td>{{ stores.오락 }}</td>
          <td>{{ population.주거 }}</td>
          <td>{{ population.직장 }}</td>
          <td>{{ population.유동 }}</td>
        </tr>
      </table>
    </div>

    <h1><strong>상권 주요차트</strong></h1>
    <div>here</div>
  </div>
</template>

<script>
import scoring from './totals/scoring.vue'
import axios from 'axios'

export default {
  components: {
    scoring
  },
  data () {
    return {
      key: this.$store.state.modalsearch,
      seen: true,
      lightcolor: {
        backgroundColor: '#14bdfb'
      },

      result: {
        score: '90점',
        prospect: '상권 좋음'
      },

      // table settings
      headers: [
        {
          text: '상권 정보',
          align: 'left',
          sortable: false,
          value: 'name'
        },
        { text: '' }
      ],

      // table header data
      stores: {
        '전체': '0',
        '음식': '0',
        '서비스': '0',
        '소매': '0',
        '숙박': '0',
        '오락': '0'
      },
      population: {
        '주거': '',
        '직장': '',
        '유동': ''
      }
    }
  },
  mouonted () {
    const suggestionUrl = 'http://70.12.247.78:8080/suggestion/Industry/' + this.key
    axios.get(suggestionUrl)
      .then(res => {
        console.log('success to get suggestion')
        this.result.summary = res.data.bl.name
        this.result.score = 80
        if (this.result.score >= 80) {
          this.lightcolor.backgroundColor = '#14bdfb'
          this.result.prospect = '상권 평가 : 좋음'
        } else if (this.result.score >= 60) {
          this.lightcolor.backgroundColor = '#39e600'
          this.result.prospect = '상권 평가 : 보통'
        } else if (this.result.score >= 40) {
          this.lightcolor.backgroundColor = '#ffd633'
          this.result.prospect = '상권 평가 : 나쁨'
        } else {
          this.lightcolor.backgroundColor = '#ff6600'
          this.result.prospect = '상권 평가 : 최악'
        }
        this.result.score = '70점'
        this.seen = true
      })
      .catch(err => console.log(err))
    const storeCountUrl = 'http://70.12.247.78:8080/storecount/' + this.key + '/1000'
    axios.get(storeCountUrl)
      .then(res => {
        console.log('success to get storeCount')
        const JsonLarge = res.data.large
        let totalCount = 0
        if (JsonLarge.소매 !== undefined) {
          this.stores.소매 = JsonLarge.소매
          totalCount += Number(JsonLarge.소매)
        }
        if (JsonLarge.음식 !== undefined) {
          this.stores.음식 = JsonLarge.음식
          totalCount += Number(JsonLarge.음식)
        }
        if (JsonLarge.생활서비스 !== undefined) {
          this.stores.서비스 = JsonLarge.생활서비스
          totalCount += Number(JsonLarge.생활서비스)
        }
        if (JsonLarge.숙박 !== undefined) {
          this.stores.숙박 = JsonLarge.숙박
          totalCount += Number(JsonLarge.숙박)
        }
        if (JsonLarge.관광여가오락 !== undefined) {
          this.stores.오락 = JsonLarge.관광여가오락
          totalCount += Number(JsonLarge.관광여가오락)
        }
        this.stores.전체 = totalCount.toString()
      })
  }
}

</script>

<style>
.main-content {
  box-sizing: border-box;
  width: auto;
  border: 1px solid black;
}
.main-content > div {
  padding: 30px;
  margin-bottom: 20px;
  /* box-sizing: border-box;
  width: 100%; */
  width: auto;
  border: 1px solid red;
  text-align: center;
}
#map-box {
  display: inline-block;
  width: 330px;
  height: 300px;
  background-image: url('http://sg.sbiz.or.kr/repos/analyFiles/sgAnaly/20200204/4350175/map0.jpg');
  background-size: contain;
  border: 1px solid black;
  margin-bottom: 20px;
}
#info-box {
  display: inline-block;
  /* need to check margin & padding */
  width: 300px;
  height: 70px;
  font-size: 25px;
}
#prospect-box {
  display: inline-block;
  width: 100px;
  height: 30px;
  padding: 5px 10px;
  font-size: 16px;
  border-radius: 10px;
}
.light {
  display: inline-block;
  height: 50px;
  width: 50px;
  border-radius: 50%;
}
h1 {
  font-size: 20px;
}
table {
  display: inline-block;
  border: 1px solid black;
}
td {
  text-align: center;
  border: 1px solid black;
}
th {
  padding: 5px 10px;
  border: 1px solid black;
  background-color: rgb(250, 241, 229);
}
</style>
