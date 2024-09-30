<template>
    <div>
      <h1>Order Management</h1>
      <div>
        <h2>{{ isEditing ? "Update Order" : "Create Order" }}</h2>
        <form @submit.prevent="submitOrder">
          <div>
            <label for="product_id">Menu Choice:</label>
            <input type="text" v-model="order.product_id" id="product_id" required />
          </div>
          <div>
            <label for="comment">Comments:</label>
            <input type="text" v-model="order.comment" id="comment" required />
          </div>
          <button type="submit">{{ isEditing ? "Update Order" : "Create Order" }}</button>
        </form>
      </div>
      
  
      <div>
        <h2>Orders</h2>
        <order-card v-for="or in this.orders" :key="or.order_id" :order = 'or' :class="{ inProgress: order.status === 'In Progress'}"/>
      </div>
    </div>
  </template>
  
  <script>
  import OrderService from '../services/OrderService'; 
  import orderCard from '../components/orderCard.vue';
  export default {
    components: { orderCard },
    data() {
      return {
        order: {
          menu_item_id: '',
          comment: '',
        },
        isEditing: false,
        orders: [

{
order_id: 1,
menu_item_id: 1,
user_id: 1,
comment: "Severe allergy to gluten",
status: 'In Progress',
event_id: ''
},

{
order_id: 2,
menu_item_id: 2,
user_id: 2,
comment: "Please put all sauces on the side",
status: 'In Progress',
event_id: ''
},

{
order_id: 3,
menu_item_id: 3,
user_id: 3,
comment: "Can I sub for pancakes?",
status: 'In Progress',
event_id: '',
},

{
order_id: 4,
menu_item_id: 4,
user_id: 4,
comment: "Needs a vegan preparation",
status: 'In Progress',
event_id: '',
},

{
order_id: 5,
menu_item_id: 5,
user_id: 5,
comment: "Light on the pepper, please",
status: 'In Progress',
event_id: '',
},

{
order_id: 6,
menu_item_id: 6,
user_id: 6,
comment: "No comment",
status: 'In Progress',
event_id: '',
},

{
order_id: 7,
menu_item_id: 7,
user_id: 7,
comment: "Extra pickles",
status: 'In Progress',
event_id: '',
},

{
order_id: 8,
menu_item_id: 8,
user_id: 8,
comment: "Dislike pork, please substitute",
status: 'In Progress',
event_id: '',
},

{
order_id: 9,
menu_item_id: 9,
user_id: 9,
comment: "Extra cheesey if you pleasey",
status: 'In Progress',
event_id: '',
},

{
order_id: 10,
menu_item_id: 10,
user_id: 10,
comment: "Make sure the curds are squeaky",
status: 'In Progress',
event_id: '',
}


],
      }
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
      
      editOrder(order) {
        this.order = { ...order };
        this.isEditing = true;
      }
    },
    mounted() {
      OrderService.getOrders()
      .then(response => {
        console.log(response.data);
        this.orders = response.data;
      })
      .catch(error => {
        console.error("There was an error fetching the orders!", error);
      });
    }
  };
  </script>
  
  <style scoped>

  h1{
    text-align: center;
  }

  h2{
    text-align: center;
  }

.order-cards-container {
  gap: 20px;
  display: flex;
  flex-wrap: wrap ;
  justify-content: space-between;
}

.order-card {
  flex: 1 1 30%; 
  box-sizing: border-box;
  margin-bottom: 20px; 
}

  form {
    background-color: #9dcad2;
    padding: 20px;
    border-radius: 10px;
    max-width: 400px;
    margin: auto;
}

label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
    color: #333;
    font-size: 1.2em;
}

textarea,
input[type="text"] {
    width: 90%;
    padding: 10px;
    margin-bottom: 20px;
    border: 2px solid #333;
    border-radius: 10px;
    background-color: #fffcdb;
    box-shadow: 3px 3px 0px #00000040;
    font-size: 1.1em;
}

button {
    background-color: #fffcdb;
    color: rgb(1, 1, 1);
    padding: 10px 20px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-size: 1.2em;
    box-shadow: 3px 3px 0px #00000040;
}

button:hover {
    background-color: #f57c00;
}

body {
    background-color: #b3e5fc;
    font-family: 'Courier New', Courier, monospace;
}
  </style>
  