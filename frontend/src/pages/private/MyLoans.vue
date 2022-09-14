<script>
import axios from "axios";
import delay from "delay";

export default {
  name: "MyLoans",
  data() {
    return {
      books: []
    }
  },
  methods: {
    getBooks: function() {
      for(let i = 0; i < this.$parent.loans.length; i++) {
        let data = {
          id_book: this.$parent.loans[i].id_book
        }
        axios.post("/spring/book/bookById", data)
            .then((response) => {
              console.log(response.data);
              this.books.push(response.data);
            })
            .catch((errors) => {
              console.log(errors);
            })
      }
    }
  },
  async mounted() {
    await delay(500);
    this.getBooks();
  }
}
</script>

<template>
  <div>
    <div class="bg-grey">
      <div class="home-banner bg-red text-white p-5">
        <h3>Mes emprunts ({{ this.$parent.loans.length }})</h3>
      </div>

      <div class="p-5" style="min-height: 70vh">
        <div class="alert alert-warning" role="alert" v-if="this.$parent.loans.length === 0">
          Vous n'avez aucun emprunt en cours
        </div>
        <div v-for="(value, index) in this.$parent.loans" :key="index">
          <div class="bg-dark-grey m-3 p-3">
            <div class="row ml-3 mr-3 justify-content-between">
              <p class="col-auto">ID : {{ value.id_loan }}</p>
              <p class="col-auto">Date de retour : {{ new Date(value.date_return).toISOString().substring(0, 10) }}</p>
              <p class="col-auto">Date d'emprunt : {{ new Date(value.date_borrowing).toISOString().substring(0, 10) }}</p>
              <p class="col-auto">Durée : {{ value.borrowing_days }} jours</p>
            </div>
            <hr class="mt-0">
            <h4 class="mb-3">{{ books[index].title }}</h4>
            <p>Description : {{ books[index].description }}</p>
            <p>Date de publication : {{ books[index].publication_year }}</p>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped src="../../assets/style.css">

</style>