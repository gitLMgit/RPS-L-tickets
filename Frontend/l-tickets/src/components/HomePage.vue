<template>
  <div class="event-grid">
    <div class="event-card" v-for="(event, index) in filteredEvents" :key="index">
      <div class="card-content">
        <div class="event-details">
          <h2>{{ event.title }}</h2>
          <p><strong>Datum:</strong> {{ formatDate(event.date) }}</p>
          <p><strong>Lokacija:</strong> {{ event.location.name }}, {{ event.location.address }}</p>
        </div>
        <div v-if="isLogged()">
          <button @click="buyTickets(event)">Kupi karte</button>
        </div>
        <button @click="showDetails(event)">Detalji</button>
      </div>
    </div>
    <div v-if="showEventDetails" class="event-details-popup">
      <h2>{{ selectedEvent.title }}</h2>
      <p><strong>Datum:</strong> {{ formatDate(selectedEvent.date) }}</p>
      <p><strong>Lokacija:</strong> {{ selectedEvent.location.name }}, {{ selectedEvent.location.address }}</p>
      <p><strong>Opis:</strong> {{ selectedEvent.description }}</p>
      <button @click="closeDetails">Zatvori</button>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';
import { format } from 'date-fns';
import { isLoggedIn } from '../session.js';

export default {
  props: {
    selectedCategory: {
      type: String,
      default: ''
    },
    searchQuery: {
      type: String,
      default: ''
    },
    isLoggedIn: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      events: [],
      showEventDetails: false,
      selectedEvent: {}
    };
  },
  computed: {
    filteredEvents() {
      return this.events.filter(event => {
        const matchesCategory = this.selectedCategory  ? event.category.category === this.selectedCategory : true;
        if (this.selectedCategory == undefined)
          this.fetchEvents();
        const matchesLocationSearch = this.searchQuery ? event.location.name.toLowerCase().includes(this.searchQuery.toLowerCase()) : true;
        const matchesTitleSearch = this.searchQuery ? event.title.toLowerCase().includes(this.searchQuery.toLowerCase()) : true;
        const matchesSearch = matchesLocationSearch || matchesTitleSearch;
        return matchesSearch && matchesCategory;
      });
    }
  },
  methods: {
    async fetchEvents() {
      try {
        const response = await axios.get('/permitAll/getAllEvent');
        this.events = response.data;
      } catch (error) {
        console.error('Failed to fetch events:', error);
      }
    },
    isLogged() {
      return isLoggedIn(); // Poziv funkcije za proveru prijave iz session.js modula
    },
    formatDate(date) {
      return format(new Date(date), 'dd.MM.yyyy');
    },
    buyTickets(event) {
      // Prosleđujemo odabrani događaj na stranicu za kupovinu karata preko Vue Router-a
      this.$router.push({ name: 'BuyTickets', params: {id: event.idEvent } });
    },
    leaveComment(index) {
      const event = this.filteredEvents[index];
      console.log(`Rating for ${event.title}: ${event.rating}`);
      console.log(`Comment for ${event.title}: ${event.comment}`);
      event.rating = 0;
      event.comment = '';
    },
    showDetails(event) {
      this.selectedEvent = event;
      this.showEventDetails = true;
    },
    closeDetails() {
      this.showEventDetails = false;
    }
  },
  mounted() {
    this.fetchEvents();
  }
};
</script>

<style scoped>
.event-grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.event-card {
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  margin: 16px;
  overflow: hidden;
  width: calc(33.33% - 50px);
  transition: transform 0.2s ease-in-out;
}

.event-card:hover {
  transform: translateY(-5px);
}

.card-content {
  padding: 16px;
}

.event-card img {
  max-width: 100%;
  height: auto;
  display: block;
  border-radius: 8px 8px 0 0;
}

.event-details {
  margin-top: 16px;
}

.event-details h2 {
  font-size: 1.5em;
  margin-bottom: 8px;
}

.event-details p {
  margin-bottom: 8px;
}

.rating-section {
  margin-top: 16px;
}

.rating-section label {
  margin-right: 8px;
}

.rating-section select,
.rating-section textarea {
  margin-bottom: 8px;
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
  font-size: 1em;
}

.rating-section button {
  background-color: #009688;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.rating-section button:hover {
  background-color: #00796b;
}

.event-card button {
  display: block;
  width: 100%;
  margin-top: 16px;
  background-color: #009688;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.event-card button:hover {
  background-color: #00796b;
}

.event-details-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #b8b7b7;
  padding: 16px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

.event-details-popup h2 {
  font-size: 1.5em;
  margin-bottom: 16px;
}

.event-details-popup p {
  margin-bottom: 8px;
}

.event-details-popup button {
  background-color: #009688;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
  margin-top: 10px;
}

.event-details-popup button:hover {
  background-color: #00796b;
}
</style>
