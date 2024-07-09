<template>
    <div class="statistics-container">
      <h1>Statistike</h1>
      <div class="table-container">
        <h2>Statistika karata</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Event Name</th>
              <th>Tickets Sold</th>
              <th>Revenue</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="stat in ticketStats" :key="stat.id">
              <td>{{ stat.event.idEvent }}</td>
              <td>{{ stat.event.title }}</td>
              <td>{{ stat.soldTickets }}</td>
              <td>{{ stat.revenue }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="table-container">
        <h2>Statistika ocenjenih dogadjaja</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Event Name</th>
              <th>Average Rating</th>
              <th>Number of Ratings</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="stat in ratedEventStats" :key="stat.id">
              <td>{{ stat.event.idEvent }}</td>
              <td>{{ stat.event.title }}</td>
              <td>{{ stat.averageRate }}</td>
              <td>{{ stat.numRate }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import axios from '@/axios'; // Adjust the import according to your axios setup
  
  export default {
    data() {
      return {
        ticketStats: [],
        ratedEventStats: []
      };
    },
    methods: {
      async fetchStatistics() {
        try {
          const ticketStatResponse = await axios.get('/statistic/ticketStat');
          this.ticketStats = ticketStatResponse.data;
          console.log(this.ticketStats)
          console.log('Dobavio ticketStats')
  
          const ratedEventsStatResponse = await axios.get('/statistic/ratedEventsStat');
          this.ratedEventStats = ratedEventsStatResponse.data;
          console.log(this.ratedEventStats)
        } catch (error) {
          console.error('Failed to fetch statistics:', error);
        }
      }
    },
    created() {
      this.fetchStatistics();
    }
  };
  </script>
  
  <style scoped>
  .statistics-container {
    padding: 20px;
  }
  
  .table-container {
    margin-bottom: 20px;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }
  
  th {
    background-color: #f2f2f2;
  }
  
  h1 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  h2 {
    text-align: left;
    margin-top: 20px;
  }
  </style>
  