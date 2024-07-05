<template>
  <div id="app">
    <header>
      <div class="header-content">
        <div class="logo">
          LukStix
          <div class="subtitle">Sajt za rezervacije</div>
        </div>
        <div class="controls-container">
          <ButtonComponent />
          <DropdownComponent />
        </div>
      </div>
    </header>

    <div class="event-grid">
      <div class="event-item" v-for="(event, index) in events" :key="index">
        <img :src="event.image" alt="Event Image" class="event-image">
        <div class="event-details">
          <h2>{{ event.title }}</h2>
          <p>{{ event.date }}</p>
          <p class="location">{{ event.location }}</p>
          <button @click="showDetails(index)" class="details-button">Detalji</button>
          <p v-if="event.showDetails" class="event-description">
            {{ event.description }}
          </p>
        </div>
      </div>
    </div>

    <main>
      <h1>Welcome to My Vue.js Project</h1>
      <p>This is a simple starting page.</p>
    </main>
  </div>
</template>

<script>
import ButtonComponent from './components/ButtonComponent.vue';
import DropdownComponent from './components/DropdownComponent.vue';

export default {
  name: 'App',
  components: {
    ButtonComponent,
    DropdownComponent
  },
  data() {
    return {
      events: [
        {
          image: 'https://via.placeholder.com/300',
          title: 'Koncert',
          date: '20. avgust 2024.',
          description: 'Opis koncerta...',
          showDetails: false,
          location: 'Sava centar'
        },
        {
          image: 'https://via.placeholder.com/300',
          title: 'Utakmica',
          date: '25. avgust 2024.',
          description: 'Opis utakmice...',
          showDetails: false,
          location: 'Stadion Partizana'
        },
        {
          image: 'https://via.placeholder.com/300',
          title: 'Festival',
          date: '21. septembar 2024.',
          description: 'Opis festivala...',
          showDetails: false,
          location: 'Petrovaradin'
        },
      ]
    };
  },
  methods: {
    showDetails(index) {
      this.events[index].showDetails = !this.events[index].showDetails;
    }
  }
};
</script>

<style>
#app {
  font-family: 'Arial', sans-serif;
  color: #333;
  text-align: center;
  margin-top: 60px;
}

header {
  background-color: #37474f; /* Tamno plava pozadina */
  padding: 10px 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header-content {
  display: flex;
  width: 100%;
  max-width: 1200px; /* Ograničavamo širinu sadržaja */
  margin: 0 auto; /* Centriramo sadržaj */
}

.logo {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff; /* Bela boja za tekst */
}

.subtitle {
  font-size: 14px;
  color: #b0bec5; /* Svetlo plava boja za podnaslov */
}

.controls-container {
  display: flex;
  gap: 10px;
  align-items: center;
  margin-left: auto; /* Pomeramo kontrole na desnu stranu */
}

.event-grid {
  width: 100%;
  max-width: 1200px; /* Ograničavamo širinu grid-a */
  margin: 20px auto; /* Centriramo grid */
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  justify-items: center;
  align-items: start;
}

.event-item {
  background-color: #eceff1; /* Svetlo siva pozadina */
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease; /* Dodajemo efekat tranzicije za hover */
}

.event-item:hover {
  transform: translateY(-5px); /* Pomeramo element malo na gore prilikom hovera */
}

.event-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 10px;
}

.event-details {
  text-align: left;
}

.event-details h2 {
  margin-bottom: 5px;
  color: #333; /* Tamno plava boja za naslov događaja */
}

.event-details p {
  margin-bottom: 10px;
  color: #666; /* Siva boja za tekst */
}

.location {
  font-size: 16px;
  text-transform: uppercase;
  font-weight: bold;
  color: #009688; /* Teal boja za lokaciju */
  margin-top: 5px;
}

.details-button {
  background-color: #009688; /* Teal boja za dugme */
  color: #ffffff; /* Bela boja za tekst na dugmetu */
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* Dodajemo efekat tranzicije za hover */
}

.details-button:hover {
  background-color: #00796b; /* Tamnija nijansa teal boje prilikom hovera */
}

.event-description {
  font-size: 14px;
  margin-top: 10px;
  color: #444; /* Tamno siva boja za opis događaja */
}

</style>
