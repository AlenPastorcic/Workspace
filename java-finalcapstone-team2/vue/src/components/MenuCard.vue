<template>
    <div class="menu-card">
      <img :src="menuItem.imgUrl" alt="Menu item image" class="menu-card-img" />
      <div class="menu-card-content">
        <h3>{{ menuItem.title }}</h3>
        <p>{{ menuItem.description }}</p>
        <div class="menu-button-holder">
    </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: ['menuItem']
  };
  </script>
  
  <style scoped>
  .menu-card {
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    max-width: 100%;
    margin: 16px;
  }
  
  .menu-card-img {
    width: 100%;
    height: auto;
  }
  
  .menu-card-content {
    padding: 16px;
  }
  
  .menu-card-content h3 {
    margin: 0 0 8px;
  }
  
  .menu-card-content p {
    margin: 0;
  }
  
  
</style>
  
  