<template>
    <div class="mef-container">
        <h1>CREATE EVENT</h1>
        <form v-on:submit.prevent="sendToCreateForm">
        <div id="mini-event-form">
            <div class="form-input-group">
                <input class="event-form-input" type="text" placeholder="Event Title" v-model="event.title"/>
            </div>
            <div class="form-input-group">
                <input class="event-form-input" type="text" placeholder="Location" v-model="event.location"/>
            </div>
            <div class="form-input-group">
                <input class="event-form-input" type="datetime-local" id="event_date" v-model="event.event_date" />
            </div>
        </div>
        <button class="button-create" type="submit">
            <strong>CREATE</strong>
            <div id="container-stars">
                <div id="stars"></div>
            </div>

            <div id="glow">
                <div class="circle"></div>
                <div class="circle"></div>
            </div>
        </button>
        </form>
    </div>
</template>


<script>
export default{
    data(){
        return{
            event: {
                eventId: 0,
                title: '',
                description: '',
                eventDate: '',
                location: '',
                imgUrl: '',
                hostId: '',
                attendees: [],
                menu: [],
                chefId: ''
            }
        }
    },
    methods:{
        sendToCreateForm(){
            const eventQuery = encodeURIComponent(JSON.stringify(this.event));
            this.$router.push({ name: 'createEvent', query:{event:eventQuery} });
        }
    }
}
</script>

<style scoped>
    .mef-container{
        display: flex;
        flex-wrap: wrap;
    }
    .mef-container h1{
        color: #2549ab;
    }
    .form-input-group {
        margin-bottom: 1rem;
    }
    label {
        margin-right: 0.5rem;
    }
    #mini-event-form{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        gap: 50px;
        justify-content: center;
    }
    .mef-container{
        text-align: center;
        padding: 15px;
        border-bottom: 1px solid #7E91C6;
    }

    .button-create {
    padding: 10px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
 

  

</style>