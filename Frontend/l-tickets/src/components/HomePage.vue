<template>
  <div class="event-grid">
      <div class="event-item" v-for="(event, index) in filteredEvents" :key="index">
        <img :src="event.image" alt="Event Image" class="event-image">
        <div class="event-details">
            <h2>{{ event.title }}</h2>
            <p class="category">{{ event.category }}</p>
            <p>{{ event.date }}</p>
            <p class="location">{{ event.location }}</p>
            <div v-if="isLoggedIn" class="comment-section">
              <label>Ocena:</label>
              <input type="number" v-model="event.rating" min="1" max="5">
              <textarea v-model="event.comment" placeholder="Ostavite komentar"></textarea>
              <button @click="leaveComment(index)" class="leave-comment-button">Ostavi komentar</button>
            </div>
          <button v-if="isLoggedIn" @click="buyTickets()" class="buy-tickets-button">Kupi karte</button>
          <button @click="showDetails(index)" class="details-button">Detalji</button>
          <p v-if="event.showDetails" class="event-description">{{ event.description }}</p>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      events: [
        {
          image: 'https://via.placeholder.com/300',
          title: 'Mando diao',
          date: '20. avgust 2024.',
          description: 'Opis koncerta...',
          showDetails: false,
          location: 'Sava centar',
          loggedIn: false,
          rating: 0, 
          comment: '',
          category: 'Koncert'
        },
        {
          image: 'https://via.placeholder.com/300',
          title: 'Partizan - Vojvodina',
          date: '25. avgust 2024.',
          description: 'Opis utakmice...',
          showDetails: false,
          location: 'Stadion Partizana',
          loggedIn: false,
          rating: 0, 
          comment: '',
          category: 'Utakmica'
        },
        {
          image: 'https://via.placeholder.com/300',
          title: 'Festival uličnih sviračа',
          date: '21. septembar 2024.',
          description: 'Opis festivala...',
          showDetails: false,
          location: 'Petrovaradin',
          loggedIn: false,
          rating: 0, 
          comment: '',
          category: 'Festival'
        },
      ],
      showRegisterModal: false,
      showLoginModal: false,
      form: {
        ime: '',
        prezime: '',
        pol: 'M',
        korisnickoIme: '',
        lozinka: ''
      },
      loginForm: {
        korisnickoIme: '',
        lozinka: ''
      },
      isLoggedIn: false,
      searchQuery: ''
    };
  },

computed: {
    filteredEvents() {
      return this.events.filter(event =>
        event.title.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
  },
  methods:{
    showDetails(index) {
      this.events[index].showDetails = !this.events[index].showDetails;
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.event-item {
  position: relative;
  overflow: hidden;
}

.event-details {
  position: relative;
  padding-bottom: 40px;
}

.comment-section {
  position: absolute;
  bottom: -100%; /* Skriva se inicijalno */
  left: 0;
  width: 100%;
  padding: 10px;
  background-color: #f0f0f0;
  border-radius: 0 0 8px 8px;
  z-index: 10;
  transition: bottom 0.3s ease;
}

.event-item:hover .comment-section {
  bottom: 100%; /* Prikazuje se ispod event-item-a pri hoveru */
}

.comment-section label {
  margin-bottom: 5px;
  font-weight: bold;
  display: block;
}

.comment-section input[type="number"],
.comment-section textarea {
  width: calc(100% - 20px);
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.leave-comment-button {
  background-color: #009688;
  color: #ffffff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.leave-comment-button:hover {
  background-color: #00796b;
}

</style>
