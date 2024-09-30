<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Topic List</router-link>
    </nav>
    <topic-details v-bind:topic="topic" />
  </div>
</template>

<script>
import TopicDetails from '../components/TopicDetails.vue';
import TopicService from '../services/TopicService';

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
    async getTopic(id) {
      try {
        this.topic = await TopicService.get(id); // Fetch the topic details
      } catch (error) {
        console.error('Error fetching topic:', error);
      } finally {
        this.isLoading = false; // Hide the loading message
      }
    }
  },
  created() {
    this.getTopic(this.$route.params.topicId);
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>