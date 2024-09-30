<template>
    <event-form :event="event" :key="event.title"/>
</template>
  
  <script>
import eventForm from '../components/eventForm.vue';
  export default {
    components: { eventForm },
    data(){
      return {
        event: this.$route.query.event!=null ? JSON.parse(decodeURIComponent(this.$route.query.event)) : {
        title: '',
        event_date: '',
        location: ''
      }
      }
    }
    
  };
  </script>