<script>
//import axios from "axios";

import axios from "axios";

export default {
  name: "CreateAuthor",
  data() {
    return {
      registered: false,
      error: '',
      name: ''
    }
  },
  methods: {
    createAuthor: function(e) {
      e.preventDefault();
      this.registered = false;
      this.error = false;

      let data = {
        name: this.name,
      }
      // ajax call using the credentials above
      axios.post("/spring/author/addByForm", data)
          .then(() => {
            this.registered = true;
            this.name = '';
          })
          .catch((errors) => {
            console.log(errors);
            this.error = true;
            this.name = '';
          })
    }
  }
}
</script>

<template>
  <div class="bg-dark-grey p-5">
    <form v-on:submit="createAuthor" style="min-height: 70vh">
      <div class="row justify-content-center">
        <div class="col-auto bg-grey div-round p-5">
          <h2>Nouvel auteur</h2>
          <hr>
          <div class="form-group">
            <label for="InputName">Nom</label>
            <input type="text" id="InputName" class="form-control" v-model="name">
          </div>

          <b-alert class="alert-form" variant="success" :show="this.registered">
            <span class="alert-icon"><span class="sr-only">Success</span></span>
            <p>L'enregistrement s'est effectué avec succès</p>
          </b-alert>
          <b-alert class="alert-form" variant="danger" :show="this.error.length > 0">
            <span class="alert-icon"><span class="sr-only">Danger</span></span>
            <p>{{ this.error }}</p>
          </b-alert>
          <button type="submit" class="btn btn-primary mr-3" :disabled="this.name === ''">Enregistrer</button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped src="../../assets/style.css">

</style>