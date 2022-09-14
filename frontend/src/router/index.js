import Vue from 'vue';
import VueRouter from 'vue-router';

// LAYOUTS
import NotFound from '../pages/NotFound.vue';
import Home from '../pages/Home.vue';

import PrivateLayout from '../pages/private/Layout.vue';
import PrivateHome from '../pages/private/Home.vue';
import PrivateCreateUser from '../pages/private/CreateUser';
import PrivateCreateLoan from '../pages/private/CreateLoan';
import PrivateCreateBook from '../pages/private/CreateBook';
import PrivateCreateAuthor from '../pages/private/CreateAuthor';
import PrivateCreatePublisher from '../pages/private/CreatePublisher';
import PrivateMyLoans from '../pages/private/MyLoans';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Root',
    redirect: '/mit-bibli'
  },
  {
    path: '/mit-bibli',
    props: true,
    name: 'MIT-Blibli',
    component: Home,
    meta: {
      checkAuth: true,
      title: "MIT-Bibli",
    }
  },
  {
    path: '/mit-bibli/home',
    name: "MIT-BibliLayout",
    component: PrivateLayout,
    children: [
      {
        path: '/mit-bibli/home',
        name: 'MIT-BibliHome',
        component: PrivateHome,
        meta: {
          requiresAuth: true,
          roles: [],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/new-user',
        name: 'MIT-BibliCreateUser',
        component: PrivateCreateUser,
        meta: {
          requiresAuth: true,
          roles: ['614f6a82ef41dbbcfc990705', '614f6a9bef41dbbcfc990706', '614f6aadef41dbbcfc990707'],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/new-loan',
        name: 'MIT-BibliCreateLoan',
        component: PrivateCreateLoan,
        meta: {
          requiresAuth: true,
          roles: ['614f6a82ef41dbbcfc990705', '614f6a9bef41dbbcfc990706', '614f6aadef41dbbcfc990707'],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/new-book',
        name: 'MIT-BibliCreateBook',
        component: PrivateCreateBook,
        meta: {
          requiresAuth: true,
          roles: ['614f6a82ef41dbbcfc990705', '614f6aadef41dbbcfc990707'],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/new-author',
        name: 'MIT-BibliCreateAuthor',
        component: PrivateCreateAuthor,
        meta: {
          requiresAuth: true,
          roles: ['614f6a82ef41dbbcfc990705', '614f6aadef41dbbcfc990707'],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/new-publisher',
        name: 'MIT-BibliCreatePublisher',
        component: PrivateCreatePublisher,
        meta: {
          requiresAuth: true,
          roles: ['614f6a82ef41dbbcfc990705', '614f6aadef41dbbcfc990707'],
          title: "MIT-Bibli",
        },
      },
      {
        path: '/mit-bibli/my-loans',
        name: 'MIT-BibliMyLoans',
        component: PrivateMyLoans,
        meta: {
          requiresAuth: true,
          roles: [],
          title: "MIT-Bibli",
        },
      },
    ]
  },

  {
    path: '*',
    name: 'NotFound',
    component: NotFound,
    meta: {
      title: "404 Oops",
    },
  },
  {
    path: 'mit-bibli/*',
    name: 'NotFound2',
    component: NotFound,
    meta: {
      title: "404 Oups",
    },
  }
];

export default routes
