import router from '../../router'
// const Kakao = require('Kakao')

const HOST = process.env.VUE_APP_SERVER_HOST
// const KAKAOKEY = process.env.VUE_APP_SERVER
const axios = require('axios')

const state = {
  token: null,
  username: null,
  email: null,
  errors: [],
  loading: false
}

const getters = {
  isLoggedIn: state => !!state.token,
  getErrors: state => state.errors,
  isLoading: state => state.loading
}

const mutations = {
  setLoading: (state, flag) => (state.loading = flag),
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
    sessionStorage.setItem('login_user_name', email)
  },
  pushError: (state, error) => state.errors.push(error),
  clearErrors: state => (state.errors = [])
}

const actions = {
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
    router.push('/login')
  },

  pushError ({ commit }, error) {
    commit('pushError', error)
  },

  login: ({ commit, getters }, credentials) => {
    commit('clearErrors')
    if (getters.isLoggedIn) {
      router.push('/')
    } else {
      axios
        .post('http://70.12.246.137:8080/user/login/', credentials)
        .then(res => {
          if (res.data.status === true) {
            // console.log(res)
            commit('setToken', res.headers['jwt-auth-token'])
            commit('setUsername', 'heecheol')
            commit('setUserEmail', res.data.data.email)
            console.log(state)
            router.push('/')
          } else {
            alert('로그인에 실패했습니다.')
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
  loginWithKakao: ({ commit, getters }) => {
    Kakao.init('0574c7ce26ff4134a0dc5f831d6edd37')
    Kakao.Auth.login({
      success: function (authObj) {
        const refreshToken = authObj.refresh_token
        const getUrl = 'http://70.12.246.137:8080/kakao/login?refresh_token=' + refreshToken
        console.log(authObj)

        axios.get(getUrl)
          .then(res => {
            if (res.data.status) {
              commit('setToken', res.headers['jwt-auth-token'])
              commit('setUsername', 'heecheol')
              commit('setUserEmail', res.data.data.email)
              console.log(state)
            }
          })
          .finally(() => {
            router.push('/')
          })
      },
      fail: function (err) {
        alert(JSON.stringify(err))
      }
    })
  },

  signup: (
    { commit, getters, dispatch },
    { username, email, password, passwordConfirmation }
  ) => {
    commit('clearErrors')
    if (getters.isLoggedIn) {
      router.push('/')
    } else {
      commit('clearErrors')
      if (!username) {
        commit('pushError', 'ID를 입력하세요')
      }
      if (!email) {
        commit('pushError', 'E-mail을 입력하세요')
      }
      if (password.length < 8) {
        commit('pushError', '비밀번호는 8자 이상이어야 합니다')
      } else {
        if (password === passwordConfirmation) {
          axios
            .post(HOST + '/api/v1/user/', { username, email, password })
            .then(message => {
              const credentials = {
                username,
                password
              }
              dispatch('login', credentials)
            })
            .catch(err => {
              if (!err.response) {
                commit('pushError', 'Network Error..')
              } else {
                commit('pushError', 'Some error occured')
              }
            })
        } else {
          commit('pushError', '비밀번호가 일치하지 않습니다')
        }
      }
    }
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
