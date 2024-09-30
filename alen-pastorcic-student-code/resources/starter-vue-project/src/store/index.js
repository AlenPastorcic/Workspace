import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {
      message: 'This empty Vue 3 project is already set up with routing and Vuex. Simply replace the SampleComponent and SampleView with your real code.'
    },
    mutations: {
      CHANGE_MESSAGE(state, msg) {
        state.message = msg;
      },
    },
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  });
}
