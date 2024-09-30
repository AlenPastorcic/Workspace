<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'TopicDetailsView', params: { topicId: topicId } }">Back to Topic Details</router-link>
    </nav>
    <message-details v-bind:message="message" />
  </div>
</template>

<script>
import MessageDetails from '../components/MessageDetails.vue';
import MessageService from '../services/MessageService'; // Import the MessageService

export default {
  components: {
    MessageDetails,
  },
  data() {
    return {
      topicId: this.$route.params.topicId,
      message: {},
      isLoading: true
    };
  },
  methods: {
    async getMessage(id) {
      try {
        this.message = await MessageService.get(id); // Fetch the message details
      } catch (error) {
        console.error('Error fetching message:', error);
      } finally {
        this.isLoading = false; // Hide the loading message
      }
    }
  },
  created() {
    this.getMessage(this.$route.params.messageId); // Fetch the message when the component is created
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
