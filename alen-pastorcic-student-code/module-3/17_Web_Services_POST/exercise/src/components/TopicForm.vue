<template>
  <form v-on:submit.prevent="submitForm">
    <div class="field">
      <label for="title">Title</label>
      <input type="text" id="title" name="title" v-model="editTopic.title" />
    </div>
    <div class="actions">
      <button class="btn-submit" type="submit">Submit</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>
  </form>
</template>

<script>
import TopicService from '../services/TopicService.js'; // Import TopicService

export default {
  props: {
    topic: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      editTopic: {
        id: this.topic.id,
        title: this.topic.title,
        date: this.topic.date,
      }
    };
  },
  methods: {
    async submitForm() {
      if (!this.validateForm()) {
        return;
      }

      try {
        if (this.editTopic.id === 0) {
          // Create a new topic
          await TopicService.create(this.editTopic);
          this.$router.push({ name: 'HomeView' });
        } else {
          // Update an existing topic
          await TopicService.update(this.editTopic.id, this.editTopic);
          this.$router.push({ name: 'TopicDetailsView', params: { topicId: this.editTopic.id } });
        }
      } catch (error) {
        this.handleErrorResponse(error, this.editTopic.id === 0 ? 'adding' : 'updating');
      }
    },
    cancelForm() {
      this.$router.back();
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        if (error.response.status === 404) {
          this.$router.push({ name: 'NotFoundView' });
        } else {
          this.$store.commit('SET_NOTIFICATION',
            `Error ${verb} topic. Response received was "${error.response.statusText}".`);
        }
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', `Error ${verb} topic. Server could not be reached.`);
      } else {
        this.$store.commit('SET_NOTIFICATION', `Error ${verb} topic. Request could not be created.`);
      }
    },
    validateForm() {
      let msg = '';
      this.editTopic.title = this.editTopic.title.trim();
      if (this.editTopic.title.length === 0) {
        msg += 'The new topic must have a title. ';
      }

      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    },
  }
};
</script>

<style>
form {
  padding: 20px;
  font-size: 16px;
}
form * {
  box-sizing: border-box;
  line-height: 1.5;
}
.field {
  display: flex;
  flex-direction: column;
}
.field label {
  margin: 4px 0;
  font-weight: bold;
}
.field input,
.field textarea {
  padding: 8px;
  font-size: 18px;
}
.field textarea {
  height: 300px;
}
.actions {
  text-align: right;
  padding: 10px 0;
}
</style>