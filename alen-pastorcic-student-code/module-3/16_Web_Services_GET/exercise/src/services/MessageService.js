import axios from 'axios';

const BASE_URL = 'http://localhost:3000';

export default {
  get(id) {
    return axios.get(`${BASE_URL}/messages/${id}`)
      .then(response => response.data)
      .catch(error => {
        console.error(`Error fetching message ${id}:`, error);
        throw error;
      });
  }
};