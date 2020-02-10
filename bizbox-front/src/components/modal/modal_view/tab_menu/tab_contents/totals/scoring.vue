<template>
  <div>
    <h1>분기별 매출</h1>
    <h1>q1: {{ sales_this_year.q1 }}</h1>
    <h1>q2: {{ sales_this_year.q2 }}</h1>
    <h1>q3: {{ sales_this_year.q3 }}</h1>
    <h1>q4: {{ sales_this_year.q4 }}</h1>
    <h1>평균 성장률</h1>
    <h1>{{ growth_rate.average_rate }}</h1>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data: () => {
    return {
      key: '진관동',
      result: null,
      sales_this_year: {
        'q1': 0,
        'q2': 0,
        'q3': 0,
        'q4': 0
      },
      sales_last_year: {
        'q1': '',
        'q2': '',
        'q3': '',
        'q4': ''
      },
      sales_change_rate: {
        'c1': '',
        'c2': '',
        'c3': '',
        'c4': ''
      },
      growth_rate: {
        'average_rate': 10
      },
      score: {
        '성장성': '',
        '안정성': '',
        '영업력': '',
        '구매력': '',
        '집객력': ''
      }
    }
  },
  created () {
    // this year
    const requestSalesUrl = 'http://70.12.247.78:8080/sales/' + this.key
    axios.get(requestSalesUrl)
      .then(res => {
        this.result = res.data
        for (let index = 0; index < this.result.length; index++) {
          if (this.result[index].a === '1') {
            this.sales_this_year.q1 += Number(this.result[index].g)
          } else if (this.result[index].a === '2') {
            this.sales_this_year.q2 += Number(this.result[index].g)
          } else if (this.result[index].a === '3') {
            this.sales_this_year.q3 += Number(this.result[index].g)
          } else {
            this.sales_this_year.q4 += Number(this.result[index].g)
          }
        }
      })
      .catch(err => console.log(err))
      .finally(() => {
        console.log(this.sales_this_year)
        // 같은 분기로 해야
        this.growth_rate.average_rate = (this.sales_this_year.q4 / this.sales_this_year.q1) ** (1 / 3) - 1
      })
      // last year
  }
}

</script>

<style></style>
