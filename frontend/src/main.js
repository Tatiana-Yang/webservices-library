import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import VueRouter from "vue-router"
import VueResource from "vue-resource"
import App from './App.vue'
import routes from './router'


// Vue.config.productionTip = false

// Utils
import axios from 'axios';

// assets import
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap-grid.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap-reboot.css'
import 'bootstrap/dist/js/bootstrap'
import 'bootstrap/dist/js/bootstrap.bundle'
import 'jquery/dist/jquery'

/*import { library } from '@fortawesome/fontawesome-svg-core'
import { faSpinner } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'library.add(faSpinner)
Vue.component('font-awesome-icon', FontAwesomeIcon)*/


// plugin setup
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(BootstrapVue);


const router = new VueRouter({
    mode: 'history',
    //base: process.env.BASE_URL,
    routes,
    linkActiveClass: 'active'
});


router.beforeEach((to, from, next) => {
  // This goes through the matched routes from last to first, finding the closest route with a title.
  // eg. if we have /some/deep/nested/route and /some, /deep, and /nested have titles, nested's will be chosen.
  const nearestWithTitle = to.matched.slice().reverse().find((r) => r.meta && r.meta.title);
  if (nearestWithTitle) {
    document.title = nearestWithTitle.meta.title;
  }

  if (to.matched.some((record) => record.meta.requiresAuth)) {
      // this route requires auth, check if logged in
      axios.get('/api/users/isLoggedIn')
          .then(function(response) {
              app.id = response.data._id;
              app.firstname = response.data.firstname;
              app.lastname = response.data.lastname;
              app.dateInscription = response.data.dateInscription;
              app.isBorrower = response.data.isBorrower;
              app.login = response.data.login;
              app.role = response.data.role;

              // If the user doesn't have the good role, he is redirected to the home page
              if(to.meta.roles.length === 0 || to.meta.roles.includes(app.role)) {
                  next();
              } else {
                  next('/mit-bibli/home');
              }
          })
          .catch(function() {
              next("/mit-bibli");
              app.disconnected = 'expired';
          });
  }

  if (to.matched.some((record) => record.meta.checkAuth)) {
      // check if logged in, if yes, redirect to home page
      axios.get('/api/users/isLoggedIn')
          .then(function(response) {
              console.log(response);
              next('/mit-bibli/home');
          })
          .catch(function() {
              next();
          });
  }

  next();
});



export const app = new Vue({
    router,
    data: {
        id: null,
        firstname: "Inconnu",
        lastname: "Inconnu",
        dateInscription: new Date(),
        isBorrower: false,
        login: "Inconnu",
        role: "614f6a47ef41dbbcfc990704",
        disconnected: "",

    },
    render: h => h(App)
}).$mount('#app')
