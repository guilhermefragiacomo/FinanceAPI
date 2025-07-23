import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/MyFinanceAPI/',
})

export default api;