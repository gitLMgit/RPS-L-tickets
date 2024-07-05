<template>
    <div class="button-container">
      <button class="btn" @click="onCreateAccount">Registruj se</button>
      <button class="btn" @click="onLogin">Uloguj se</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ButtonComponent',
    methods: {
      onCreateAccount() {
        alert('Navigacija ka stranici za kreiranje naloga');
        // Dodaj navigaciju ka stranici za kreiranje naloga
      },
      onLogin() {
        alert('Navigacija ka stranici za logovanje');
        // Dodaj navigaciju ka stranici za logovanje
      }
    }
  };
  </script>
  
  <style scoped>
  .button-container {
    display: flex;
    gap: 10px;
  }
  
  .btn {
    background-color: #42b983;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
  }
  
  .btn:hover {
    background-color: #379a70;
  }
  </style>
  