import Vue from 'vue';
import App from './App.vue';
import {router} from './router';
import store from './store';
import axios from 'axios';

Vue.config.productionTip = false;
axios.defaults.timeout = 5000;
axios.defaults.responseType = 'json';
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.create();

router.beforeEach((to, from, next) => {
    if (store.getters['login/LOGGED_IN'] === false && to.path!== '/login') {
        next('/login');
    } else {
        next();
    }
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
