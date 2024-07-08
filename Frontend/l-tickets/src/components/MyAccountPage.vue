<template>
  <div>
    <h1>Moj nalog</h1>
    <p>Dobrodošli na stranicu vašeg naloga.</p>
    <div class="tickets-container">
      <div 
        v-for="ticket in tickets" 
        :key="ticket.id" 
        class="ticket-card" 
        :class="{ 'active-auction': ticket.isAuctionActive }"
      >
        <h2>{{ ticket.eventName }}</h2>
        <p>ID: {{ ticket.id }}</p>
        <p>Datum: {{ ticket.date }}</p>
        <p>Sedište: {{ ticket.seat }}</p>
        <p>Cena: {{ ticket.price }} RSD</p>
        <div v-if="ticket.isAuctionActive" class="cancel-auction" @click="cancelAuction(ticket.id)">
          X
        </div>
        <div v-else>
          <div class="rating-section">
            <label for="rating">Ocena:</label>
            <select v-model="ticket.rating">
              <option v-for="n in 5" :key="n" :value="n">{{ n }}</option>
            </select>
          </div>
          <div class="comment-section">
            <label for="comment">Komentar:</label>
            <textarea v-model="ticket.comment"></textarea>
          </div>
          <button @click="submitFeedback(ticket.id)">Pošalji ocenu i komentar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyAccountPage',
  data() {
    return {
      tickets: [
        {
          id: '12345',
          eventName: 'Koncert 1',
          date: '2024-08-01',
          seat: 'A1',
          price: '5000',
          isAuctionActive: false,
          rating: null,
          comment: ''
        },
        {
          id: '67890',
          eventName: 'Predstava 2',
          date: '2024-09-15',
          seat: 'B2',
          price: '3000',
          isAuctionActive: false,
          rating: null,
          comment: ''
        },
        {
          id: '1234567',
          eventName: 'Koncert 3',
          date: '2024-08-02',
          seat: 'C2',
          price: '5000',
          isAuctionActive: true,
          rating: null,
          comment: ''
        },
      ]
    };
  },
  methods: {
    cancelAuction(ticketId) {
      this.tickets = this.tickets.filter(ticket => ticket.id !== ticketId);
      console.log(`Licitacija za kartu ${ticketId} je otkazana i karta je obrisana.`);
    },
    submitFeedback(ticketId) {
      const ticket = this.tickets.find(ticket => ticket.id === ticketId);
      if (ticket) {
        console.log(`Ocena za događaj ${ticket.eventName}: ${ticket.rating}`);
        console.log(`Komentar za događaj ${ticket.eventName}: ${ticket.comment}`);
        // Ovdje možete implementirati logiku za slanje ocene i komentara na server
        alert(`Ocena i komentar za događaj ${ticket.eventName} su poslati.`);
      }
    }
  }
};
</script>

<style scoped>
.tickets-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.ticket-card {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 16px;
  width: 200px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  position: relative;
}

.ticket-card h2 {
  margin: 0 0 8px;
  font-size: 18px;
  color: #333;
}

.ticket-card p {
  margin: 4px 0;
  font-size: 14px;
  color: #666;
}

.active-auction {
  border-left: 4px solid green;
}

.cancel-auction {
  position: absolute;
  top: 8px;
  right: 8px;
  cursor: pointer;
  color: red;
  font-weight: bold;
}

.rating-section {
  margin-top: 10px;
}

.rating-section label {
  display: block;
  margin-bottom: 5px;
}

.rating-section select {
  width: 100%;
  padding: 5px;
}

.comment-section {
  margin-top: 10px;
}

.comment-section label {
  display: block;
  margin-bottom: 5px;
}

.comment-section textarea {
  width: 100%;
  height: 60px;
  padding: 5px;
}

button {
  background-color: #009688;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 10px;
}

button:hover {
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
