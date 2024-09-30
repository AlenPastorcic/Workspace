<template>
  <div id="about">
    <h1>About Us</h1>
    <p><b>Meet the Team:</b></p>
    <div class="team-list">
      <div class="team-member">
        <p><b>Product Owner: Allison Rehmann</b></p>
        <p><a href="mailto:allison@techelevator.com">allison@techelevator.com</a></p>
      </div>
      <br>
      <div class="team-member">
        <p><b>Project Members:  Alen Pastorcic</b></p>
        <p><a href="mailto:al.pastorcic@gmail.com">al.pastorcic@gmail.com</a></p>
      </div>
      <div class="team-member">
        <p><b>Kaung Myat Lwin</b></p>
        <p><a href="mailto:kgmwyatlin89@gmail.com">kgmwyatlin89@gmail.com</a></p>
      </div>
      <div class="team-member">
        <p><b>Alex Weingartner</b></p>
        <p><a href="mailto:acweingartner@gmail.com">acweingartner@gmail.com</a></p>
      </div>
      <div class="team-member">
        <p><b>Jerry Bonet</b></p>
        <p><a href="mailto:jeremias.bonet1@gmail.com">jeremias.bonet1@gmail.com</a></p>
      </div>
    </div>
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