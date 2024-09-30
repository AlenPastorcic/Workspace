<template>
  <div class="homeContainer">
  <about-char-code/>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="user.email" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" v-model="user.firstname" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" v-model="user.lastname" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
  </div>
</template>

<script>
import AboutCharCode from '../components/aboutCharCode.vue';
import authService from '../services/AuthService';

export default {
  components:{
    AboutCharCode
  },
  props:['event'],
  data() {
    return {
      user: {
        username: '',
        email: '',
        firstname: '',
        lastname: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 15px;
}
.form-input-group label {
    flex: 0 0 150px;
    text-align: right;
    margin-right: 10px;
}
.form-input-group input {
    padding: 8px;
    border: 1px solid #7E91C6;
    border-radius: 4px;
}
#register{
  text-align: center;
  width: 500px;
  border: 1px solid #7E91C6;
  margin: 10px;
  padding: 15px;
}
.homeContainer{
 display: flex;
 gap: 25px;
 justify-content: space-between;
}
@media screen AND (max-width: 1100px) {
  .homeContainer{
    display: flex;
    flex-direction: column;
    gap: 25px;
    align-items: center;
}
}
@media screen AND (max-width: 550px) {
  .homeContainer{
    display: flex;
    flex-direction: column;
    gap: 25px;
    align-items: center;
  }
  #register{
    text-align: center;
    width: 300px;
    border: 1px solid #7E91C6;
    margin: 5px;
    padding: 5px;
  }
  .form-input-group label {
    flex: 0 0 100px;
    text-align: right;
    margin-right: 10px;
  }
  .form-input-group input {
    padding: 8px;
    border: 1px solid #7E91C6;
    border-radius: 4px;
  }
}
</style>
