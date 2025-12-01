import { createApp } from "vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "primeicons/primeicons.css";
import "./custom-bs5.css";
import "./style.css";

import App from "./App.vue";

import axios from "axios";
import router from "./router";

axios.defaults.baseURL = "http://localhost:9090/api";
axios.defaults.withCredentials = true;

createApp(App).use(router).mount("#app");
