import axios from 'axios'

export default axios.create({
  baseURL: 'http://70.12.247.78:8080',
  // baseURL: 'http://13.125.20.125:8081',
  headers: {
    'Content-type': 'application/json'
  }
})
