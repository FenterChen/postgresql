import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "./assets/tailwind.css";
import 'flowbite';
import store from "./store";
import { gsap } from "gsap";

createApp(App).use(router).use(store).use(gsap).mount('#app');
