<script>
import Search from "../../components/Search.vue";
import axios from "axios";

export default {
  name: "CreateLoan",
  components: {
    Search
  },
  data() {
    return {
      registered: false,
      error: false,
      login: '',
      userID: '',
      bookID: '',
      borrowingDays: 15,
      search: ''
    }
  },
  methods: {
    createLoan: function(e) {
      e.preventDefault();
      this.registered = false;
      this.error = false;

      let data = {
        login: this.login
      }
      axios.post("/api/users/getId", data)
          .then((response) => {
            console.log(response);
            this.userID = response.data.toString();
            data = {
              id_user: this.userID,
              id_book: parseInt(this.bookID),
              borrowing_days: this.borrowingDays
            }
            axios.post("/spring/loan/add", data)
                .then(() => {
                  this.registered = true;
                })
                .catch((errors) => {
                  console.log(errors);
                  this.error = true;
                  this.login = '';
                  this.userID = '';
                  this.bookID = '';
                  this.borrowingDays = 15;
                })
          })
          .catch((errors) => {
            console.log(errors);
            this.error = true;
          })
    }
  }
}
</script>

<template>
  <div class="bg-dark-grey p-5">
    <form v-on:submit="createLoan">
      <div class="row justify-content-center">
        <div class="col-auto bg-grey div-round p-5">
          <h2>Nouvel emprunt</h2>
          <hr>
          <div class="form-group">
            <label for="InputLogin">Login utilisateur</label>
            <input type="text" id="InputLogin" class="form-control" v-model="login">
          </div>
          <div class="form-group">
            <label for="InputBookID">ID de l'ouvrage</label>
            <input type="text" id="InputBookID" class="form-control" v-model="bookID">
          </div>
          <div class="form-group">
            <label for="BorrowingDaysControlSelect">Durée de l'emprunt</label>
            <select class="form-control" id="BorrowingDaysControlSelect" v-model="borrowingDays">
              <option :value="7">Une semaine</option>
              <option :value="15">Deux semaines</option>
              <option :value="30">Un mois</option>
              <option :value="90">Trois mois</option>
            </select>
          </div>

          <b-alert class="alert-form" variant="success" :show="this.registered">
            <span class="alert-icon"><span class="sr-only">Success</span></span>
            <p>L'enregistrement s'est effectué avec succès</p>
          </b-alert>
          <button type="submit" class="btn btn-primary mr-3" :disabled="this.login === '' || this.bookID === ''">Enregistrer</button>
        </div>

        <div class="col ml-5 bg-grey div-round text-white" style="min-height: 70vh">
          <Search/>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped src="../../assets/style.css">

</style>