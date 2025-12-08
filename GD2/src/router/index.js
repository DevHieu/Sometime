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
        meta: { requiresAuth: true },
      },
      {
        path: "uploads",
        component: Upload,
        meta: { requiresAuth: true },
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

router.beforeEach((to, from, next) => {
  const isAuthenticated = sessionStorage.getItem("loggedIn") === "true";
  if (
    to.matched.some((record) => record.meta.requiresAuth) &&
    !isAuthenticated.value
  ) {
    next({ path: "/auth/login" });
  } else {
    next();
  }
});

export default router;
