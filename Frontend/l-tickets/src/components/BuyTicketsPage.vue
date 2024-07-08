<template>
  <div class="buy-tickets-page">
    <h1>Onlajn kupovina ulaznice</h1>
    <div class="event-details">
      <h2>{{ selectedEvent.title }}</h2>
      <p><strong>Datum:</strong> {{ selectedEvent.date }}</p>
      <p><strong>Lokacija:</strong> {{ selectedEvent.location }}</p>
      <p><strong>Trenutna cena licitacije:</strong> {{ currentBid }}</p>
      <input type="number" v-model="bidAmount" :min="currentBid" placeholder="Unesite vašu licitaciju">
      <button @click="placeBid">Licitiraj</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BuyTicketsPage',
  data() {
    return {
      selectedEvent: {
        title: 'Partizan - Vojvodina',
        date: '2024-07-10',
        location: 'Beograd'
      },
      currentBid: 1000, // Initial bid amount
      bidAmount: 1000
    };
  },
  methods: {
    placeBid() {
      if (this.bidAmount > this.currentBid) {
        this.currentBid = this.bidAmount;
        alert(`Uspešno ste licitirali sa iznosom ${this.bidAmount}`);
      } else {
        alert('Uneta vrednost mora biti veća od trenutne cene.');
      }
    }
  }
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
