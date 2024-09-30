<template>
  <div class="loading" v-if="isLoading">
    <h1>Card Detail</h1>
    <img src="../assets/ping_pong_loader.gif" />
  </div>
  <div v-else>
    <div class="board-actions">
    <router-link v-bind:to="{ name: 'BoardView', params: { id: $route.params.boardId }}">Back to Board</router-link>
  </div>
    <card-detail v-bind:card="card" />
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
      isLoading: true,
      card: {}
    }
  },
  methods: {
    getCardInformation() {
      boardService.getCard(this.$route.params.cardId).then(response => {
        this.card = response.data;
        this.isLoading = false;
      }
      )
    }
  },
  created() {
    this.getCardInformation();
  }
};
</script>
