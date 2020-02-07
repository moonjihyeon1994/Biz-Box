import router from '../../router'

const axios = require('axios')

const state = {
  token: null,
  username: null,
  email: null,
  errors: [],
  loading: false,
  dialog: false
}

const getters = {
  isLoggedIn: state => !!state.token,
  getErrors: state => state.errors,
  isLoading: state => state.loading,
  isPopup: state => state.dialog
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
  pushError: (state, error) => state.errors.push(error),
  clearErrors: state => (state.errors = [])
}

const actions = {
  popupDialog: ({ commit }) => {
    commit('setPopup', true)
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
  },

  pushError ({ commit }, error) {
    commit('pushError', error)
  },

  login: ({ commit, getters }, credentials) => {
    commit('clearErrors')
    if (getters.isLoggedIn) {
      router.push('/')
    } else {
      // console.log(credentials)
      axios
        .post('http://70.12.246.137:8080/user/login/', credentials)
        .then(res => {
          if (res.data.status === true) {
            commit('setToken', res.headers['jwt-auth-token'])
            commit('setUsername', res.data.data.name)
            commit('setUserEmail', res.data.data.email)
            commit('setPopup', false)
            // console.log(sessionStorage)
          } else {
            alert('로그인에 실패했습니다.')
          }
        })
        .catch(err => {
          alert('로그인에 실패했습니다.')
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
          commit('setToken', res.headers['jwt-auth-token'])
          commit('setUsername', res.data.data.name)
          commit('setUserEmail', res.data.data.email)
          commit('setPopup', false)
          // console.log('sessionstorage')
          // console.log(sessionStorage)
        }
      })
  },
  signup: ({ commit }, credentials) => {
    const postUrl = 'http://70.12.246.137:8080/user/signup'
    // const postUrl = 'http://70.12.247.78:8080/user/signup'
    axios.post(postUrl, credentials)
      .then(res => {
        if (res.data.status === true) {
          commit('setToken', res.headers['jwt-auth-token'])
          commit('setUsername', res.data.data.name)
          commit('setUserEmail', res.data.data.email)
          commit('setPopup', false)
          // console.log('sessionstorage')
          // console.log(sessionStorage)
        } else {
          alert('회원가입에 실패했습니다')
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
