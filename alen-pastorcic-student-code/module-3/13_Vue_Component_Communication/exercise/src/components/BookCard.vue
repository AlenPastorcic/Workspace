<template>
  <div class="card">
    <h2 class="book-title">
         {{ book.title }} 
    </h2>
    <img :src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" alt="Book cover" class="book-image"/>
    <h3 class="book-author"> {{ book.author }}</h3>
     <div class="button-container" v-if="! enableAdd">
        <button class="mark-read" v-on:click.prevent="setRead(true)" v-if=" ! book.read">Mark Read</button>
        <button class="mark-unread" v-on:click.prevent="setRead(false)" v-if="book.read">Mark Unread</button>
    </div>
  </div>
</template>

<script>
export default {
    name: "book-card",
    props: {
        book: Object,
        enableAdd: {
            type: Boolean,
            default: false
        }
    },

 methods: {
        setRead(value) {
            this.$store.commit('SET_READ_STATUS', {book: this.book, value: value});
        }
    }
}
</script>

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 550px;
  margin: 20px;
  position: relative;
}

.card.read {
  background-color: lightgray;
}

.card .book-title {
  font-size: 1.5rem;
}

.card .book-author {
  font-size: 1rem;
}

.book-image {
  width: 80%;
}

.mark-read, .mark-unread {
  display: block;
  position: absolute;
  bottom: 40px;
  width: 80%;
  left: 10%;
  font-size: 1rem;
}
</style>
