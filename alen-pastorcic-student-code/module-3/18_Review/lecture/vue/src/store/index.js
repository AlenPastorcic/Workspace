import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      /* User Authentication section */
      token: currentToken || '',
      user: currentUser || {},

      /* Our demo data section */
      notification: null,
      products: [],
      shoppingCart: [],
    },
    mutations: {
      SET_PRODUCTS(state, products) {
        state.products = products;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_NOTIFICATION(state, notification) {
        // Clear the current notification if one exists
        if (state.notification) {
          this.commit('CLEAR_NOTIFICATION');
        }

        if (typeof notification === 'string') {
          // If only a string was sent, create a notification object with defaults
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else {
          // Else add default values if needed
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        // Set the notification in state
        state.notification = notification;

        // Clear the message after timeout (see https://developer.mozilla.org/en-US/docs/Web/API/setTimeout)
        notification.timer = window.setTimeout(() => {
          this.commit('CLEAR_NOTIFICATION');
        }, notification.timeout);
      },

      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
      ADD_TO_CART(state, product) {
        let thisProduct = state.shoppingCart.find((sp) => {
          return sp.productId == product.productId;
        });
        if (thisProduct) {
          thisProduct.qty++;
        }
        else {
          let newShoppingProduct = {
            productId: product.productId,
            qty: 1
          };
          state.shoppingCart.push(newShoppingProduct);

        }
      },
      REMOVE_FROM_CART(state, product) {
        let thisProduct = state.shoppingCart.find((sp) => {
          return sp.productId == product.productId;
        });

        if (thisProduct) {
          if (thisProduct.qty <= 1) {
            state.shoppingCart.pop(thisProduct);
          }
          else {
            thisProduct.qty--;
          }
        }
      }
    },
  });
  return store;
}
