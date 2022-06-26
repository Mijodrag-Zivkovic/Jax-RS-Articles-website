import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.config.productionTip = false

Vue.filter('capitalize', function(value) {
  if (!value) {
    return '';
  }

  value = value.toString();

  return value.charAt(0).toUpperCase() + value.slice(1)
})

Vue.mixin({
  methods: {
    globalIfLoggedIn: function () {
        if(localStorage.getItem("jwt"))
        {
          let token = localStorage.getItem("jwt");
          var base64Url = token.split('.')[1];
          var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
          var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
          }).join(''));
          let payload = JSON.parse(jsonPayload);
          if(payload.status == "active")
            return true;
          else return false;
        }
        else return false;
    },
    globalIfAdminLoggedIn: function () {
      if(localStorage.getItem("jwt"))
      {
        let token = localStorage.getItem("jwt");
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        let payload = JSON.parse(jsonPayload);
        console.log(payload);
        console.log(payload.userType)
        if(payload.userType == "admin")
        {
          return true
        }
        else return false;
      }
      else return false;
    },
  },
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

