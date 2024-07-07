import { createRouter, createWebHistory } from 'vue-router';
//import App from './App.vue';
import HomePage from './components/HomePage.vue';
import MyAccountPage from './components/MyAccountPage.vue';
import BuyTicketsPage from './components/BuyTicketsPage.vue';



const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage,
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
