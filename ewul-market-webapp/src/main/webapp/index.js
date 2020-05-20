import Vue from "vue";
import App from "./App.vue";

console.log("ewul-market-webapp");



document.writeln("<div id=\"emw\"></div>");

const v = new Vue({
    el: "#emw",
    render: (h) => h(App)
});


export default v;
