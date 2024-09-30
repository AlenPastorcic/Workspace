<template>
  <div style="position: relative;">
    <button class="close-form-button" @click="closeForm">X</button>
      <h1>Orders</h1>
      <p v-if="cartItems.length < 1">There is no order yet!</p>
      <table class="order-table" v-else>
        <tr class="order-item cart-item" v-for="(item, index) in cartItems" :key="index">
            <td class="item-title">{{ item.title }}</td>
            <td class="comment-section">
              <input 
                v-model="item.comment" 
                type="text" 
                placeholder="Add a comment"
              />
            </td>
            <td v-if="item.status != null" class="comment-section" style="margin:0 15px 0 15px;">
              <p ><strong>Status: </strong>{{ item.status }}</p>
            </td>
            <td>
            <button class="btn btn-cooking" @click="statusCooking(item)">
              <span >Cooking</span>
            </button></td>
            <td>
            <button class="btn btn-ready" @click="statusReady(item)">
              <span >Ready</span>
            </button></td>
        </tr>
        
      </table>
    </div>
</template>




<script>
import backEndService from '../services/BackEndService';

export default {
  data() {
    return {
      event: JSON.parse(decodeURIComponent(this.$route.query.event)),
      menuItems: [],
      cartItems: [],
      isLoading: true,
      notification: '',
      notificationVisible: false
    };
  },
  methods: {
    addToCart(itemId, title) {
      const newOrder = {
      title: title,
      menuItemId: itemId,
      userId: this.$store.state.user.id,
      comment: '',
      status: null,
      eventId: this.event.eventId
      };
      this.cartItems.push(newOrder);
    },
    statusCooking(item) {
      item.status = 'cooking';
      this.isLoading = true;
      backEndService.updateOrder(item.orderId, item)
      .then(response => {
        item = response.data;
        this.isLoading = false;
      })
    },
    statusReady(item) {
      item.status = 'ready';
      this.isLoading = true;
      backEndService.updateOrder(item.orderId, item)
      .then(response => {
        item = response.data;
        this.isLoading = false;
      })
    },
    closeForm(){
      this.$router.push({ name: 'home'});
    },

  },
  created() {
      backEndService.getOrdersByEvent(this.event.eventId)
      .then(response => {
        this.cartItems = response.data;
        this.isLoading = false;
      })
  }
};
</script>




<style scoped>
h1 {
  text-align: center;
  text-transform: uppercase;
  font-size: 26px;
  letter-spacing: 1px;
  color: #7E91C6;
}
.order-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}

.order-table .order-item {
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}

.order-table .item-title {
    width: 25%;
    padding: 10px;
    font-weight: bold;
    vertical-align: middle;
}

.order-table .comment-section {
    width: 30%;
    padding: 10px;
}

.order-table .comment-section input[type="text"] {
    width: 100%;
    padding: 5px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.order-table .comment-section p {
    margin: 0;
    padding: 0;
    font-size: 14px;
}

.order-table td {
    padding: 10px;
    text-align: center;
    vertical-align: middle;
}

.order-table .btn {
    padding: 8px 16px;
    border-radius: 4px;
    border: none;
    cursor: pointer;
}

.order-table .btn-cooking {
    background-color: #f39c12;
    color: white;
}

.order-table .btn-ready {
    background-color: #27ae60;
    color: white;
}

.order-table .btn-cooking:hover,
.order-table .btn-ready:hover {
    opacity: 0.9;
}



</style>
