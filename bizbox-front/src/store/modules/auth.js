import router from '../../router'
import axios from '@/js/http-commons'


const state = {
  token: null,
  username: null,
  email: null,
  errors: [],
  mylist: [],
  loading: false,
  dialog: false,
  alarm: null
}

const getters = {
  isLoggedIn: state => !!state.token,
  getErrors: state => state.errors,
  getAlarm: state => state.alarm,
  isLoading: state => state.loading,
  isPopup: state => state.dialog,
  getlist: state => state.mylist
}

const mutations = {
  setLoading: (state, flag) => (state.loading = flag),
  setPopup: (state, flag) => (state.dialog = flag),
  setToken: (state, token) => {
    state.token = token
    sessionStorage.setItem('jwt-auth-token', token)
  },
  setUsername: (state, name) => {
    state.username = name
    sessionStorage.setItem('login_user_name', name)
  },
  setUserEmail: (state, email) => {
    state.email = email
    sessionStorage.setItem('login_user_email', email)
  },
  setStoreList: (state, storelist) => {
    state.mylist = storelist
  },
  pushError: (state, error) => state.errors.push(error),
  pushAlarm: (state, alarm) => state.alarm.push(alarm)
}

const actions = {
  popupDialog: ({ commit }) => {
    commit('setPopup', true)
  },
  closeDialog: ({ commit }) => {
    commit('setPopup', false)
  },
  initialLogin: ({ commit }) => {
    const token = sessionStorage.getItem('jwt-auth-token')
    if (token) {
      commit('setToken', token)
    }
  },

  logout: ({ commit }) => {
    commit('setToken', null)
    sessionStorage.removeItem('jwt-auth-token')
    sessionStorage.removeItem('login_user_name')
    sessionStorage.removeItem('login_user_email')
    state.alarm = '로그아웃 되었습니다'
    alert('로그아웃 되었습니다')
    router.push('/')
  },

  pushError ({ commit }, error) {
    commit('pushError', error)
  },

  pushAlarm ({ commit }, alarm) {
    commit('pushAlarm', alarm)
  },

  login: ({ commit, getters }, credentials) => {
    if (getters.isLoggedIn) {
      router.push('/')
    } else {
      // console.log(credentials)
      axios
        .post('/user/login/', credentials)
        .then(res => {
          if (res.data.status === true) {
            commit('setToken', res.headers['jwt-auth-token'])
            commit('setUsername', res.data.data.name)
            commit('setUserEmail', res.data.data.email)
            commit('setStoreList', res.data.storelist)
            commit('setPopup', false)
            state.alarm = '로그인 되었습니다'
            alert('로그인 되었습니다')
          } else {
            console.log()
            state.alarm = '로그인에 실패했습니다'
            alert('아이디/비밀번호를 확인해주세요')
          }
        })
        .catch(err => {
          if (!err.response) {
            commit('pushError', 'Network Error..')
          } else if (err.response.status === 400) {
            commit('pushError', 'Invalid username or password')
          } else if (err.response.status === 500) {
            commit('pushError', 'Internal Server error. Please try again later')
          } else {
            commit('pushError', 'Some error occured')
          }
          commit('setLoading', false)
        })
    }
  },
  loginWithKakao: ({ commit }, getUrl) => {
    axios.get(getUrl)
      .then(res => {
        if (res.data.status) {
          //console.log(res.data)
          commit('setToken', res.headers['jwt-auth-token'])
          commit('setUsername', res.data.data.name)
          commit('setUserEmail', res.data.data.email)
          commit('setPopup', false)
          commit('setStoreList', res.data.storelist)
          state.alarm = '로그인 되었습니다'
          alert('로그인 되었습니다')
          // console.log('sessionstorage')
          // console.log(sessionStorage )
        }
      })
  },
  signup: ({ commit }, credentials) => {
    const postUrl = '/user/signup'
    // const postUrl = 'http://70.12.247.78:8080/user/signup'
    axios.post(postUrl, credentials)
      .then(res => {
        if (res.data.status === true) {
          commit('setToken', res.headers['jwt-auth-token'])
          commit('setUsername', res.data.data.name)
          commit('setUserEmail', res.data.data.email)
          commit('setPopup', false)
          state.alarm = '회원가입되었습니다'
          alert('회원가입 되었습니다')
          // console.log('sessionstorage')
          // console.log(sessionStorage)
        } else {
          state.alarm = '회원가입을 실패하였습니다'
          alert('회원가입에 실패하였습니다')
        }
      })
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
