import Register from "./../pages/register/Register.vue";
import Login from "./../pages/auth/Login.vue";

export default [
  { path: "/", component: Login },
  { path: "/register", component: Register },
  { path: "/login", component: Login }
];
