import { createApp } from 'vue'
import App from "~/App.vue";
import "~/assets/styles/global.css"
import router from "~/route/router.ts";

createApp(App)
    .use(router)
    .mount('#app')
