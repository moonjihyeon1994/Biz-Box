<template>
<div class="howToUse">
  <section class="section intro">
  <div class="container">
    <h2>How To Use <h1 id="title">Biz Box </h1></h2>
  </div>
</section>

<section class="timeline">
  <ol>
    <li>
      <div class="serviceText" style="border-radius:5px;">
        <time>비회원 서비스</time> 로그인 없이 사용 가능한 서비스.
      </div>
    </li>
    <li>
      <div>
        <time>Step1</time> 시작하기 버튼으로 시작합니다. <img id="howImg" src="@/assets/icons/howToUse/step1.png">
      </div>
    </li>
    <li>
      <div>
        <time>Step2</time> 지역의 상권 변화 지표를 <br/>지역의 색으로 확인합니다. <img id="howImg" src="@/assets/icons/howToUse/step2.png">
      </div>
    </li>
    <li>
      <div>
        <time>Step3</time> 간략히 보며 <br/> 주변 상권의 특징을 확인합니다. <img id="howImg" src="@/assets/icons/howToUse/step3.png">
      </div>
    </li>
    <li>
      <div>
        <time>Step4</time> 자세히 보기를 통해 <br/> 지역의 세부 사항을 확인합니다. <img id="howImg" src="@/assets/icons/howToUse/step4.png">
      </div>
    </li>
    <li>
      <div>
        <time>Step5</time> 원으로 검색을 통해 <br/> 검색 반경 내 상권을 확인합니다 <img id="howImg" src="@/assets/icons/howToUse/step5.png">
      </div>
    </li>
    <li>
      <div class="serviceText" style="border-radius:5px;">
        <time>회원 서비스</time> 로그인 상태로 사용 가능한 서비스.
      </div>
    </li>
    <li>
      <div>
        <time>1957</time> In mattis elit vitae odio posuere, nec maximus massa varius. Suspendisse varius volutpat mattis. Vestibulum id magna est.
      </div>
    </li>
    <li>
      <div>
        <time>1967</time> Aenean condimentum odio a bibendum rhoncus. Ut mauris felis, volutpat eget porta faucibus, euismod quis ante.
      </div>
    </li>
    <li>
      <div>
        <time>1977</time> Vestibulum porttitor lorem sed pharetra dignissim. Nulla maximus, dui a tristique iaculis, quam dolor convallis enim, non dignissim ligula ipsum a turpis.
      </div>
    </li>
    <li>
      <div>
        <time>1985</time> In mattis elit vitae odio posuere, nec maximus massa varius. Suspendisse varius volutpat mattis. Vestibulum id magna est.
      </div>
    </li>
    <li>
      <div>
        <time>2000</time> In mattis elit vitae odio posuere, nec maximus massa varius. Suspendisse varius volutpat mattis. Vestibulum id magna est.
      </div>
    </li>
    <li>
      <div>
        <time>created At</time> SSAFY
      </div>
    </li>
    <li>
      <div>
      </div>
    </li>
    <li>
      <div>
        <time>2005</time> In mattis elit vitae odio posuere, nec maximus massa varius. Suspendisse varius volutpat mattis. Vestibulum id magna est.
      </div>
    </li>
    <li></li>
  </ol>
  <div class="arrows">
    <button class="arrow arrow__prev disabled" disabled>
      <img src="@/assets/icons/arrow/left.png" alt="prev timeline arrow">
    </button>
    <button class="arrow arrow__next">
      <img src="@/assets/icons/arrow/right.png" alt="next timeline arrow">
    </button>
  </div>
</section>
</div>
</template>

<script>
import './UseDetail.css'
export default {
  data () {
    return {
    }
  },
  mounted () {
    (function () {
    // VARIABLES
      const timeline = document.querySelector('.timeline ol')
      const elH = document.querySelectorAll('.timeline li > div')
      const arrows = document.querySelectorAll('.timeline .arrows .arrow')
      const arrowPrev = document.querySelector('.timeline .arrows .arrow__prev')
      const arrowNext = document.querySelector('.timeline .arrows .arrow__next')
      const firstItem = document.querySelector('.timeline li:first-child')
      const lastItem = document.querySelector('.timeline li:last-child')
      const xScrolling = 350
      const disabledClass = 'disabled'

      // START
      window.addEventListener('load', init)

      function init () {
        setEqualHeights(elH)
        animateTl(xScrolling, arrows, timeline)
        setSwipeFn(timeline, arrowPrev, arrowNext)
        setKeyboardFn(arrowPrev, arrowNext)
      }

      // SET EQUAL HEIGHTS
      function setEqualHeights (el) {
        let counter = 0
        for (let i = 0; i < el.length; i++) {
          const singleHeight = el[i].offsetHeight

          if (counter < singleHeight) {
            counter = singleHeight
          }
        }

        for (let i = 0; i < el.length; i++) {
          el[i].style.height = `${counter}px`
        }
      }

      // CHECK IF AN ELEMENT IS IN VIEWPORT
      // http://stackoverflow.com/questions/123999/how-to-tell-if-a-dom-element-is-visible-in-the-current-viewport
      function isElementInViewport (el) {
        const rect = el.getBoundingClientRect()
        return (
          rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
        rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        )
      }

      // SET STATE OF PREV/NEXT ARROWS
      function setBtnState (el, flag = true) {
        if (flag) {
          el.classList.add(disabledClass)
        } else {
          if (el.classList.contains(disabledClass)) {
            el.classList.remove(disabledClass)
          }
          el.disabled = false
        }
      }

      // ANIMATE TIMELINE
      function animateTl (scrolling, el, tl) {
        let counter = 0
        for (let i = 0; i < el.length; i++) {
          el[i].addEventListener('click', function () {
            if (!arrowPrev.disabled) {
              arrowPrev.disabled = true
            }
            if (!arrowNext.disabled) {
              arrowNext.disabled = true
            }
            const sign = (this.classList.contains('arrow__prev')) ? '' : '-'
            if (counter === 0) {
              tl.style.transform = `translateX(-${scrolling}px)`
            } else {
              const tlStyle = getComputedStyle(tl)
              // add more browser prefixes if needed here
              const tlTransform = tlStyle.getPropertyValue('-webkit-transform') || tlStyle.getPropertyValue('transform')
              const values = parseInt(tlTransform.split(',')[4]) + parseInt(`${sign}${scrolling}`)
              tl.style.transform = `translateX(${values}px)`
            }

            setTimeout(() => {
              isElementInViewport(firstItem) ? setBtnState(arrowPrev) : setBtnState(arrowPrev, false)
              isElementInViewport(lastItem) ? setBtnState(arrowNext) : setBtnState(arrowNext, false)
            }, 1100)

            counter++
          })
        }
      }

      // ADD SWIPE SUPPORT FOR TOUCH DEVICES
      function setSwipeFn (tl, prev, next) {
        // eslint-disable-next-line no-undef
        const hammer = new Hammer(tl)
        hammer.on('swipeleft', () => next.click())
        hammer.on('swiperight', () => prev.click())
      }

      // ADD BASIC KEYBOARD FUNCTIONALITY
      function setKeyboardFn (prev, next) {
        document.addEventListener('keydown', (e) => {
          if ((e.which === 37) || (e.which === 39)) {
            const timelineOfTop = timeline.offsetTop
            const y = window.pageYOffset
            if (timelineOfTop !== y) {
              window.scrollTo(0, timelineOfTop)
            }
            if (e.which === 37) {
              prev.click()
            } else if (e.which === 39) {
              next.click()
            }
          }
        })
      }
    })()
  }
}
</script>
