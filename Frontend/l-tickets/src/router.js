import { createRouter, createWebHistory } from 'vue-router';
//import App from './App.vue';
import HomePage from './components/HomePage.vue';
import MyAccountPage from './components/MyAccountPage.vue';
import BuyTicketsPage from './components/BuyTicketsPage.vue';
import NewEvent from './components/NewEvent.vue';
import AdminOperation from './components/AdminOperation.vue';
import StatisticsPage from './components/StatisticsPage.vue';


const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage,
  },
  {
    path: '/buy-tickets/:id',
    name: 'BuyTickets',
    component: BuyTicketsPage,
    props: true
  },
  {
    path: '/my-account',
    name: 'MyAccount',
    component: MyAccountPage,

  },
  {
    path: '/add-event',
    name: 'NewEvenet',
    component: NewEvent
  },
  {
    path: '/admin-page',
    name: 'AdminOperation',
    component: AdminOperation,
  },
  {
    path: '/statistics',
    name: 'StatisticsPage',
    component: StatisticsPage
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
