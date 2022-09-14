<script>
import SignInForm from '../components/SignInForm.vue';
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';

export default {
  name: "SignIn",
  components: {
    Header,
    SignInForm,
    Footer
  }
}
</script>


<template>
  <div id="Layout" style="overflow: hidden">
    <Header/>

    <div class="bg-dark-grey p-5">
      <b-alert class="alert-signin" variant="success" :show="this.$root.disconnected === 'loggedout'">
        <span class="alert-icon"><span class="sr-only">Success</span></span>
        <p>Vous vous êtes déconnecté avec succès.</p>
      </b-alert>

      <b-alert class="alert-signin" variant="warning" :show="this.$root.disconnected === 'expired'">
        <span class="alert-icon"><span class="sr-only">Danger</span></span>
        <p>Votre session est expirée. Vous avez été déconnecté.</p>
      </b-alert>

      <div class="container">
        <div class="row justify-content-center">
          <div class="col-auto">
            <SignInForm class="p-5 bg-grey div-round"/>
          </div>
        </div>
      </div>
    </div>

    <Footer class="footer"/>
  </div>
</template>

<style scoped src="../assets/style.css">

</style>
