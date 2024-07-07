<template>
  <div class="button-container">
    <button class="register-button" @click="$emit('open-register-modal')">
      Registruj se
    </button>
    <button class="login-button" @click="$emit('open-login-modal')">
      Uloguj se
    </button>
  </div>
</template>

<script>
export default {
  name: 'ButtonComponent',
};
</script>

<style scoped>
.button-container {
  display: flex;
  gap: 10px; /* Dodajemo razmak između dugmadi */
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
</style>
