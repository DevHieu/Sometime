import { createRouter, createWebHistory } from "vue-router";

import DefaultLayout from "../layouts/DefaultLayout.vue";
import BlankLayout from "../layouts/BlankLayout.vue";

import Home from "../views/Home.vue";
import Search from "../views/Search.vue";
import Blog from "../views/Blog.vue";
import Profile from "../views/Profile.vue";
import Upload from "../views/Upload.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";

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

router.beforeEach(async (to, from) => {
  const isAuthenticated = sessionStorage.getItem("loggedIn") === "true";
  console.log(to);
  if (!isAuthenticated && (to.path === "/uploads" || to.path === "/profile")) {
    return { path: "/auth/login" };
  }
});

export default router;
