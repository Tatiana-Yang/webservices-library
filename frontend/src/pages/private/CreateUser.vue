<script>
import axios from "axios";

export default {
  name: "CreateUser",
  data() {
    return {
      registered: false,
      error: '',
      firstname: '',
      lastname: '',
      login: '',
      role: '614f6a47ef41dbbcfc990704',
    }
  },
  methods: {
    createUser: function(e) {
      e.preventDefault();
      this.registered = false;
      this.error = false;

      let data = {
        firstname: this.firstname,
        lastname: this.lastname,
        login: this.login,
        role: this.role
      }
      // ajax call using the credentials above
      axios.post("/api/users/signUp", data)
          .then(() => {
            this.registered = true;
          })
          .catch((errors) => {
            console.log(errors);
            this.error = true;
          })
      this.reset();
    },
    reset: function () {
      this.firstname = '';
      this.lastname = '';
      this.login = '';
      this.role = '614f6a47ef41dbbcfc990704';
    }
  }
}
</script>

<template>
  <div class="bg-dark-grey p-5">
    <form v-on:submit="createUser" style="min-height: 70vh">
      <div class="row justify-content-center">
        <div class="col-auto bg-grey div-round p-5">
          <h2>Nouvel utilisateur</h2>
          <hr>
          <div class="form-group row">
            <div class="col">
              <label for="InputFirstname">Prénom</label>
              <input type="text" id="InputFirstname" class="form-control" v-model="firstname">
            </div>
            <div class="col">
              <label for="InputLastname">Nom</label>
              <input type="text" id="InputLastname" class="form-control" v-model="lastname">
            </div>
          </div>
          <div class="form-group">
            <label for="InputLogin">Identifiant</label>
            <input type="text" id="InputLogin" class="form-control" v-model="login">
          </div>

          <div class="form-group">
            <div class="form-check">
              <input class="form-check-input" type="radio" name="exampleRadios" id="consultRadios" value="614f6a47ef41dbbcfc990704" checked v-model="role">
              <label class="form-check-label" for="consultRadios">
                Utilisateur
              </label>
            </div>
            <div class="form-check" v-if="this.$root.role === '614f6aadef41dbbcfc990707'">
              <input class="form-check-input" type="radio" name="exampleRadios" id="borrowRadios" value="614f6a9bef41dbbcfc990706" v-model="role">
              <label class="form-check-label" for="borrowRadios">
                Agent de bibliothèque
              </label>
            </div>
            <div class="form-check" v-if="this.$root.role === '614f6aadef41dbbcfc990707'">
              <input class="form-check-input" type="radio" name="exampleRadios" id="contribRadios" value="614f6a82ef41dbbcfc990705" v-model="role">
              <label class="form-check-label" for="contribRadios">
                Bibliothécaire
              </label>
            </div>
            <div class="form-check" v-if="this.$root.role === '614f6aadef41dbbcfc990707'">
              <input class="form-check-input" type="radio" name="exampleRadios" id="adminRadios" value="614f6aadef41dbbcfc990707" v-model="role">
              <label class="form-check-label" for="adminRadios">
                Administrateur
              </label>
            </div>
          </div>

          <b-alert class="alert-form" variant="success" :show="this.registered">
            <span class="alert-icon"><span class="sr-only">Success</span></span>
            <p>L'enregistrement s'est effectué avec succès</p>
          </b-alert>
          <b-alert class="alert-form" variant="danger" :show="this.error">
            <span class="alert-icon"><span class="sr-only">Danger</span></span>
            <p>Une erreur est survenue</p>
          </b-alert>
          <button type="submit" class="btn btn-primary mr-3" :disabled="this.firstname === '' || this.lastname === '' || this.login === ''">Enregistrer</button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped src="../../assets/style.css">

</style>