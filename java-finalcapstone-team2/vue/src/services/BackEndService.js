import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
  getEvent(){
    let userId = JSON.parse(localStorage.getItem('user')).id;
    return http.get(`/api/events/user/${userId}`);
  },
  createEvent(event) {
    return http.post('/api/events', event);
  },
  updateEvent(event){
    return http.put(`/api/events/${event.eventId}`, event);
  },
  getUsers(){
    return http.get(`/users`);
  },
  deleteEvent(id){
    return http.delete(`/api/events/${id}`);
  },
  getMenuItems(){
    return http.get('/api/menuitems');
  },
  getMenuItemsByEventId(eventId){
    return http.get(`/api/menus/${eventId}`);
  },
  addAttendeeToEvent(attendeeDto){
    return http.post('/api/events/addAttendee', attendeeDto);
  },
  createOrders(orders){
    return http.post('/api/orders', orders);
  },
  getOrdersByEvent(eventId){
    return http.get(`/api/orders/event/${eventId}`);
  },
  getOrderByUserAndEvent(eventId, userId){
    return http.get(`/api/orders/event/${eventId}/${userId}`)
  },
  updateOrder(orderId, order){
    return http.put(`/api/orders/${orderId}`, order);
  },
  createMenu(menus){
    return http.post('/api/menus/createMenu', menus)
  }

}