import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/LukixSpring',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default instance;
