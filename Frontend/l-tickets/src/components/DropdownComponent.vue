<template>
  <div class="dropdown">
    <button @click="toggleDropdown" class="dropdown-button">
      {{ selectedCategory || 'Odaberite kategoriju' }}
    </button>
    <div v-if="isOpen" class="dropdown-menu">
      <div v-for="category in categories" :key="category" @click="selectCategory(category)">
        {{ category }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isOpen: false,
      selectedCategory: '',
      categories: ['Koncert', 'Utakmica', 'Festival', 'Bioskop', 'Predstava', 'Ostalo'] // Dodaj ili promenite kategorije po potrebi
    };
  },
  methods: {
    toggleDropdown() {
      this.isOpen = !this.isOpen;
    },
    selectCategory(category) {
      this.selectedCategory = category;
      this.isOpen = false;
      this.$emit('selectCategory', category);
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
  background-color: #009688;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
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
