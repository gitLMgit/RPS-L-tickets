<template>
  <div class="dropdown">
    <button @click="toggleDropdown" class="dropdown-button">
      {{ selectedCategory || 'Odaberite kategoriju..' }}
    </button>
    <div v-if="isOpen" class="dropdown-menu">
      <div v-for="category in categories" :key="category.idCategory" @click="selectCategory(category.category)">
        {{ category.category }}
      </div>
      <div @click="selectCategory()">
      Odaberite kategoriju..
    </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'; 

export default {
  data() {
    return {
      isOpen: false,
      selectedCategory: '',
      categories: [] 
    };
  },
  created() {
    console.log('created')
    this.fetchCategories();
  },
  methods: {
    toggleDropdown() {
      this.isOpen = !this.isOpen;
    },

    allCats(){
      this.selectCategory = '';
      this.isOpen = false;
      this.$emit('selectCategory', '');
    },
    selectCategory(category) {
      this.selectedCategory = category;
      this.isOpen = false;
      this.$emit('selectCategory', category);
    },
    fetchCategories() {
      console.log("pribavljamo kat")
      axios.get('/permitAll/getAllCategories')
        .then(response => {
          console.log(response.data)
          this.categories = response.data;
        })
        .catch(error => {
          console.error('Error fetching categories:', error);
        });
        console.log(this.categories)
    }
  }
};
</script>

<style scoped>
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-button {
  width: 180px; 
  height: 32px;
  background-color: #009688;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  text-align: center;
  line-height: normal; 
  display: flex;
  justify-content: center; 
}

.dropdown-menu {
  position: absolute;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  z-index: 1;
  width: 100%;
}

.dropdown-menu div {
  padding: 10px 20px;
  cursor: pointer;
}

.dropdown-menu div:hover {
  background-color: #f0f0f0;
}
</style>
