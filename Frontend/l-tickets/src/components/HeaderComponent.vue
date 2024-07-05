<template>
  <div class="dropdown">
    <label for="event-types">Vrste događaja:</label>
    <select id="event-types" v-model="selectedEvent">
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
  }
};
</script>

<style scoped>
.dropdown {
  display: flex;
  align-items: center;
  gap: 10px;
}

label {
  font-weight: bold;
}

select {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
