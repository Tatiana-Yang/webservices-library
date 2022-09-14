<script>
import Search from "../../components/Search.vue";
import axios from "axios";

export default {
  name: "CreateBook",
  components: {
    Search
  },
  data() {
    return {
      registered: false,
      error: false,
      title: '',
      publicationYear: 2000,
      description: '',
      state: 0,
      publisherID: '',
      authorID: ''
    }
  },
  methods: {
    createBook: function(e) {
      e.preventDefault();
      this.registered = false;
      this.error = false;

      let data = {
        title: this.title,
        publication_year: this.publicationYear,
        description: this.description,
        state: this.state,
        id_publisher: parseInt(this.publisherID),
        id_authors: [parseInt(this.authorID)]
      }
      // ajax call using the credentials above
      axios.post("/spring/book/add", data)
          .then(() => {
            this.registered = true;
          })
          .catch((errors) => {
            console.log(errors);
            this.error = true;
            this.title = '';
            this.publicationYear = 2000;
            this.description = '';
            this.state = 0;
            this.publisherID = '';
            this.authorID = '';
          })
    }
  }
}
</script>

<template>
  <div class="bg-dark-grey p-5">
    <form v-on:submit="createBook" style="min-height: 70vh">
      <div class="row justify-content-center">
        <div class="col-auto bg-grey div-round p-5">
          <h2>Nouvel ouvrage</h2>
          <hr>
          <div class="form-group row">
            <div class="col">
              <label for="InputTitle">Titre</label>
              <input type="text" id="InputTitle" class="form-control" v-model="title">
            </div>
            <div class="col-4">
              <label for="InputPublicationYear">Année de publication</label>
              <input type="number" id="InputPublicationYear" class="form-control" v-model="publicationYear">
            </div>
          </div>
          <div class="form-group">
            <label for="descriptionControlTextarea">Description</label>
            <textarea class="form-control" id="descriptionControlTextarea" rows="5" v-model="description"></textarea>
          </div>

          <!-- STATE DÉFINI PAR DÉFAUT À 0 -->

          <div class="form-group">
            <label for="InputAuthor">ID de l'auteur</label>
            <input type="text" id="InputAuthor" class="form-control" v-model="authorID">
          </div>
          <div class="form-group">
            <label for="InputPublisher">ID de l'éditeur</label>
            <input type="text" id="InputPublisher" class="form-control" v-model="publisherID">
          </div>

          <b-alert class="alert-form" variant="success" :show="this.registered">
            <span class="alert-icon"><span class="sr-only">Success</span></span>
            <p>L'enregistrement s'est effectué avec succès</p>
          </b-alert>
          <b-alert class="alert-form" variant="danger" :show="this.error">
            <span class="alert-icon"><span class="sr-only">Danger</span></span>
            <p>Une erreur est survenue</p>
          </b-alert>
          <button type="submit" class="btn btn-primary mr-3" :disabled="this.title === '' || this.description === '' || this.publisherID === '' || this.authorID === ''">Enregistrer</button>
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