<template>
    <div id="about">
      <h1>Welcome to CharCode!</h1>
      <h2>We're dedicated to making your cookouts effortless and enjoyable.</h2>
      <br>
        <p><b>Simplified Planning:</b> Wide range of recipes, guest coordination tools, and essential checklists.</p>
        <p><b>User-Friendly:</b> Designed for ease of use, whether you're a seasoned grill master or a beginner.</p>
        <p><b>Our Mission:</b> Help you create memorable outdoor gatherings with ease.</p>

      <br>
      <h2>Join us and make every cookout a success with CharCode!</h2>
    </div>
    <div id="miniForm">
        <form action=""></form>

    </div>
  </template>
  
  <script>
  export default {
    data(){
        return {
            event: {
                title: '',
                location: '',
                event_date: ''
            }
        };
    },
    
  };
  </script>

  <style scoped>
  #about{
    text-align: center;
    align-content: center;
    justify-content: center;
    grid-template: 'about';
    flex-grow: 1;
  }

  #about p{
    font-size: 18px;
  }
  </style>