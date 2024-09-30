<template>
  <div class="loading" v-if="isLoading">
    <img src="../assets/cat_loading.gif" />
  </div>
  <div class="main-event-form-container" v-else>
  <button class="close-form-button" @click="closeForm">X</button>
    <h2 class="main-event-form-title">Create New Event</h2>
    <form style="width: 100%;"  @submit.prevent="createEvent">
      <div class="form-group">
        <label for="title">Title</label>
        <input class="main-event-form-input" type="text" id="title" v-model="newEvent.title" required />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea class="main-event-form-input" id="description" v-model="newEvent.description" required></textarea>
      </div>
      <div class="form-group">
        <label for="event_date">Event Date</label>
        <input class="main-event-form-input" type="datetime-local" id="event_date" v-model="newEvent.eventDate" required />
      </div>
      <div class="form-group">
        <label for="location">Location</label>
        <input class="main-event-form-input" type="text" id="location" v-model="newEvent.location" required />
      </div>

      <div class="form-group">
        <label for="img_upload">Upload Image</label>
        <input class="main-event-form-input" type="file" id="img_upload" @change="onFileChange" accept="image/*" required />
      </div>
      <div v-if="newEvent.imgUrl" class="form-group">
        <label>Image Preview</label>
        <img :src="newEvent.imgUrl" alt="Uploaded Image" class="image-preview" />
      </div>


    <div class="form-group">
      <label for="attendees">Add Attendee</label>
      <select  class="main-event-form-input" id="attendees" v-model="selectedAttendee">
        <option value="" disabled>Select an attendee</option>
        <option v-for="user in availableUsers" :key="user.id" :value="user">{{ user.username }}</option>
      </select>
      <button class="add-attendee-button" v-on:click.prevent="addAttendee">Add Attendee</button>
    </div>
    <div v-if="newEvent.attendees.length > 0" class="form-group">
      <h3>Attendees:</h3>
      <ul>
        <li v-for="attendee in newEvent.attendees" :key="attendee.id">{{ attendee.username }}&nbsp;&nbsp;&nbsp;<a href="#" style="color: red;" @click.prevent="removeAttendees(attendee.id)">remove</a></li>
      </ul>
    </div>
    <div class="form-group">
      <label for="attendees">Add Attendee by Email</label>
      <input class="main-event-form-input" type="email" v-model="attendeeEmail"/>
      <button class="add-attendee-button" v-on:click.prevent="addAttendeeByEmail">Add Email</button>
    </div>
    <div v-if="newEvent.attendeesEmailList.length > 0" class="form-group">
      <h3>Attendees Email:</h3>
      <ul>
        <li v-for="mail in newEvent.attendeesEmailList" :key="mail">{{ mail }}</li>
      </ul>
    </div>
    <div class="form-group">
        <label for="chef">Select Chef</label>
        <div class="selection-value-container">
          <select  class="main-event-form-input" id="chef" v-model="this.newEvent.chefId" @change.prevent="assignChef()">
          <option value="" disabled>Select a chef</option>
          <option v-for="user in availableUsers" :key="user.id" :value="user.id">{{ user.username }}</option>
          </select>
          <p v-if="selectedChef"><strong>Chef: </strong>{{ selectedChef.username }}</p>
        </div>
    </div>
      
    <button class="main-event-form-button-confirm" type="submit" @click.prevent="createEvent">{{this.event.eventId == 0 ? 'Create Event' : 'Edit Event'}}</button>
    </form>
  </div>
  </template>
  
  <script>
  import backEndService from '../services/BackEndService';
  export default {
    props:['event'],
    created(){
      backEndService.getUsers().then(response => {
          this.users = response.data;
          this.isLoading = false;
        });
    },
    data() {
      return {
        newEvent: {
          eventId: this.event.eventId,
          title: this.event.title,
          description: this.event.description,
          eventDate: this.event.eventDate,
          location: this.event.location,
          imgUrl: this.event.imgUrl,
          hostId: JSON.parse(localStorage.getItem('user')).id,
          attendees: Array.isArray(this.event.attendees) 
          ? this.event.attendees 
          : Object.values(this.event.attendees || {}),
          
          menu: Array.isArray(this.event.menu) 
          ? this.event.menu 
          : Object.values(this.event.menu || {}),

          chefId: this.event.chefId,

          attendeesEmailList: []
        },
        selectedChef: null,
        selectedAttendee: null,
        isLoading: true,
        attendeeEmail: null,
        users:[]
      };
    },
    computed: {
      availableUsers() {
        let filteredUser = this.users.filter(user => 
                            !this.newEvent.attendees.some(attendee => attendee.id === user.id));
        let hostUser = this.$store.state.user;
        filteredUser = filteredUser.filter( user => user.id != hostUser.id);
        if(this.selectedChef != null){
          filteredUser = filteredUser.filter(user => user.id != this.selectedChef.id);
        }
        return filteredUser;
      }
    },
    methods: {
      createEvent() {
        if(this.$store.state.token === ''){
          this.$router.push({name: 'register'})
        }
        if(this.event.eventId === 0){
          backEndService.createEvent(this.newEvent).then(response => {
              this.$router.push({ name: 'home'});
          });
        } else{
          backEndService.updateEvent(this.newEvent).then(response => {
            this.$router.push({ name: 'home'});
          });
        }
        this.resetForm();
      },
      closeForm(){
        this.$router.push({ name: 'home'});
        this.resetForm();
      },
      addAttendee() {
        if (this.selectedAttendee) {
          this.newEvent.attendees.push(this.selectedAttendee);
          this.selectedAttendee = null;
        }
      },
      addAttendeeByEmail() {
        if(this.newEvent.attendeesEmailList.includes(this.attendeeEmail)){
          alert("Email already included!!!");
          this.attendeeEmail = null;
        }else if (this.attendeeEmail) {
          this.newEvent.attendeesEmailList.push(this.attendeeEmail);
          this.attendeeEmail = null;
        }
      },
      assignChef(){
        this.selectedChef = this.users.find(user => user.id === this.newEvent.chefId);
      },
      onFileChange(event) {
        const file = event.target.files[0];
        if (file && file.type.startsWith('image/')) {
          this.newEvent.imgUrl = URL.createObjectURL(file);
        } else {
          alert('Please select a valid image file.');
        }
      },
      resetForm() {
        this.newEvent = {
          title: '',
          description: '',
          eventDate: '',
          location: '',
          imgUrl: '',
          hostId: JSON.parse(localStorage.getItem('user')).id,
          attendees: [],
          menu: [],
          chefId: '',
          attendeesEmailList: []
        };
      },
      formatDate(dateTime) {
        return new Date(dateTime).toLocaleString();
      },
      removeAttendees(id){
        this.newEvent.attendees = this.newEvent.attendees.filter(attendee => attendee.id !== id);
      }
    }
  };
  </script>
  
  <style>
  .form-group {
    margin-bottom: 16px;
    padding-left: 25px;
    padding-right: 25px;
  }
  
  label {
    display: block;
    margin-bottom: 8px;
  }

  .selection-value-container{
    display: flex;
    gap: 25px;
  }

  .selection-value-container p{
    font-size: 20px;
    margin: 0;
    padding: 0;
    align-content: center;
  }
  
  /* input, textarea {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
  } */
  
  
  .form-group label{
    font-weight: 700;
    font-size: 18px;
  }


  button:hover {
    background-color: lightgray;
  }

  .image-preview {
  max-width: 100%;
  height: auto;
  margin-top: 10px;
} 

  .main-event-form-container {
  --input-focus: #2d8cf0;
  --font-color: #323232;
  --font-color-sub: #666;
  --bg-color: beige;
  --main-color: black;
  padding: 20px;
  background: lightblue;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  gap: 20px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  position: relative;
  max-width: 600px;
  margin: 0 auto;
  padding: 16px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.main-event-form-title {
  width: 100%;
  text-align: center;
  color: var(--font-color);
  font-weight: 900;
  margin-top: 0;
  margin-bottom: 25px;
}

.main-event-form h3 {
  color: var(--font-color-sub);
  font-weight: 600;
  font-size: 17px;
}

.main-event-form-input {
  width: 250px;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 15px;
  font-weight: 600;
  color: var(--font-color);
  /* padding: 5px 10px; */
  outline: none;
}

.main-event-form-input::placeholder {
  color: var(--font-color-sub);
  opacity: 0.8;
}

.main-event-form-input:focus {
  border: 2px solid var(--input-focus);
}


.main-event-form-button-confirm {
  display: block;
  justify-content: center;
  margin: 50px auto 25px auto;
  width: auto;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 17px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
  padding: 10px 20px;
}

.add-attendee-button{
  width: auto;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 17px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
  margin-left: 10px;
}

</style>
  