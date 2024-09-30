<template>
  <div v-if="isLoading">
    <h1>Loading...</h1>
  </div>
  <div v-else>
    <card-detail />
  </div>
</template>

<script>
import CardDetail from '../components/CardDetail.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    CardDetail
  },
  data() {
    return {
      isLoading: true
    }
  },
  methods: {
    getCardInformation() {
      boardService.getCard(this.$route.params.id)
    }
  }
};
</script>
