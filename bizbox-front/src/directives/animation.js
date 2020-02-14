const animatedObserver = new IntersectionObserver(
  (entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        entry.target.classList.add('animation')
      } else {
        entry.target.classList.remove('animation')
      }
    })
  }
)

export default {
  bind (el) {
    el.classList.add('before-animation')
    animatedObserver.observe(el)
  }
}
