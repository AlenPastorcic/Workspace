import axios from "axios";

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {

    createOrder(order) {
        alert('Creating order...');
        return http.post('/api/orders', order)
    },

    updateOrder(order){
        return http.put(`/api/orders/${order.user_id}`, order)
    },

    getOrders() {
        return http.get('/api/orders');
      }
}