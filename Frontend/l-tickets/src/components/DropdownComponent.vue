<template>
    <div class="dropdown">
      <select id="event-types" v-model="selectedEvent">
        <option disabled value="">Vrste događaja</option>
        <option value="concerts">Koncerti</option>
        <option value="matches">Utakmice</option>
        <option value="plays">Predstave</option>
        <option value="movies">Bioskopske projekcije</option>
        <option value="festivals">Festivali</option>
        <option value="others">Ostalo</option>
      </select>
    </div>
  </template>
  
  <script>
  export default {
    name: 'DropdownComponent',
    data() {
      return {
        selectedEvent: ''
      };
    },
    methods: {
    handleTypeChange(event) {
      this.$emit('filterByType', event.target.value);
    }
  }
  };
  </script>
  
  <style scoped>
  .dropdown select {
    padding: 10px 20px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #ffffff;
    color: #333;
    cursor: pointer;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    outline: none;
    width: 200px;
    background-image: url("data:image/svg+xml;utf8,<svg fill='none' stroke='black' stroke-width='2' viewBox='0 0 24 24' xmlns='http://www.w3.org/2000/svg'><path stroke-linecap='round' stroke-linejoin='round' d='M19 9l-7 7-7-7'/></svg>");
    background-repeat: no-repeat;
    background-position: right 10px center;
  }
  
  .dropdown select:focus {
    border-color: #42b983;
  }
  
  .dropdown {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  .dropdown label {
    font-weight: bold;
  }
  </style>
  