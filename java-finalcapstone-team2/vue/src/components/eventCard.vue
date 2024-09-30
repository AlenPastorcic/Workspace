<template>
  <div class="event-card" @click="showDetails">
    <img :src="event.imgUrl" alt="Event Image" class="event-card-image" />
    <h2>{{ event.title }}</h2>
    <p>{{ event.description }}</p>
    <h3>You are the {{ eventCategory }} of the event.</h3>
    <p><strong>Date:</strong> {{ formatDate(event.eventDate) }}</p>
    <p><strong>Location:</strong> {{ event.location }}</p>
  </div>
</template>


<script>
export default {
  props: {
    event: Object,
    eventCategory: String
  },
  methods: {
    showDetails() {
      this.$emit('show-details', this.event, this.eventCategory);
      window.scrollTo(0, 0);
    },
    formatDate(dateTime) {
      return new Date(dateTime).toLocaleString();
    }
  }
};
</script>

<style>
.event-card {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
  cursor: pointer;
  transition: box-shadow 0.3s;
}
.event-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.event-card-image {
  max-width: 100%;
  width: auto\9;
  height: 200px;
  display: block;
  margin: auto;
}
</style>