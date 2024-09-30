import axios from 'axios';

const http = axios.create({
  baseURL: "https://www.themealdb.com/api/json/v1/1"
});

export default {

  searchFood(keyword) {
    return http.get('/search.php?s=' + keyword)
  },
  getFoodCategory(){
    return http.get('/categories.php')
  }

}