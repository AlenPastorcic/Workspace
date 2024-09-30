<template>
    <div class="event-detail" v-if="event">
      <div id="event-button-group">
        <button class="close-button" @click="$emit('close-details')">Close</button>
        <button v-if="eventCategory === 'host'" class="close-button" @click="editEvent">Edit</button>
        <button v-if="eventCategory === 'host' || eventCategory === 'chef'" class="close-button" @click="editMenu">Menu</button>
        <button v-if="eventCategory === 'attendee' || eventCategory === 'host'" class="close-button" @click="makeOrder">Order</button>
        <button v-if="eventCategory === 'chef'" class="close-button" @click="manageOrder">Manage Order</button>
        <button v-if="eventCategory === 'host'" class="close-button" @click="deleteEvent">Delete</button>
      </div>

      <h2>{{ event.title }}</h2>
      <p>{{ event.description }}</p>
      <p><strong>Date:</strong> {{ formatDate(event.eventDate) }}</p>
      <p><strong>Location:</strong> {{ event.location }}</p>
      <p><strong>Host:</strong> {{ event.host.firstname + ' ' + event.host.lastname }}</p>
      <p><strong>Chef:</strong> {{ event.chef.firstname + ' ' + event.chef.lastname }}</p>
      
      <p><strong>Attendees: </strong></p>
      <ul class="attendees-list" v-if="Object.keys(event.attendeesList).length > 0">
        <li v-for="([key, attendee]) in Object.entries(event.attendeesList)" :key="key">
           {{ attendee.firstname + ' ' + attendee.lastname }}
        </li>
      </ul>
      <p v-else class="no-attendees"> No attendees available. </p>

      <p><strong>Menu: </strong></p>
      <ul class="attendees-list" v-if="Object.keys(event.menu).length > 0">
        <li v-for="([key, item]) in Object.entries(event.menu)" :key="key">
           {{ item.title }}
        </li>
      </ul>
      <p v-else class="no-attendees"> No Menu available. </p>

      <img :src="event.imgUrl" alt="Event Image" class="event-detail-image" />
      

    </div>
  </template>
  
  <script>
import BackEndService from '../services/BackEndService';
import Swal from 'sweetalert2';

  export default {
    props: ['event','eventCategory'],
    data(){
      return{
        currentEvent: {
          eventId: this.event.eventId,
          title: this.event.title,
          description: this.event.description,
          eventDate: this.event.eventDate,
          location: this.event.location,
          imgUrl: this.event.imgUrl,
          hostId: JSON.parse(localStorage.getItem('user')).id,
          attendees: this.event.attendeesList || [],
          menu: this.event.menu || [],
          chefId: this.event.chef.id
        }
      }
    },
    methods: {
      formatDate(dateTime) {
        return new Date(dateTime).toLocaleString();
      },
      editEvent(){
        this.$store.commit('SET_EVENT_ROLE', this.eventCategory);
        const eventQuery = encodeURIComponent(JSON.stringify(this.currentEvent));
        this.$router.push({ name: 'createEvent', query:{event:eventQuery} });
      },
      editMenu(){
        this.$store.commit('SET_EVENT_ROLE', this.eventCategory);        
        const eventQuery = encodeURIComponent(JSON.stringify(this.currentEvent));
        this.$router.push({name: 'menu', query:{event:eventQuery}});
      },
      makeOrder(){
        this.$store.commit('SET_EVENT_ROLE', this.eventCategory);        
        const eventQuery = encodeURIComponent(JSON.stringify(this.currentEvent));
        this.$router.push({name: 'placeOrder', query:{event:eventQuery}});

      },
      manageOrder(){
        this.$store.commit('SET_EVENT_ROLE', this.eventCategory);        
        const eventQuery = encodeURIComponent(JSON.stringify(this.currentEvent));
        this.$router.push({name: 'manageOrder', query:{event:eventQuery}});

      },
      deleteEvent(){
        Swal.fire({
                title: 'Are you sure?',
                text: 'You won\'t be able to revert this!',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                  BackEndService.deleteEvent(this.event.eventId)
                    .then(response => {
                      if(response.status === 200){
                        Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
                        this.$emit('close-details');
                      }
                    })
                    .catch(error => {
                      if (error.response) {
                        alert(`Error deleting event. Response received was "${error.response.statusText}".`);
                      } else if (error.request) {
                        alert('Error deleting event. Server could not be reached.');
                      } else {
                        alert('Error deleting event. Request could not be created.');
                      }
                    });
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                  Swal.fire(
                      'Cancelled',
                      'Your file is safe :)',
                      'error'
                  );
                }
            });
      }
    }
  };
  </script>
  
  <style>
  .attendees-list{
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    /* text-align: justify; */
    justify-content: center;
    /* justify-content: flex-start; */
  }
  .attendees-list li{
    width: auto;
    margin: 10px;
  }
  .event-detail {
    border: 1px solid #ccc;
    padding: 16px;
    margin: 16px;
    text-align: center;
  }
  .event-detail-image {
    width: auto\9;
    max-height: 100vh;
    max-width: 100%;
  }
  .close-button {
  --b: 3px;   /* border thickness */
  --s: .45em; /* size of the corner */
  --color: #373B44;
  
  padding: calc(.5em + var(--s)) calc(.9em + var(--s));
  color: var(--color);
  --_p: var(--s);
  background:
    conic-gradient(from 90deg at var(--b) var(--b),#0000 90deg,var(--color) 0)
    var(--_p) var(--_p)/calc(100% - var(--b) - 2*var(--_p)) calc(100% - var(--b) - 2*var(--_p));
  transition: .3s linear, color 0s, background-color 0s;
  outline: var(--b) solid #0000;
  outline-offset: .6em;
  font-size: 16px;

  border: 0;
  width: 100px;

  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.no-attendees{
  color: brown;
  
}

.close-button:hover,
.close-button:focus-visible{
  --_p: 0px;
  outline-color: var(--color);
  outline-offset: .05em;
  background-color: rgba(255, 0, 0, 0.8);
  font-weight: 900;
  color: white;
}

.close-button:active {
  background: var(--color);
  color: #fff;
}


#event-button-group {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}  
</style>