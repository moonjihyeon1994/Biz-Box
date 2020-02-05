<template>
  <div class="foot-container">
    <div class="foot-item">
      <h1>Biz Box</h1>
      <h2>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
        dolorum libero labore quis pariatur officia doloremque distinctio
        tempore repellat, iste incidunt perferendis aliquid sit, facilis ipsa
        hic assumenda quidem maxime.
      </h2>
      <!-- <ul class="button-group" v-for="item in itemList" :key="item.title">
        <li class="foot-button" :href="item.url" target="_blank">
          <button>{{ item.title }}</button>
        </li>
      </ul> -->
    </div>
    <div class="foot-item">
      <h1>About Us</h1>
      <h2>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
        dolorum libero labore quis pariatur officia doloremque distinctio
        tempore repellat, iste incidunt perferendis aliquid sit, facilis ipsa
        hic assumenda quidem maxime.
      </h2>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      itemList: [
        {
          title: 'Git',
          url: 'https://lab.ssafy.com/webmobile1-sub3/S207',
          icon: '@/assets/icons/Gitlab.png'
        },
        {
          title: 'Jira',
          url:
            'https://jira.ssafy.com/secure/RapidBoard.jspa?projectKey=S02P13A207&rapidView=2366',
          icon: '../../assets/icons/Jira.png'
        }
      ]
    }
  }
}
</script>
<style scoped src="./HomeFooter.css"></style>
