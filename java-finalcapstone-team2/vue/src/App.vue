<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link class="image-link" v-bind:to="{ name: 'home' }">
        <img id="logo" src="src/assets/logo.png" alt="logo.png">
      </router-link>
      <div id="login-info" v-if="$store.state.token != ''">
      <router-link :to="{name: 'editProfile'}"  class='welcome'><p>{{ this.welcome }}</p></router-link> 

      <button class="notification-button" @click.prevent="toggleNotifications">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            width="24"
            height="24"
          >
            <path fill="none" d="M0 0h24v24H0z"></path>
            <path fill="currentColor" d="M20 17h2v2H2v-2h2v-7a8 8 0 1 1 16 0v7zm-2 0v-7a6 6 0 1 0-12 0v7h12zm-9 4h6v2H9v-2z"></path>
          </svg>
          <span class="notification-badge" v-if="notifications.length > 0">{{ notificationCount }}</span>
      </button>
      <div v-if="showDropdown" class="notification-dropdown">
        <ul>
          <li v-for="notification in notifications" :key="notification.id" @click="removeNotification(notification.id)">
            {{ notification.message }}
          </li>
        </ul>
      </div>

      <router-link class="image-link" id="logout" v-bind:to="{ name: 'logout' }"><img src="src/assets/logout.svg" alt="Logout"></router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      notifications: [
          { id: 1, message: "You are invited to Tech Expo 2024" },
          { id: 2, message: "Your food order is Ready" },
          { id: 3, message: "Your Order is start Cooking" }
        ], 
      notificationCount: 3,
      showDropdown: false 
    };
  },
  computed: {
    welcome() {
      return this.$store.state.user != null ? "Welcome " + this.$store.state.user.firstname : "";
    }
  },
  methods: {
    toggleNotifications() {
      this.showDropdown = !this.showDropdown;
    },
    removeNotification(id) {
      this.notifications = this.notifications.filter(notification => notification.id !== id);
      this.notificationCount = this.notifications.length;
      if (this.notificationCount === 0) {
        this.showDropdown = false;
      }
    }
  }
}
</script>




<style scoped>

#nav {
  background-color: white;
  height: 50px;
  align-content: center;
  padding-left: 10px;
  padding-right: 10px;
  display: flex;
  justify-content: space-between;
}
.welcome{
  text-decoration: none;
  color: #7e91c6;
}
#login-info {

  display: flex;
  gap: 15px;
  color: #7e91c6;
  font-weight: 900;
  font-size: 30px;
}

#login-info p {
  padding: 0;
  margin: auto;
}

.image-link {
  text-align: center;
}

#logo {
  height: 45px;
}

#logout {
  font-size: 25px;
  text-decoration: none;
  text-align: center;
  color: #7e91c6;
}

#capstone-app {
  width: 100%;
  background-image: linear-gradient(white, #7e91c6);
  /* background-image: url('./assets/background.jpg'); */
  height: 100%;
  min-height: 100vh;
}

/* Styles for notification button */
.notification-button {
  background: none;
  border: none;
  padding: 15px 15px;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
}

.notification-button:hover {
  background: rgba(170, 170, 170, 0.062);
  transition: 0.5s;
}

.notification-button svg {
  color: #7E91C6;
}

.notification-badge {
  position: absolute;
  top: -5px;
  right: -5px;
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: bold;
}


.notification-dropdown {
  position: absolute;
  top: 60px;
  right: 0;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 250px;
  max-height: 300px;
  overflow-y: auto; 
  z-index: 1000; 
}

.notification-dropdown ul {
  font-size: 12px;
  font-weight: bold;
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.notification-dropdown li {
  padding: 10px;
  border-bottom: 1px solid #ddd;
  cursor: pointer;
}

.notification-dropdown li:last-child {
  border-bottom: none;
}

.notification-dropdown li:hover {
  background-color: #f5f5f5;
}


@media screen and (max-width: 400px) {
  #nav {
    background-color: white;
    height: auto;
    align-content: center;
    padding: 10px;
    gap: 10px;
    display: flex;
    flex-wrap: wrap;
  }

  #logout {
    font-size: 15px;
    text-decoration: none;
    text-align: center;
    color: #7e91c6;
  }

  #logo {
    height: 45px;
  }
}
</style>
