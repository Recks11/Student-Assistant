import Vue from 'vue';
import App from './App.vue';
import {router} from './router';
import store from './store';
import axios from 'axios';

Vue.config.productionTip = false;
axios.defaults.timeout = 10000;
axios.defaults.responseType = 'json';
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://localhost:8080';
axios.create({
    baseURL: `http://localhost:8080`,
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8081',
    },
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
