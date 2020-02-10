import axios from 'axios'

export default axios.create({
  // baseURL: 'http://70.12.246.38:8080',
  baseURL: 'http://70.12.246.137:8080',
  // baseURL: 'http://13.125.20.125:8081',
  // baseURL: 'http://70.12.127.78:8080',
  // baseURL: 'http://70.12.247.78:8080',
  headers: {
    'Content-type': 'application/json'
  }
})
