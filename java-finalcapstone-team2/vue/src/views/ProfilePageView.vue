<template>
    <div class="Profile Page">
        <h1>Edit Profile</h1>
        <form @submit.prevent="updateProfile">
            <div class="form-group">
                <label for ="username">UserName:</label>
                <input type="text" id="username" v-model="profile.username" disabled />
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" v-model="profile.firstname" id="firstName" />
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" v-model="profile.lastname" id="lastName" />
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" v-model="profile.email" required />
            </div>
      <button type="submit">Save Changes</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      profile: this.$store.state.user,
      message: ''
    };
  },
  mounted() {
    this.fetchProfileData();
  },
  methods: {
    fetchProfileData() {
      fetch('/api/get-users')
        .then(response => response.json())
        .then(data => {
          this.profile = data;
        })
        .catch(error => {
          console.error('Error fetching profile data:', error);
        });
    },
    updateProfile() {
      if (this.profile.password !== this.profile.confirmPassword) {
        this.message = 'Passwords do not match.';
        return;
      }
      fetch('/api/update-users', {
        method: 'POST',
        body: JSON.stringify(this.profile),
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then(response => response.json())
        .then(data => {
          this.message = 'Profile updated successfully!';
        })
        .catch(error => {
          this.message = 'An error occurred while updating your profile.';
        });
    },
  },
};
</script>

<style scoped>
.profile-page {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.profile-page h1 {
  text-align: center;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

input:focus {
  border-color: #007BFF;
  outline: none;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007BFF;
  color: #FFF;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

p {
  text-align: center;
  color: green;
}
</style>



    