<script>
import NavigationMenu from "../../components/NavigationMenu.vue";
import Search from "../../components/Search.vue";

export default {
  name: "Home",
  components: {
    NavigationMenu,
    Search
  }
}
</script>

<template>
  <div>
    <div class="home-banner bg-red text-white p-5">
      <div class="row justify-content-between">
        <div class="col-auto">
          <h3>Bonjour {{ this.$root.firstname }} !</h3>
        </div>
        <div class="col-auto">
          <span class="badge badge-secondary p-3" v-if="this.$parent.loans.length <= 0">Aucun emprunt en cours</span>
          <span class="badge badge-danger p-3" v-else>
            {{ this.$parent.loans.length }} emprunt(s) en cours
            <hr class="mt-2 mb-2">
            <router-link class="text-white" to="/mit-bibli/my-loans">Voir les détails</router-link>
          </span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col bg-dark-grey" style="min-height: 70vh">
        <Search/>
      </div>

      <div class="nav-col col-auto p-3 bg-grey" v-if="this.$root.role !== '614f6a47ef41dbbcfc990704'">
        <NavigationMenu/>
      </div>
    </div>
  </div>
</template>



<style scoped src="../../assets/style.css">

</style>
