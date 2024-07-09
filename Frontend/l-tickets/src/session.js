import axios from '@/axios'; 

export function isLoggedIn() {
    const userData = JSON.parse(sessionStorage.getItem('userData')); 
    return !!userData; 
  }
  
  export function logout() {
    axios.post('/rest/auth/logout')
    sessionStorage.removeItem('userData');
  }

  export  function isUserLoged(){
    const userData = JSON.parse(sessionStorage.getItem('userData')); 
    return !!userData && userData.admin; 
  }
  