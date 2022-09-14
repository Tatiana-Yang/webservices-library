<script>
export default {
  name: "NavigationMenu.vue",
}
</script>

<template>
  <div>
    <div id="large-screen">
      <div class="container">
        <router-link to="/mit-bibli/new-user" class="card mb-3" style="max-width: 540px;">
          <div class="row no-gutters">
            <div class="col-md-4">
              <p style="text-align: center" class="mt-3"><i class="fas fa-user"></i></p>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">Nouvel utilisateur</h5>
                <p class="card-text">Enregistrer un nouvel utilisateur</p>
              </div>
            </div>
          </div>
        </router-link>

        <router-link to="/mit-bibli/new-loan" class="card mb-3" style="max-width: 540px;">
          <div class="row no-gutters">
            <div class="col-md-4">
              <p style="text-align: center" class="mt-3"><i class="fas fa-book-reader"></i></p>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">Nouvel emprunt</h5>
                <p class="card-text">Enregistrer un nouvel emprunt</p>
              </div>
            </div>
          </div>
        </router-link>

        <router-link to="/mit-bibli/new-book" class="card mb-3" style="max-width: 540px;" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
          <div class="row no-gutters">
            <div class="col-md-4">
              <p style="text-align: center" class="mt-3"><i class="fas fa-book"></i></p>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">Nouvel ouvrage</h5>
                <p class="card-text">Enregistrer un nouvel ouvrage</p>
              </div>
            </div>
          </div>
        </router-link>

        <router-link to="/mit-bibli/new-author" class="card mb-3" style="max-width: 540px;" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
          <div class="row no-gutters">
            <div class="col-md-4">
              <p style="text-align: center" class="mt-3"><i class="fas fa-pen-nib"></i></p>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">Nouvel auteur</h5>
                <p class="card-text">Enregistrer un nouvel auteur</p>
              </div>
            </div>
          </div>
        </router-link>

        <router-link to="/mit-bibli/new-publisher" class="card mb-3" style="max-width: 540px;" v-if="this.$root.role === '614f6a82ef41dbbcfc990705' || this.$root.role === '614f6aadef41dbbcfc990707'">
          <div class="row no-gutters">
            <div class="col-md-4">
              <p style="text-align: center" class="mt-3"><i class="fas fa-address-book"></i></p>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">Nouvel éditeur</h5>
                <p class="card-text">Enregistrer un nouvel éditeur</p>
              </div>
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped src="../assets/style.css">

</style>