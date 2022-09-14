<script>
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';


export default {
  name: "NotFound",
  components: {
    Header,
    Footer
  }
}
</script>


<template>
  <div>
    <Header/>

    <div id="NotFound" class="pt-5 pb-5 d-flex justify-content-center" style="min-height: 75vh">

      <div class="text-center">
        <!--<h2 style="font-size: 8em"><i class="far fa-frown"></i></h2>-->
        <img class="glitch" src="../assets/img/404.gif" alt="glitch"/>
        <h2 class="display-4">Erreur 404</h2>
        <b-alert class="alert text-left" variant="danger" show>
          <span class="alert-icon"><span class="sr-only">Danger</span></span>
          <p>Oups, Désolé nous n'avons pas trouvé cette page... Veuillez retourner sur <router-link class="text-danger" to="/mit-bibli">Accueil</router-link></p>
        </b-alert>
      </div>
    </div>

    <Footer/>

  </div>
</template>

<style scoped src="../assets/style.css">

</style>
