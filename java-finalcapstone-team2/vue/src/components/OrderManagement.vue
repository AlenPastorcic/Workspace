<template>
    <div>
      <h1>{{ isEditing ? "Update Order" : "Create Order" }}</h1>
      <form @submit.prevent="submitOrder">
        
        <div>
          <label for="user_id">User ID:</label>
          <input type="text" v-model="order.user_id" id="user_id" required />
        </div>
        <div>
          <label for="product_id">Product ID:</label>
          <input type="text" v-model="order.product_id" id="product_id" required />
        </div>
        <div>
          <label for="quantity">Quantity:</label>
          <input type="number" v-model="order.quantity" id="quantity" required />
        </div>
        <button type="submit">{{ isEditing ? "Update Order" : "Create Order" }}</button>
      </form>
  
      <h2>Orders</h2>
      <ul>
        <li v-for="order in orders" :key="order.id">
          User ID: {{ order.user_id }} | Product ID: {{ order.product_id }} | Quantity: {{ order.quantity }}
          <button @click="editOrder(order)">Edit</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import OrderService from '../services/OrderService';
  
  export default {
    data() {
      return {
        order: {
          user_id: '',
          product_id: '',
          quantity: 1,
        },
        isEditing: false,
        orders: [], 
      };
    },
    methods: {
      async submitOrder() {
        try {
          if (this.isEditing) {
            await OrderService.updateOrder(this.order);
            alert("Order updated successfully!");
          } else {
            await OrderService.createOrder(this.order);
            alert("Order created successfully!");
          }
          this.fetchOrders(); 
        } catch (error) {
          alert("There was an error processing your request.");
          console.error(error);
        }
      },
      async fetchOrders() {
        try {
          const response = await OrderService.getOrders();
          this.orders = response.data;
        } catch (error) {
          console.error("Failed to fetch orders", error);
        }
      },
      editOrder(order) {
        this.order = { ...order };
        this.isEditing = true;
      }
    },
    mounted() {
      this.fetchOrders();
    }
  };
  </script>
  
  <style>
  form {
    max-width: 400px;
    margin: 0 auto;
  }
  form div {
    margin-bottom: 15px;
  }
  label {
    display: block;
    margin-bottom: 5px;
  }
  input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
  }
  button {
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  li {
    margin-bottom: 10px;
  }
  </style>