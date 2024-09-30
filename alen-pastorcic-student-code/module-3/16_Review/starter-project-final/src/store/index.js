import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {
      message: 'This empty Vue 3 project is already set up with routing and Vuex. Simply replace the SampleComponent and SampleView with your real code.',
      user: { 
        username: 'Ben',
      },
      products: [
        {
          id: 1,
          name: 'Pink Plushy Unicorn',
          price: 15.00,
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
          id: 2,
          name: 'Bounty Paper Towels 2pk',
          price: 8.50,
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
          id: 201,
          name: 'Flamingo shirt',
          price: 21.35,
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        }
      ],
      shoppingCart: [
        {
          productId: 201,
          qty: 1
        },
        {
          productId: 1,
          qty: 2
        }
      ]
    },
    mutations: {
      CHANGE_MESSAGE(state, msg) {
        state.message = msg;
      },
      ADD_TO_CART(state, product) {
        let thisProduct = state.shoppingCart.find((sp) => {
          return sp.productId == product.id;
        });
        if (thisProduct) {
          thisProduct.qty++;
        }
        else {
          let newShoppingProduct = {
            productId: product.id,
            qty: 1
          };
          state.shoppingCart.push(newShoppingProduct);

        }
      },
      REMOVE_FROM_CART(state, product) {
        let thisProduct = state.shoppingCart.find((sp) => {
          return sp.productId == product.id;
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
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  });
}
