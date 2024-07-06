<template>
  <div id="app">
    <header>
      <div class="header-content">
        <div class="logo" @click="goToHome">
          LukStix
          <div class="subtitle">Sajt za rezervacije</div>
        </div>
        <div class="search-bar">
          <input type="text" v-model="searchQuery" placeholder="Pretražite događaje">
          <i class="fa fa-search"></i>
        </div>
        <div class="controls-container">
          <ButtonComponent 
            v-if="!isLoggedIn" 
            @open-register-modal="openRegisterModal" 
            @open-login-modal="openLoginModal" 
          />
          <DropdownComponent />
          <button v-if="isLoggedIn" @click="logout" class="logout-button">Odjavi se</button>
          <button v-if="isLoggedIn" @click="goToMyAccount" class="my-account-button">Moj nalog</button>
        </div>
      </div>
    </header>

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

    <main>
      <h1>Dobrodošli na naš sajt za rezervacije!</h1>
    </main>

    <!-- Modal za registraciju -->
    <div class="modal" v-if="showRegisterModal">
      <div class="modal-content">
        <h2>Registracija</h2>
        <form @submit.prevent="register">
          <div>
            <label for="ime">Ime:</label>
            <input type="text" id="ime" v-model="form.ime" required>
          </div>
          <div>
            <label for="prezime">Prezime:</label>
            <input type="text" id="prezime" v-model="form.prezime" required>
          </div>
          <div>
            <label for="pol">Pol:</label>
            <select id="pol" v-model="form.pol" required>
              <option value="M">Muški</option>
              <option value="Ž">Ženski</option>
            </select>
          </div>
          <div>
            <label for="korisnicko-ime">Korisničko ime:</label>
            <input type="text" id="korisnicko-ime" v-model="form.korisnickoIme" required>
          </div>
          <div>
            <label for="lozinka">Lozinka:</label>
            <input type="password" id="lozinka" v-model="form.lozinka" required>
          </div>
          <button type="submit">Registruj se</button>
        </form>
        <button class="close-modal" @click="closeRegisterModal">Zatvori</button>
      </div>
    </div>

    <!-- Modal za logovanje -->
    <div class="modal" v-if="showLoginModal">
      <div class="modal-content">
        <h2>Logovanje</h2>
        <form @submit.prevent="login">
          <div>
            <label for="korisnicko-ime-login">Korisničko ime:</label>
            <input type="text" id="korisnicko-ime-login" v-model="loginForm.korisnickoIme" required>
          </div>
          <div>
            <label for="lozinka-login">Lozinka:</label>
            <input type="password" id="lozinka-login" v-model="loginForm.lozinka" required>
          </div>
          <button type="submit">Uloguj se</button>
        </form>
        <button class="close-modal" @click="closeLoginModal">Zatvori</button>
      </div>
    </div>
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
  methods: {
    leaveComment(index) {
      const event = this.events[index];
      console.log(`Rating for ${event.title}: ${event.rating}`);
      console.log(`Comment for ${event.title}: ${event.comment}`);
    
      event.rating = 0;
      event.comment = '';
    },
    goToMyAccount() {
      this.$router.push('/my-account');
    },
    showDetails(index) {
      this.events[index].showDetails = !this.events[index].showDetails;
    },
    openRegisterModal() {
      this.showRegisterModal = true;
    },
    closeRegisterModal() {
      this.showRegisterModal = false;
    },
    register() {
      // Implementacija logike za registrovanje korisnika
      console.log('Podaci za registraciju:', this.form);
      // Resetovanje forme i zatvaranje modala nakon registracije
      this.form = {
        ime: '',
        prezime: '',
        pol: 'M',
        korisnickoIme: '',
        lozinka: ''
      };
      this.closeRegisterModal();
    },
    openLoginModal() {
      this.showLoginModal = true;
    },
    closeLoginModal() {
      this.showLoginModal = false;
    },
    login() {
      // Implementacija logike za logovanje korisnika
      console.log('Podaci za logovanje:', this.loginForm);
      // Resetovanje forme i zatvaranje modala nakon logovanja
      this.loginForm = {
        korisnickoIme: '',
        lozinka: ''
      };
      this.closeLoginModal();
      this.isLoggedIn = true;
      // Postavljanje statusa ulogovanosti za sve događaje (simulacija ulogovanosti)
      this.events.forEach(event => {
        event.loggedIn = true;
      });
    },
    logout() {
      // Implementacija logike za odjavu korisnika
      this.isLoggedIn = false;
      // Resetovanje statusa ulogovanosti za sve događaje (simulacija odjave)
      this.events.forEach(event => {
        event.loggedIn = false;
      });
    },
    buyTickets() {
      this.$router.push('/buy-tickets');
    },
    goToHome() {
      // Implementacija navigacije na početnu stranicu
    }
  }
};
</script>



<style>

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

.buy-tickets-button, .details-button {
  margin: 10px;
  position: relative;
  z-index: 1; /* Postavljanje visokog z-indexa za dugme "Detalji" */
  background-color: #009688;
  color: #ffffff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.buy-tickets-button, .details-button:hover {
  background-color: #00796b;
}


.logout-button {
  background-color: #e53935; /* Crvena boja za dugme */
  color: #ffffff; /* Bela boja za tekst */
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background-color: #c62828; /* Tamnija nijansa crvene boje prilikom hovera */
}

.my-account-button {
  background-color: #009688;
  color: #ffffff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.my-account-button:hover {
  background-color: #00796b;
}

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
  position: fixed; /* Fiksirano na vrhu */
  width: 100%;
  top: 0;
  z-index: 1;
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
  cursor: pointer;
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

.register-button,
.login-button {
  background-color: #009688;
  color: #ffffff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.register-button:hover,
.login-button:hover {
  background-color: #00796b;
}

.event-grid {
  width: 100%;
  max-width: 1200px; /* Ograničavamo širinu grid-a */
  margin: 80px auto 20px; /* Centriramo grid i dodajemo marginu za header */
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

/* Stilizacija za modal */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Poluprovidna crna pozadina */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999; /* Visok z-index da bude iznad ostalog sadržaja */
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  width: 300px;
  text-align: left;
}

.modal-content h2 {
  text-align: center;
  margin-bottom: 10px;
}

.modal-content form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal-content form div {
  margin-bottom: 10px;
}

.modal-content form label {
  display: block;
  margin-bottom: 5px;
}

.modal-content form input {
  width: 100%;
  padding: 8px;
  font-size: 14px;
}

.modal-content form button {
  background-color: #009688;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.modal-content form button:hover {
  background-color: #00796b;
}

.close-modal {
  background-color: #e53935;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 10px;
}

.close-modal:hover {
  background-color: #c62828;
}

.search-bar {
  position: relative;
  margin: 0 auto; /* Pomeramo na sredinu */
  width: 400px; /* Povećavamo širinu */
}


.search-bar input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.search-bar i {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  color: #555;
}

</style>