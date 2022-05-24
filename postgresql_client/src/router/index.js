import { createWebHistory, createRouter } from "vue-router";

const routes = [
    {
        path: "/",
        name: "Home",
        component: () =>
            import("@/views/Home.vue"),
    },
    {
        path: "/login",
        name: "Login",
        component: () => import("@/views/Login.vue"),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    let personalInfo = JSON.parse(localStorage.getItem("personallogin"));
    if (to.name !== "Login" && !personalInfo) {
        next({ name: "Login" });
    } else if (to.name == "Login" && personalInfo) {
        next({ name: "Home" });
    } else next();
});

export default router;