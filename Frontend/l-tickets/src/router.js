import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import MyAccountPage from './MyAccountPage.vue';
import BuyTicketsPage from './components/BuyTicketsPage.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: App
  },
  {
    path: '/buy-tickets',
    name: 'BuyTickets',
    component: BuyTicketsPage,
  },
  {
    path: '/my-account',
    name: 'MyAccount',
    component: MyAccountPage,

  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
