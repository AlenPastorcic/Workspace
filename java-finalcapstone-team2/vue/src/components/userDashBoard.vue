<template>
  <div class="loading" v-if="isLoading">
    <img src="../assets/cat_loading.gif" />
    <!-- <iframe src="https://giphy.com/embed/GeP8zNzIp9DNsXYvWa" width="480" height="480" style="" frameBorder="0" class="giphy-embed" allowFullScreen></iframe> -->
  </div>
  <div v-else>
    <div v-if="selectedEvent" class="event-detail-container">
      <EventDetail :event="selectedEvent" :eventCategory="selectedEventCategory" @close-details="closeDetails" />
    </div>
    <div v-else>
      <div class="brutalist-container">
        <input
          type="text"
          v-model="searchTerm"
          placeholder="Search..."
          class="brutalist-input smooth-type"
        />
      </div>
      <hr>
      <h1 class="event-h1" @click="showHideEvent(1)">Hosted Event</h1>
      <p v-if="hostEvents.length < 1" class="centered-brown-text">You don’t have any host events!</p>
      <div v-if="showHost" class="event-list">
        <EventCard
          v-for="event in filteredHostEvents"
          :key="event.title"
          :event="event"
          eventCategory="host"
          @show-details="showDetails"
        />
      </div>
      <hr>
      <h1 class="event-h1" @click="showHideEvent(2)">Chef</h1>
      <p v-if="chefEvents.length < 1" class="centered-brown-text">You don’t have any chef events!</p>
      <div v-if="showChef" class="event-list">
        <EventCard
          v-for="event in filteredChefEvents"
          :key="event.title"
          :event="event"
          eventCategory="chef"
          @show-details="showDetails"
        />
      </div>
      <hr>
      <h1 class="event-h1" @click="showHideEvent(3)">You are invited to:</h1>
      <a href="#" @click.prevent="attendEvent"  class="event-invite-code">Do you have invitation code? Click Here!!!</a>
      <p v-if="attendeeEvents.length < 1" class="centered-brown-text">You don’t have any events to attend!</p>
      <div v-if="showAttendee" class="event-list">
        <EventCard
          v-for="event in filteredAttendeeEvents"
          :key="event.title"
          :event="event"
          eventCategory="attendee"
          @show-details="showDetails"
        />
      </div>
  </div>
  </div>
</template>
  
  <script>
  import EventDetail from './eventDetail.vue';
  import backEndService from '../services/BackEndService';
  import EventCard from './eventCard.vue';
  import Swal from 'sweetalert2';
  export default {
    name: 'App',
    components: {
      EventCard,
      EventDetail
    },
    data() {
      return {
        hostEvents: [],
        chefEvents: [],
        attendeeEvents: [],
        isLoading: true,
        selectedEvent: null,
        selectedEventCategory: null,
        showHost: true,
        showChef: true,
        showAttendee: true,
        searchTerm: '',
        attendeeDto: {
          eventId: '',
          attendeeId: this.$store.state.user.id
        }
      };
    },
    created(){
        this.getEvents();
    },
    methods: {
      getEvents(){
        backEndService.getEvent().then(response => {
          this.hostEvents = response.data.hostEvent;
          this.chefEvents = response.data.chefEvent;
          this.attendeeEvents = response.data.attendeeEvent;
          this.isLoading = false;
        });
      },
      showDetails(event, eventCategory) {
        this.selectedEvent = event;
        this.selectedEventCategory = eventCategory;
      },
      closeDetails() {
        this.selectedEvent = null;
        this.getEvents();
      },
      showUser(){
        console.log(this.userEvent);
      },
      showHideEvent(num){
        switch(num) {
          case 1:
            this.showHost = !this.showHost;
            break;
          case 2:
            this.showChef = !this.showChef;
            break;
          case 3:
            this.showAttendee = !this.showAttendee;
            break;
        }
      },
      attendEvent(){
        Swal.fire({
        title: 'Enter your invitation Code',
        input: 'text',
        inputPlaceholder: 'Code...',
        showCancelButton: true,
        confirmButtonText: 'Submit',
        cancelButtonText: 'Cancel'
        }).then((result) => {
          if (result.isConfirmed) {
            alert(result.value)
            this.attendeeDto.eventId =  parseInt(result.value);
            backEndService.addAttendeeToEvent(this.attendeeDto).then(response => {
              this.getEvents();
              Swal.fire({
                    title: 'Event Successfully Added',
                    icon: 'success',
                    confirmButtonText: 'OK'
                  });
            })
            .catch(error => {
              console.log(error);
                Swal.fire({
                    title: 'Invalid Invitation Code!',
                    icon: 'error',
                    confirmButtonText: 'OK'
                  });
            })
          }
        });
      }
    },
    computed:{
      filteredHostEvents(){
        return this.hostEvents.filter(event => {
          return event.title.toLowerCase().includes(this.searchTerm.toLocaleLowerCase());
        });
      },
      filteredChefEvents(){
        return this.chefEvents.filter(event => {
          return event.title.toLowerCase().includes(this.searchTerm.toLocaleLowerCase());
        });
      },
      filteredAttendeeEvents(){
        return this.attendeeEvents.filter(event => {
          return event.title.toLowerCase().includes(this.searchTerm.toLocaleLowerCase());
        });
      },
      
    }
  };
  </script>
  
  <style>
  .event-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
  }
  
  .event-detail-container {
    margin: 16px;
  }
  
  .event-card {
    width: calc(33.333% - 32px);
    box-sizing: border-box;
    margin-bottom: 32px;
  }
  
  @media (max-width: 768px) {
    .event-card {
      width: calc(50% - 32px);
    }
  }
  
  @media (max-width: 480px) {
    .event-card {
      width: 100%;
    }
  }
  .event-h1{
    text-align: center;
    cursor: pointer;
  }

  .event-invite-code{
    text-align: center;
    display: block;
  }

  .centered-brown-text {
  text-align: center;
  color: brown;
  font-size: 16px;
  }

  </style>
  