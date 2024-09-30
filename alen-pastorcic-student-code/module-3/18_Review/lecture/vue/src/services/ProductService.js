import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  getProducts() {
    return http.get('/products');
  },

  getProduct(productId) {
    return http.get(`/products/${productId}`);
  },

  getOrders() {
    return http.get('/orders')
  },

  addOrder(order) {
    return http.post('/orders', order);
  }


}
