<template>
  <div class="foot-container">
    <div class="foot-item">
      <h1 class="foot-title">Biz Box</h1>
      <span>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
        dolorum libero labore quis pariatur officia doloremque distinctio
        tempore repellat, iste incidunt perferendis aliquid sit, facilis ipsa
        hic assumenda quidem maxime.
      </span>
      <div class="button-group">
        <a class="foot-button" v-for="(item, idx) in itemList" :key="'i' + idx" :href="item.url" target="_blank">
          {{ item.title }}
        </a>
      </div>
    </div>
    <div class="foot-item">
      <h1 class="foot-title">About Us</h1>
      <div class="memeber-list">
        <div class="member" v-for="(member, idx) in memberList" :key="'M'+idx">
          <h3 class="memeber-name">{{ member.name }}</h3>
          <h4 class="member-role">{{ member.role }}</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      memberpicture: '../../../assets/images/member/',
      itemList: [
        {
          title: 'GitLab',
          url: 'https://lab.ssafy.com/webmobile1-sub3/S207',
          whereto: 'git'
        },
        {
          title: 'Jira Software',
          url:
            'https://jira.ssafy.com/secure/RapidBoard.jspa?projectKey=S02P13A207&rapidView=2366',
          whereto: 'jira'
        }
      ],
      memberList: [
        { name: '음영현', role: '풀스택 개발자', imgUrl: '../../../assets/images/member/eyh.jpg' },
        { name: '오승완', role: '21세기 김정호', imgUrl: '../../../assets/images/member/osw.jpg' },
        { name: '문지현', role: '학습 괴물', imgUrl: '../../../assets/images/member/mjh.jpg' },
        { name: '양희철', role: '데이터 귀공자', imgUrl: '../../../assets/images/member/yhc.jpg' },
        { name: '문요한', role: '프론트엔드의 종점', imgUrl: '../../../assets/images/member/myh.jpg' },
        { name: '김재현', role: '그림 그리는 개발자', imgUrl: '../../../assets/images/member/kjh.jpg' }
      ]
    }
  }
}
</script>
<style scoped src="./HomeFooter.scss" lang='scss'></style>
