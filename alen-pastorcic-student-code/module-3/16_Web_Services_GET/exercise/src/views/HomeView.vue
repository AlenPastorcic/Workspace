<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <header class="flex">
      <h1>Topics</h1>
    </header>
    <topic-list v-bind:topics="topics"/>
  </div>
</template>

<script>
import TopicList from '../components/TopicList.vue';
import TopicService from '../services/TopicService';

export default {
  components: {
    TopicList
  },
  data() {
    return {
      topics: [],
      isLoading: true
    };
  },
  methods: {
    async getTopics() {

      try {
      this.topics = await TopicService.list(); // Fetch the topics
    } catch (error) {
      console.error('Error fetching topics:', error);
    } finally {
      this.isLoading = false; // Hide the loading message
    }

    },
  },
  created() {
    this.getTopics();
  }
}
</script>

<style scoped>
</style>