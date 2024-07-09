<template>
  <div class="buy-tickets-page">
    <h1>Onlajn kupovina ulaznice</h1>
    <div class="event-details">
      <h2>{{ selectedEvent.title }}</h2>
      <p><strong>Datum:</strong> {{ selectedEvent.date }}</p>
      <p><strong>Lokacija:</strong> {{ selectedEvent.location.name }}</p>
      <p><strong>Trenutna cena licitacije:</strong> {{ currentBid }}</p>
      <input type="number" v-model="bidAmount" :min="currentBid" placeholder="Unesite vašu licitaciju">
      <button @click="placeBid">Licitiraj</button>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'; 
import { format } from 'date-fns';
export default {
  
  name: 'BuyTickets',
  data() {
    return {
      selectedEvent: {
        title: '',
        date: '',
        location: ''
      },
      eventId: 0,
      currentBid: 0, // Initial bid amount
      bidAmount: 0
    };
  },
  methods: {
    async fetchEventDetails() {
        console.log("u fetch data smo")
        const userData = sessionStorage.getItem('userData');
        const user = JSON.parse(userData);
        console.log(user.token)
        await axios.get(`/event/getEvent/${this.eventId}`,   {
        headers: {
    Authorization: `Bearer ${user.token}`
  }}).then(response=> {
    this.selectedEvent = response.data.event;
    this.currentBid = response.data.currentBid
    this.bidAmount = this.currentBid
  }).catch (error => {
    console.error('Failed to fetch event details:', error);
  });
    },
    async placeBid() {
      try {
        const userData = sessionStorage.getItem('userData');
        const user = JSON.parse(userData);
        console.log(this.bidAmount)
         const todayDate = format(new Date(), 'yyyy-MM-dd');
      const response = await axios.post('/event/bidTicket', {
          idUser: user.id,
        idEvent: this.eventId,
        bidPrice: this.bidAmount,
        bidDateTime:  todayDate
      });
      if (response.data)
        console.log('Uspesno poslato:', response.data);
      else 
        console.log("Neuspesno.")
      // Obrada odgovora ako je potrebno
    } catch (error) {
        console.error('Greska pri slanju licitacije:', error);
    // Obrada greške
  }
    }
  },
  created(){
      console.log("stigli smo u create")
      this.eventId = this.$route.params.id;
    // Učitavanje detalja događaja na osnovu eventId
      this.fetchEventDetails();
    },
};
</script>

<style scoped>
.buy-tickets-page {
  font-family: 'Arial', sans-serif;
  color: #333;
  text-align: center;
  margin-top: 60px;
}

.event-details {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 16px;
  max-width: 600px;
  margin: 0 auto;
  text-align: left;
}

.event-details h2 {
  font-size: 1.5em;
  margin-bottom: 16px;
}

.event-details p {
  margin-bottom: 8px;
}

.event-details input {
  display: block;
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.event-details button {
  background-color: #009688;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
}

.event-details button:hover {
  background-color: #00796b;
}

h1 {
    font-family: 'Roboto', sans-serif; /* Profesionalni font */
    font-size: 3rem; /* Veličina fonta */
    color: #2c3e50; /* Tamno plava boja */
    text-align: center; /* Centriran tekst */
    margin-top: 5rem; /* Gornji razmak */
    margin-bottom: 1rem; /* Donji razmak */
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2); /* Lagana senka za tekst */
}
</style>
