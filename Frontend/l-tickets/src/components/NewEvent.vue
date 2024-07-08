<template>
    <div class="add-event">
      <h1>Dodaj novi događaj</h1>
      <form @submit.prevent="submitEvent">
        <div class="form-group">
          <label for="name">Naziv</label>
          <input type="text" id="name" v-model="event.name" required>
        </div>
  
        <div class="form-group">
          <label for="date">Datum</label>
          <input type="date" id="date" v-model="event.date" required>
        </div>
  
        <div class="form-group">
          <label for="description">Opis</label>
          <textarea id="description" v-model="event.description" required></textarea>
        </div>
  
        <div class="form-group">
          <label for="category">Kategorija</label>
          <select id="category" v-model="event.category" required>
            <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
          </select>
        </div>
  
        <div class="form-group">
          <label for="location">Lokacija</label>
          <select id="location" v-model="event.location" required>
            <option v-for="location in locations" :key="location" :value="location">{{ location }}</option>
          </select>
        </div>
  
        <button type="submit" @click="submitEvent">Dodaj događaj</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        event: {
          name: '',
          date: '',
          description: '',
          category: '',
          location: ''
        },
        categories: ['Koncert', 'Predstava', 'Izložba', 'Sport'],
        locations: ['Beograd', 'Novi Sad', 'Niš', 'Kragujevac']
      };
    },
    methods: {
      submitEvent() {
         console.log(this.event);
         console.log('Događaj uspešno dodat');
      }
    }
  };
  </script>
  
  <style scoped>
  .add-event {
    max-width: 600px;
    margin: 0 auto;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input, textarea, select {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #45a049;
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
  