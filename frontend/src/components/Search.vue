<script>
import axios from "axios";

export default {
  name: "Search",
  data() {
    return {
      search: '',
      publishers: [],
      authors: [],
      books: []
    }
  },
  methods: {
    searchFor: function () {
      this.error = false;
      let data = {
        name: this.search
      }
      axios.post("/spring/publisher/publisherByName", data)
          .then((response) => {
            this.$set(this, 'publishers', response.data);
          });
      axios.post("/spring/author/authorByName", data)
          .then((response) => {
            this.$set(this, 'authors', response.data);
          });
      this.books = [];
      data = {
        title: this.search
      }
      axios.post("/spring/book/bookByTitle", data)
          .then((response) => {
            this.$set(this, 'books', response.data);
          });
      let year = parseInt(this.search);
      if(!isNaN(year)) {
        console.log("HELLO");
        data = {
          publication_year: year
        }
        axios.post("/spring/book/bookByYear", data)
            .then((response) => {
              this.books = this.books.concat(response.data);
            });
      }
    },
    deleteAuthor: function(id) {
      axios.delete("/spring/author/delete-" + id)
          .then(() => {
            for(let i = 0; i < this.authors.length; i++){
              if (this.authors[i].id_author === id) {
                this.authors.splice(i, 1);
              }
            }
          })
          .catch((errors) => {
            console.log(errors);
            this.authors = [];
          });
    },
    deletePublisher: function(id) {
      axios.delete("/spring/publisher/delete-" + id)
          .then(() => {
            for(let i = 0; i < this.publishers.length; i++){
              if (this.publishers[i].id_publisher === id) {
                this.publishers.splice(i, 1);
              }
            }
          })
          .catch((errors) => {
            console.log(errors);
            this.publishers = [];
          });
    },
    deleteBook: function(id) {
      axios.delete("/spring/book/delete-" + id)
          .then(() => {
            for(let i = 0; i < this.books.length; i++){
              if (this.books[i].id_book === id) {
                this.books.splice(i, 1);
              }
            }
          })
          .catch((errors) => {
            console.log(errors);
            this.books = [];
          });
    }
  }
}
</script>

<template>
  <div class="pl-5 pb-5 pr-5">
    <div class="container mt-4">
      <div class="row justify-content-center">
        <div class="col-auto">
          <p>Vous pouvez chercher un auteur, un ouvrage, une date...</p>
        </div>
        <div class="col-auto">
          <form class="form-inline">
            <input class="form-control" type="search" v-model="search" aria-label="Search">
            <button class="btn btn-outline-warning" type="button" v-on:click="searchFor">Rechercher</button>
          </form>
        </div>
      </div>
    </div>

    <hr>

    <div class="container">
      <div class="p-5">
        <div class="bg-grey p-5 m-3 rounded">
          <h5>Ouvrages ({{ this.books.length }})</h5>
          <hr class="mb-0">
          <div class="alert alert-warning mt-3" role="alert" v-if="this.books.length === 0" style="width: max-content">
            Aucun résultat
          </div>
          <div class="results-section" v-else>
            <div v-for="book in this.books" :key="book.id_book">
              <div class="row m-3">
                <div class="col">
                  <p>Titre : <i>{{ book.title }}</i></p>
                  <p>ID : {{ book.id_book }}</p>
                </div>
                <div class="col-auto">
                  <button type="button" class="btn btn-primary btn-sm" v-on:click="deleteBook(book.id_book)" v-if="$root.role === '614f6aadef41dbbcfc990707'"><i class="fas fa-trash-alt"></i></button>
                </div>
              </div>
              <hr>
            </div>
          </div>
        </div>
        <div class="row m-3">
          <div class="col bg-grey p-5 mr-3 rounded">
            <h5>Auteurs ({{ this.authors.length }})</h5>
            <hr class="mb-0">
            <div class="alert alert-warning mt-3" role="alert" v-if="this.authors.length === 0" style="width: max-content">
              Aucun résultat
            </div>
            <div class="results-section" v-else>
              <div v-for="author in this.authors" :key="author.id_author">
                <div class="row m-3">
                  <div class="col">
                    <p>Nom : {{ author.name }}</p>
                    <p>ID : {{ author.id_author }}</p>
                  </div>
                  <div class="col-auto">
                    <button type="button" class="btn btn-primary btn-sm" v-on:click="deleteAuthor(author.id_author)" v-if="$root.role === '614f6aadef41dbbcfc990707'"><i class="fas fa-trash-alt"></i></button>
                  </div>
                </div>
                <hr>
              </div>
            </div>
          </div>
          <div class="col bg-grey p-5 rounded">
            <h5>Éditeurs ({{ this.publishers.length }})</h5>
            <hr class="mb-0">
            <div class="alert alert-warning mt-3" role="alert" v-if="this.publishers.length === 0" style="width: max-content">
              Aucun résultat
            </div>
            <div class="results-section" v-else>
              <div v-for="publisher in this.publishers" :key="publisher.id_publisher">
                <div class="row m-3">
                  <div class="col">
                    <p>Nom : {{ publisher.name }}</p>
                    <p>ID : {{ publisher.id_publisher }}</p>
                  </div>
                  <div class="col-auto">
                    <button type="button" class="btn btn-primary btn-sm" v-on:click="deletePublisher(publisher.id_publisher)" v-if="$root.role === '614f6aadef41dbbcfc990707'"><i class="fas fa-trash-alt"></i></button>
                  </div>
                </div>
                <hr>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped src="../assets/style.css">

</style>