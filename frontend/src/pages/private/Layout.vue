<script>
import Footer from "../../components/Footer";
const delay = require('delay');
const axios = require('axios');

export default {
  name: "Layout",
  components: {
    Footer,
  },
  data() {
    return {
      loading: false,
      login: '',
      password: '',
      confirmPassword: '',
      alert: '',
      error: '',
      success: '',
      checked: false,
      loans: []
    }
  },
  methods: {
    signOut: function(e) {
      e.preventDefault()
      axios.get("/api/users/signOut")
          .then(() => {
            this.$router.push("/mit-bibli");
            this.$root.disconnected = "loggedout";
          })
    },
    changeLogin: function() {
      if(this.login !== '') {
        let data = {
          login: this.login
        }
        axios.post("/api/users/changeLogin", data)
            .then((response) => {
              console.log(response);
              this.alert = '';
              this.error = '';
              this.success = "L'identifiant a été modifié avec succès.";
            })
            .catch((errors) => {
              console.log(errors);
              this.alert = '';
              this.error = "Une erreur est survenue.";
            })
      }
      else {
        this.alert = "Le champ est vide.";
      }
    },
    changePassword: function() {
      if(this.password !== '' && this.password === this.confirmPassword) {
        let data = {
          password: this.password
        }
        axios.post("/api/users/changePassword", data)
            .then((response) => {
              console.log(response);
              this.alert = '';
              this.error = '';
              this.success = "Le mot de passe a été modifié avec succès.";
            })
            .catch((errors) => {
              console.log(errors);
              this.alert = '';
              this.error = "Une erreur est survenue.";
            })
      }
      else {
        if(this.password === '' || this.confirmPassword === '') {
          this.alert = "Tout les champs doivent être remplis.";
        }
        else {
          this.alert = "Le mot de passe est incorrect.";
        }
      }
    },
    getUserLoan: function() {
      this.loading = true;

      let data = {
        id_user: this.$root.id.toString()
      }
      axios.post("/spring/loan/loanByUser", data)
          .then((response) => {
            this.loans = response.data;
          })
          .catch((errors) => {
            console.log(errors);
          })
      this.loading = false;
    },
    reset: function() {
      if(this.success !== '') {
        location.reload();
      }
      else {
        this.alert = '';
        this.error = '';
        this.success = '';
        this.checked = false;
        this.login = '';
        this.password = '';
        this.confirmPassword = '';
      }
    }
  },
  async mounted() {
    await delay(1000);
    this.getUserLoan();
  }
}
</script>

<template>
  <div>
    <header role="banner">
      <nav class="navbar navbar-expand-lg navbar-dark">
        <router-link class="row navbar-brand ml-5 logo" to="/mit-bibli/home">
          <img src="../../assets/img/MIT_logo2.png" alt="MIT" style="width: 100%">
        </router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item" v-if="this.$root.role !== '614f6a47ef41dbbcfc990704'">
              <router-link to="/mit-bibli/new-user" class="nav-link" role="button"><i class="fas fa-user-plus mr-2"></i>Nouvel utilisateur</router-link>
            </li>
            <li class="nav-item" v-if="this.$root.role !== '614f6a47ef41dbbcfc990704'">
              <router-link to="/mit-bibli/new-loan" class="nav-link" role="button"><i class="fas fa-book-reader mr-2"></i>Nouvel emprunt</router-link>
            </li>
            <li class="nav-item" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
              <router-link to="/mit-bibli/new-book" class="nav-link" role="button"><i class="fas fa-book mr-2"></i>Nouvel ouvrage</router-link>
            </li>
            <li class="nav-item" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
              <router-link to="/mit-bibli/new-author" class="nav-link" role="button"><i class="fas fa-pen-nib mr-2"></i>Nouvel auteur</router-link>
            </li>
            <li class="nav-item" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
              <router-link to="/mit-bibli/new-publisher" class="nav-link" role="button"><i class="fas fa-address-book mr-2"></i>Nouvel éditeur</router-link>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-cog mr-2"></i>{{ this.$root.login }}</a>
              <div class="dropdown-menu user-param" aria-labelledby="navbarDropdown">
                <a role="button" class="dropdown-item" data-toggle="modal" data-target="#loginModal"><i class="fas fa-id-card mr-2"></i>Changer d'identifiant</a>
                <div class="dropdown-divider"></div>
                <a role="button" class="dropdown-item" data-toggle="modal" data-target="#passwordModal"><i class="fas fa-key mr-2"></i>Changer de mot de passe</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" v-on:click="signOut" role="button"><i class="fas fa-power-off mr-2"></i>Se déconnecter</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </header>

    <router-view/>

    <Footer/>





    <!-- Login Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content bg-grey">
          <div class="modal-header">
            <h3 class="modal-title" id="loginModalLabel">Changement de votre identifiant</h3>
            <!-- Boosted mod: using visually hidden text instead of aria-label -->
            <button type="button" class="close" data-dismiss="modal" v-on:click="reset">
              <span class="sr-only">Close live modal demo</span>
            </button>
          </div>
          <div class="modal-body">
            <b-alert class="alert-signin" variant="success" :show="this.success !== ''">
              <span class="alert-icon"><span class="sr-only">Success</span></span>
              <p>{{ this.success }}</p>
            </b-alert>
            <b-alert class="alert-signin" variant="danger" :show="this.error !== ''">
              <span class="alert-icon"><span class="sr-only">Danger</span></span>
              <p>{{ this.error }}</p>
            </b-alert>
            <b-alert class="alert-signin" variant="warning" :show="this.alert !== ''">
              <span class="alert-icon"><span class="sr-only">Warning</span></span>
              <p>{{ this.alert }}</p>
            </b-alert>

            <h5>Entrez votre nouvel identifiant</h5>
            <input type="text" class="form-control" aria-label="username" v-model="login">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary mr-4" data-dismiss="modal" v-on:click="reset">Fermer</button>
            <button type="button" class="btn btn-primary" :disabled="this.login === ''" v-on:click="changeLogin">Enregistrer</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Password Modal -->
    <div class="modal fade" id="passwordModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content bg-grey">
          <div class="modal-header">
            <h3 class="modal-title" id="passwordModalLabel">Changement de votre mot de passe</h3>
            <!-- Boosted mod: using visually hidden text instead of aria-label -->
            <button type="button" class="close" data-dismiss="modal" v-on:click="reset">
              <span class="sr-only">Close live modal demo</span>
            </button>
          </div>
          <div class="modal-body">
            <b-alert class="alert-signin" variant="success" :show="this.success !== ''">
              <span class="alert-icon"><span class="sr-only">Success</span></span>
              <p>{{ this.success }}</p>
            </b-alert>
            <b-alert class="alert-signin" variant="danger" :show="this.error !== ''">
              <span class="alert-icon"><span class="sr-only">Danger</span></span>
              <p>{{ this.error }}</p>
            </b-alert>
            <b-alert class="alert-signin" variant="warning" :show="this.alert !== ''">
              <span class="alert-icon"><span class="sr-only">Warning</span></span>
              <p>{{ this.alert }}</p>
            </b-alert>

            <div class="form-group">
              <h5>Entrez votre nouveau mot de passe</h5>
              <input type="password" class="form-control" aria-label="password" v-model="password">
            </div>
            <div class="form-group">
              <h5 class="mt-3">Confirmez votre nouveau mot de passe</h5>
              <input type="password" class="form-control" aria-label="password" v-model="confirmPassword">
            </div>
            <div class="form-group ml-5">
              <input type="checkbox" class="custom-control-input" id="check" v-model="checked">
              <label class="custom-control-label" for="check">Je suis sûr de moi</label>
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary mr-4" data-dismiss="modal" v-on:click="reset">Fermer</button>
            <button type="button" class="btn btn-primary" :disabled="this.password === '' || this.confirmPassword === '' || this.password !== this.confirmPassword || !this.checked" v-on:click="changePassword">Enregistrer</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<style scoped src="../../assets/style.css">

</style>