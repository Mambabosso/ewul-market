// import scss

import "./style.scss";

// import

import Vue from "vue";

import BootstrapVue from "bootstrap-vue";

// import emw

import App from "./App.vue";

import VueI18n from "./i18n/index";

import Router from "./router/index";

import "./widgets/index";

// vue use

Vue.use(BootstrapVue);

// start

console.log("ewul-market-webapp");

document.write('<div id="emw"></div>');

const v = new Vue({
  el: "#emw",
  i18n: VueI18n,
  router: Router,
  render: (h) => h(App)
});

export default v;
