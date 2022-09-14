<script>
import axios from "axios";

export default {
  name: "SignInForm",
  props: ['errorType'],
  data() {
    return {
      unauthorized: false,
      login: '',
      password: ''
    }
  },
  methods: {
    signIn: function(e) {
      e.preventDefault()
      this.$root.disconnected = '';
      this.unauthorized = false;

      let data = {
        login: this.login,
        password: this.password
      }
      // ajax call using the credentials above
      axios.post("/api/users/signIn", data)
          .then((response) => {
            console.log(response);
            this.$router.push("/mit-bibli/home");
          })
          .catch((errors) => {
            console.log(errors);
            this.unauthorized = true;
            this.login = '';
            this.password = '';
          })
    }
  }
}
</script>

<template>
  <div class="m-5" style="min-height: 60vh">
    <form v-on:submit="signIn">
      <div class="row">
        <div class="col">
          <h2>Connexion</h2>
          <hr>
          <div class="form-group">
            <label for="InputLogin">Identifiant</label>
            <input type="text" id="InputLogin" class="form-control" v-model="login">
          </div>
          <div class="form-group">
            <label for="InputPassword">Mot de passe</label>
            <input type="password" id="InputPassword" class="form-control" v-model="password">
          </div>
          <b-alert class="alert-form" variant="danger" :show="unauthorized">
            <span class="alert-icon"><span class="sr-only">Danger</span></span>
            <p>Erreur : votre identifiant ou votre mot de passe est incorrect. Essayez à nouveau.</p>
          </b-alert>
          <button type="submit" class="btn btn-primary mr-3">Se connecter</button>
          <a role="button" class="text-left text-white" data-toggle="modal" data-target="#ForgotPassword">Mot de passe oublié ?</a>
        </div>
      </div>
    </form>

    <!-- Forgot Password Modal -->
    <div class="modal fade" id="ForgotPassword" tabindex="-1" aria-labelledby="ForgotPasswordLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content bg-grey">
          <div class="modal-header">
            <h5 class="modal-title" id="ForgotPasswordLabel">Mot de passe oublié ?</h5>
            <button type="button" class="close" data-dismiss="modal">
              <span class="sr-only">Close live modal demo</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Veuillez vous rapprocher d'un agent de bibliothèque pour réinitialiser votre mot de passe.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped src="../assets/style.css">

</style>