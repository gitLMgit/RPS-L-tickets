<template>
  <div id="app">
    <header>
      <div class="header-content">
        <div class="logo" @click="goToHome">
          LukStix
          <div class="subtitle">Sajt za rezervacije</div>
        </div>
        <div class="search-bar" v-if="isUser()" >
          <input type="text" v-model="searchQuery" placeholder="Pretražite događaje">
          <i class="fa fa-search"></i>
        </div>
        <div class="controls-container">
          <ButtonComponent 
            v-if="!isLogged()" 
            @open-register-modal="openRegisterModal" 
            @open-login-modal="openLoginModal" 
          />
          <DropdownComponent v-if="isUser()"  @selectCategory="selectCategory"/>
          <router-link v-if="isLogged() && isUser()" to="/my-account" class="my-account-button">Moj nalog</router-link>
          <router-link v-if="isLogged() && !isUser()" to="/statistics" class="my-account-button">Statistika</router-link>
          <router-link v-if="isLogged() && !isUser()" to="/admin-page" class="my-account-button">Dodavanje</router-link>
          <button v-if="isLogged()" @click="logoutU" class="logout-button">Odjavi se</button>
        </div>
      </div>
    </header>
    <router-view :selected-category="selectedCategory" :search-query="searchQuery"></router-view>
    <footer class="footer">
      <h2>Dobrodošli na naš sajt za rezervacije!</h2>
    </footer>

    <div class="modal" v-if="showRegisterModal">
  <div class="modal-content">
    <h2>Registracija</h2>
    <form @submit.prevent="register">
      <div v-if="registerError" class="error-message">{{ registerError }}</div>
      <div>
        <label for="name">Ime:</label>
        <input type="text" id="name" v-model="form.name" required>
      </div>
      <div>
        <label for="surname">Prezime:</label>
        <input type="text" id="surname" v-model="form.surname" required>
      </div>
      <div>
        <label for="age">Godine:</label>
        <input type="number" id="age" v-model="form.age" required>
      </div>
      <div>
        <label for="status">Status  :</label>
        <input type="text" id="status" v-model="form.status" required>
      </div>
      <div>
        <label for="gender">Pol:</label>
        <select id="gender" v-model="form.gender" required>
          <option value="M">Muški</option>
          <option value="Ž">Ženski</option>
        </select>
      </div>
      <div>
        <label for="username">Korisničko ime:</label>
        <input type="text" id="username" v-model="form.username" required>
      </div>
      <div>
        <label for="password">Lozinka:</label>
        <input type="password" id="password" v-model="form.password" required>
      </div>
      <button type="submit">Registruj se</button>
    </form>
    <button class="close-modal" @click="closeRegisterModal">Zatvori</button>
  </div>
</div>


    <div class="modal" v-if="showLoginModal">
  <div class="modal-content">
    <h2>Logovanje</h2>
    <form @submit.prevent="login">
      <div v-if="loginError" class="error-message">{{ loginError }}</div>
      <div>
        <label for="korisnicko-ime-login">Korisničko ime:</label>
        <input type="text" id="korisnicko-ime-login" v-model="loginForm.email" required>
      </div>
      <div>
        <label for="lozinka-login">Lozinka:</label>
        <input type="password" id="lozinka-login" v-model="loginForm.password" required>
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
import axios from '@/axios'; 
import { isLoggedIn, logout, isUserLoged } from './session.js';

export default {
  name: 'App',
  components: {
    ButtonComponent,
    DropdownComponent
  },
  data() {
    return {
      events: [],
      showRegisterModal: false,
      showLoginModal: false,
      form: {
        name: '',
        surname: '',
        age: 0,
        status: '',
        gender: '',
        username: '',
        password: '',
      },
      loginForm: {
        email: '',
        password: ''
      },
      isLoggedIn: false,
      searchQuery: '',
      selectedCategory: '',
      loginError: '',
      registerError: ''
    };
  },
  methods: {
    leaveComment(index) {
      const event = this.events[index];
      console.log(`Rating for ${event.title}: ${event.rating}`);
      console.log(`Comment for ${event.title}: ${event.comment}`);
    
      event.rating = 0;
      event.comment = '';
    },
    isLogged() {
      return isLoggedIn(); // Poziv funkcije za proveru prijave iz session.js modula
    },
    goToMyAccount() {
      console.log('Navigacija ka MyAccount');
      this.$router.push('/my-account');
      //window.location.href = '/my-account'
    },

    isUser() {
      console.log(isUserLoged())
      return !isUserLoged()
    }, 

    openRegisterModal() {
      this.showRegisterModal = true;
    },
    closeRegisterModal() {
      this.showRegisterModal = false;
    },
    register() {
  // Kreiranje objekta sa podacima za slanje
  const userData = {
    name: this.form.name,
    surname: this.form.surname,
    age: this.form.age,
    status: this.form.status,
    gender: this.form.gender,
    username: this.form.username,
    password: this.form.password
  };

  // Slanje podataka na backend
  axios.post('/permitAll/register', JSON.stringify(userData), {
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    console.log('Server response:', response.data);
    alert("Uspešno ste se registrovali! sada se možete ulogovati.")
    // Resetovanje forme i zatvaranje modala nakon registracije
    this.form = {
      name: '',
      surname: '',
      age: 0,
      status: '',
      gender: '',
      username: '',
      password: ''
    };
    this.closeRegisterModal();
    alert('Uspešno ste se registrovali!');
    this.$router.go('/');
  }).catch(error => {
    if (error.response && error.response.status === 400) {
      this.registerError = 'Korisnik sa unetim korisničkim imenom već postoji.';
    } else {
      console.error('Error submitting form:', error);
      this.registerError = 'Došlo je do greške prilikom registracije. Molimo pokušajte ponovo.';
    }
  });
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
  axios.post('/rest/auth/login', JSON.stringify(this.loginForm), {
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    console.log('Server response:', response.data);
    sessionStorage.setItem('userData', JSON.stringify(response.data));
    this.closeLoginModal();
    console.log(response.data.admin)
    if (response.data.admin == false)
      this.$router.go(0);
    else
      this.$router.push('/statistics')
  }).catch(error => {
    if (error.response && error.response.status === 400) {
      console.error('Bad request:', error.response.data);
      // Postavljanje poruke o grešci
      this.loginError = 'Pogrešno korisničko ime ili lozinka. Molimo pokušajte ponovo.';
    } else {
      console.error('Error submitting form:', error);
      // Handle other errors
      this.loginError = 'Došlo je do greške prilikom logovanja. Molimo pokušajte ponovo.';
    }

  });
      // Resetovanje forme i zatvaranje modala nakon logovanja
      this.loginForm = {
        email: '',
        password: ''
      };
      
    },
    logoutU() {
      logout()
      axios.post('/rest/auth/logout')
      sessionStorage.removeItem('userData');
      console.log("u log out smo")
      window.location.href='/'
    },
    buyTickets() {
      this.$router.push('/buy-tickets');
    },
    goToHome() {
      this.$router.push('/')
    },
    selectCategory(category) {
      this.selectedCategory = category;
    }
  }
};
</script>
<style>


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

.error-message {
  color: #e53935; /* Crvena boja za poruku o grešci */
  font-size: 14px;
  margin-bottom: 10px;
}

.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 8%;
  background-color: #0b2503; 
  color: white; 
  text-align: center;
  padding: 5px; 
}


</style>