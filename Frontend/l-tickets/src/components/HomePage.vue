<template>
  <div class="event-grid">
    <div class="event-card" v-for="(event, index) in filteredEvents" :key="index">
      <div class="card-content">
        <img :src="event.imageUrl" :alt="event.title">
        <div class="event-details">
          <h2>{{ event.title }}</h2>
          <p><strong>Datum:</strong> {{ event.date }}</p>
          <p><strong>Lokacija:</strong> {{ event.location }}</p>
        </div>
        <div v-if="isLoggedIn" class="rating-section">
          <label for="rating">Ocena:</label>
          <select v-model="event.rating">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>
          <label for="comment">Komentar:</label>
          <textarea v-model="event.comment"></textarea>
          <button @click="leaveComment(index)">Pošalji</button>
        </div>
        <button  @click="buyTickets">Kupi karte</button> <!--samo da se pojavi kada je neko ulogovan-->
        <button  @click="showDetails(event)">Detalji</button>
      </div>
    </div>
    <div v-if="showEventDetails" class="event-details-popup">
      <h2>{{ selectedEvent.title }}</h2>
      <p><strong>Datum:</strong> {{ selectedEvent.date }}</p>
      <p><strong>Lokacija:</strong> {{ selectedEvent.location }}</p>
      <p><strong>Opis:</strong> {{ selectedEvent.description }}</p>
      <button @click="closeDetails">Zatvori</button>
    </div>
  </div>
</template>

<script>
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
      events: [
        {
          title: 'Partizan - Vojvodina',
          date: '2024-07-10',
          location: 'Beograd',
          imageUrl: 'concert.jpg',
          description: 'Opis događaja Partizan - Vojvodina',
          rating: 0,
          comment: '',
          category: 'Utakmica'
        },
        {
          title: 'Barselona - Partizan',
          date: '2024-07-12',
          location: 'Novi Sad',
          imageUrl: 'match.jpg',
          description: 'Opis događaja Barselona - Partizan',
          rating: 0,
          comment: '',
          category: 'Utakmica'
        },
        {
          title: 'Festival uličnih svirača',
          date: '2024-07-14',
          location: 'Niš',
          imageUrl: 'festival.jpg',
          description: 'Opis događaja Festival uličnih svirača',
          rating: 0,
          comment: '',
          category: 'Festival'
        },
        // Dodajte ostale događaje po potrebi
      ],
      showEventDetails: false,
      selectedEvent: {}
    };
  },
  computed: {
    filteredEvents() {
      return this.events.filter(event => {
        const matchesCategory = this.selectedCategory ? event.category === this.selectedCategory : true;
        const matchesSearch = this.searchQuery ? event.title.toLowerCase().includes(this.searchQuery.toLowerCase()) : true;
        return matchesCategory && matchesSearch;
      });
    }
  },
  methods: {
    buyTickets() {
      this.$router.push('/buy-tickets');
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
  background-color: #ffffff;
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
