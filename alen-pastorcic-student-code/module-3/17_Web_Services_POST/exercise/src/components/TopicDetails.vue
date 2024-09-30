<template>
  <div v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link :to="{ name: 'HomeView' }">Back to Topic List</router-link>
    </nav>
    <topic-details :topic="topic" />
    <button class="btn-delete" @click="deleteTopic">Delete Topic</button>
  </div>
</template>

<script>
import TopicService from '../services/TopicService.js';
import TopicDetails from '../components/TopicDetails.vue';

export default {
  components: {
    TopicDetails
  },
  data() {
    return {
      topic: {},
      isLoading: true
    };
  },
  methods: {
    getTopic(id) {
      TopicService.get(id)
        .then(response => {
          this.topic = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          this.handleErrorResponse(error);
        });
    },
    async deleteTopic() {
      try {
        await TopicService.delete(this.topic.id);
        this.$router.push({ name: 'HomeView' });
      } catch (error) {
        this.handleErrorResponse(error, 'deleting');
      }
    },
    handleErrorResponse(error) {
      if (error.response) {
        if (error.response.status === 404) {
          this.$router.push({ name: 'NotFoundView' });
        } else {
          this.$store.commit('SET_NOTIFICATION', 'Could not get topic data from server.');
        }
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', 'No response received from the server.');
      } else {
        this.$store.commit('SET_NOTIFICATION', `Request error: ${error.message}`);
      }
      this.isLoading = false;
    }
  },
  created() {
    this.getTopic(this.$route.params.topicId);
  }
};
</script>

<style scoped>
/* Optional scoped styles */
</style>