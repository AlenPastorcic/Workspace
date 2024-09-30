import { createRouter as _createRouter, createWebHistory } from 'vue-router';
import SampleView from '../views/SampleView.vue';

const routes = [
  {
    path: '/',
    name: 'sample-view',
    component: SampleView
  }

];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
