<template>
  <mini-event-form/>
  <user-dash-board/>
</template>

<script>
import miniEventForm from '../components/miniEventForm.vue';
import UserDashBoard from '../components/userDashBoard.vue';
export default {
  components: { miniEventForm , UserDashBoard },
};
</script>
