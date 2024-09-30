import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
  // Fetch the list of topics
  list() {
    return http.get('/topics');
  },

  // Fetch a single topic by ID
  get(id) {
    return http.get(`/topics/${id}`);
  },

  // Create a new topic
  create(topic) {
    return http.post('/topics', topic)
      .then(response => {
        if (response.status === 201) {
          return response.data;
        }
        throw new Error('Failed to create topic');
      });
  },

  // Update an existing topic
  update(id, topic) {
    return http.put(`/topics/${id}`, topic)
      .then(response => {
        if (response.status === 200) {
          return response.data;
        }
        throw new Error('Failed to update topic');
      });
  },

  // Delete a topic
  delete(id) {
    return http.delete(`/topics/${id}`)
      .then(response => {
        if (response.status === 200) {
          return response.data;
        }
        throw new Error('Failed to delete topic');
      });
  }
};






