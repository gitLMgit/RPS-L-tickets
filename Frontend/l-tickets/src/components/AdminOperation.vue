<template>
    <div class="admin-operation-container">
      <div class="form-container">
        <h2>Dodavanje nove kategorije</h2>
        <form @submit.prevent="addCategory">
          <div class="form-group">
            <label for="categoryName">Naziv kategorije:</label>
            <input type="text" id="categoryName" v-model="categoryName" required>
          </div>
          <div class="form-group">
            <button type="submit">Dodaj kategoriju</button>
          </div>
        </form>
      </div>
  
      <div class="form-container">
        <h2>Dodavanje novog događaja</h2>
        <form @submit.prevent="addEvent">
          <div class="form-group">
            <label for="eventTitle">Naslov:</label>
            <input type="text" id="eventTitle" v-model="eventTitle" required>
          </div>
          <div class="form-group">
            <label for="eventDescription">Opis:</label>
            <textarea id="eventDescription" v-model="eventDescription" rows="4" required></textarea>
          </div>
          <div class="form-group">
            <label for="eventDate">Datum:</label>
            <input type="date" id="eventDate" v-model="eventDate" required>
          </div>
          <div class="form-group">
            <label for="eventCategory">Kategorija:</label>
            <select id="eventCategory" v-model="selectedCategory" required>
              <option v-for="category in categories" :key="category.id" :value="category.idCategory">{{ category.category }}</option>
            </select>
          </div>
          <div class="form-group">
            <label for="eventLocation">Lokacija:</label>
            <select id="eventLocation" v-model="selectedLocation" required>
              <option v-for="location in locations" :key="location.id" :value="location.idLocation">{{ location.name }}</option>
            </select>
          </div>
          <div class="form-group">
            <button type="submit">Dodaj događaj</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from '@/axios';
  
  export default {
    data() {
      return {
        categoryName: '',
        eventTitle: '',
        eventDescription: '',
        eventDate: '',
        selectedCategory: null,
        selectedLocation: null,
        categories: [],
        locations: []
      };
    },
    methods: {
      async fetchCategoriesAndLocations() {
        try {
          const categoriesResponse = await axios.get('/permitAll/getAllCategories');
          this.categories = categoriesResponse.data;
  
          const locationsResponse = await axios.get('/permitAll/getLocations');
          this.locations = locationsResponse.data;
        } catch (error) {
          console.error('Failed to fetch categories and locations:', error);
        }
      },
      async addCategory() {
  try {
    const params = new URLSearchParams();
    params.append('catName', this.categoryName);

    const response = await axios.post('/category/addCategory', null, {
        params: {
            catName: this.categoryName
        }
    });
    console.log('Category added successfully:', response.data);
    alert(`Nova kategorija je uspesno dodata!`);
    this.categoryName = '';
  } catch (error) {
    console.error('Failed to add category:', error);
  }
}
,
      async addEvent() {
        try {
          const eventData = {
            title: this.eventTitle,
            description: this.eventDescription,
            date: this.eventDate,
            category: this.selectedCategory,
            location: this.selectedLocation
          };
          console.log(eventData)
          const response = await axios.post('/event/addEvent', eventData);
          console.log('Event added successfully:', response.data);
          alert(`Novi dogadjaj je uspesno dodat!`);
          // Clear form after successful submission
          this.eventTitle = '';
          this.eventDescription = '';
          this.eventDate = '';
          this.selectedCategory = null;
          this.selectedLocation = null;
        } catch (error) {
          console.error('Failed to add event:', error);
        }
      }
    },
    created() {
      this.fetchCategoriesAndLocations();
    }
  };
  </script>
  
  <style scoped>
  .admin-operation-container {
    display: flex;
    justify-content: space-between;
  }
  
  .form-container {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px;
    width: 45%;
  }
  
  .form-group {
    margin-bottom: 10px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="text"],
  input[type="date"],
  textarea,
  select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  