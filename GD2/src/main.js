import { createApp } from "vue";
import { createRouter, createWebHistory } from "vue-router";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "primeicons/primeicons.css";
import "./custom-bs5.css";
import "./style.css";

import DefaultLayout from "./layouts/DefaultLayout.vue";
import BlankLayout from "./layouts/BlankLayout.vue";

import App from "./App.vue";
import Home from "./views/Home.vue";
import Search from "./views/Search.vue";
import Blog from "./views/Blog.vue";
import Profile from "./views/Profile.vue";
import Upload from "./views/Upload.vue";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";

import axios from "axios";

axios.defaults.baseURL = "http://localhost:9090/api";
axios.defaults.withCredentials = true;

const routes = [
  {
    path: "/",
    component: DefaultLayout,
    children: [
      {
        path: "",
        component: Home,
      },
      {
        path: "search",
        component: Search,
      },
      {
        path: "blog",
        component: Blog,
      },
      {
        path: "profile",
        component: Profile,
      },
      {
        path: "uploads",
        component: Upload,
      },
    ],
  },

  {
    path: "/auth",
    component: BlankLayout,
    children: [
      {
        path: "login",
        component: Login,
      },
      {
        path: "register",
        component: Register,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

createApp(App).use(router).mount("#app");
