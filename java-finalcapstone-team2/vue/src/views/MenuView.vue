<template>
  <div>
    <h1>Menu</h1>

    <!-- Cart at the Top -->
    <div class="cart"><button class="close-form-button" @click="closeForm">X</button>
      <h3>Your Menu</h3>
      <ul>
        <li v-for="(item, index) in cartItems" :key="index">
          {{ item.title }}
          <!-- Remove Button -->
          <button class="btn btn-delete" @click="removeFromCart(index)">
            <span class="mdi mdi-delete mdi-24px"></span>
            <span class="mdi mdi-delete-empty mdi-24px"></span>
            <span>Delete</span>
          </button>
        </li>
        <button class="btn btn-finish" @click="finishMenu">Finish Menu</button>
      </ul>
    </div>

    <div class="loading" v-if="isLoading">
      <img src="../assets/cat_loading.gif" alt="Loading..." />
    </div>

    <div v-else id="menu-container">
      <div class="categories-container">
        <catagory-card 
          v-for="mc in menuItems" 
          :key="mc.item_id" 
          :menuItem="mc"
          @add-to-cart="addToCart"
        />
      </div>
    </div>

    <!-- Full-screen Notification -->
    <div v-if="notificationVisible" class="full-screen-notification">
      <div class="notification-message">{{ notification }}</div>
    </div>
  </div>
</template>


<script>
import catagoryCard from '../components/catagoryCard.vue';
import backEndService from '../services/BackEndService';

export default {
  components: { catagoryCard },
  data() {
    return {
      event: JSON.parse(decodeURIComponent(this.$route.query.event)),
      menuItems: [],
      cartItems: [],
      menus:[],
      isLoading: true,
    };
  },
  methods: {
    addToCart(item) {
      this.cartItems.push(item);
      const menu = {
        eventId : this.event.eventId,
        itemId : item.itemId
      }
      this.menus.push(menu);
    },
    removeFromCart(index) {
      this.cartItems.splice(index, 1);
      this.menus.splice(index, 1);
    },
    finishMenu() {
      backEndService.createMenu(this.menus)
      .then(response => {
        this.$router.push({name: 'home'});
      })
    },
    closeForm(){
      this.$router.push({ name: 'home'});
    },

  },
  created() {
    // alert(this.event.eventId)
    backEndService.getMenuItems().then(response => {
      this.menuItems = response.data;
      this.isLoading = false;
    });
    this.isLoading = true;
    backEndService.getMenuItemsByEventId(this.event.eventId)
    .then(response => {
      this.menus = response.data.menus;
      this.cartItems = response.data.menuItems;
      this.isLoading = false;
    })
  }
};
</script>



<style scoped>
#menu-container {
  align-content: center;
}

h1 {
  text-align: center;
  text-transform: uppercase;
  font-size: 26px;
  letter-spacing: 1px;
  color: #7E91C6;
}

.cart {
  position: relative;
  width: 80%; /* Shrink the width */
  max-width: 600px; /* Optional: Set a maximum width */
  margin: 15px auto; /* Center horizontally */
  padding: 10px;
  background-color: #f4f4f4;
  box-shadow: 0px 0px 10px 1px #7E91C6;
  text-align: center;
}

.cart h3 {
  margin: 0;
  font-size: 20px;
  color: #7E91C6;
}

.cart ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center; /* Center items horizontally */
}

.cart ul li {
  padding: 5px 0;
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%; /* Make sure list items take full width */
  max-width: 500px; /* Optional: Set a max width for better layout */
}

.categories-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 15px;
}

.category-card {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.category-card {
  width: calc(33.333% - 32px);
  box-sizing: border-box;
  margin-bottom: 32px;
}

@media (max-width: 768px) {
  .category-card {
    width: calc(50% - 32px);
  }
}

@media (max-width: 480px) {
  .category-card {
    width: 100%;
  }
}

.loading {
  text-align: center;
  margin-top: 20px;
}

.loading img {
  width: 50px;
  height: 50px;
}

body {
  margin: 32px;
}

.btn {
  display: flex;
  align-items: center;
  background: none;
  border: 1px solid #e0e0e0; /* Light gray border */
  height: 48px;
  padding: 0 24px 0 16px;
  letter-spacing: 0.25px;
  border-radius: 24px;
  cursor: pointer;
}

.btn:focus {
  outline: none;
}

.mdi {
  margin-right: 8px;
}

.btn-delete {
  font-size: 16px;
  color: red;
  border-color: red;
}

.btn-delete > .mdi-delete-empty {
  display: none;
}

.btn-delete:hover {
  background-color: #ffcccc; /* Light coral color */
}

.btn-delete:hover > .mdi-delete-empty {
  display: block;
}

.btn-delete:hover > .mdi-delete {
  display: none;
}

.btn-delete:focus {
  box-shadow: 0 0 0 4px #ffcccc; /* Light coral shadow */
}

.btn-finish {
  font-size: 16px;
  color: #007BFF;
  border-color: #007BFF;
  margin-top: 10px; /* Add some spacing above the button */
  display: inline-block; /* Ensure the button is inline with its container */
}

.btn-finish:hover {
  background-color: #0056b3; /* Darker blue color */
  color: white;
}

.full-screen-notification {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000; /* Ensure it's on top of other content */
}

.notification-message {
  background-color: #dff0d8; /* Light green background */
  color: #3c763d; /* Dark green text */
  padding: 20px;
  border-radius: 4px;
  font-size: 18px;
  text-align: center;
  max-width: 80%;
  width: auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
</style>
