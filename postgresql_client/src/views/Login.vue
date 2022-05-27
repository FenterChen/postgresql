<template>
  <div class="login">
    <div
      class="absolute z-50 min-h-full w-full bg-white flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8"
    >
      <div class="max-w-md w-full space-y-5">
        <div>
          <img class="mx-auto w-1/3" src="../assets/wanin.png" alt="Workflow" />
          <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Sign in to your account
          </h2>
        </div>
        <input type="hidden" name="remember" value="true" />
        <div class="rounded-md shadow-sm -space-y-px">
          <div>
            <label for="user-id" class="sr-only">user-id</label>
            <input
              id="user-id"
              type="text"
              v-model="user.id"
              class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
              placeholder="User Id"
            />
          </div>
          <div>
            <label for="password" class="sr-only">Password</label>
            <input
              id="password"
              type="password"
              v-model="user.password"
              class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
              placeholder="Password"
            />
          </div>
        </div>
        <div v-if="validationErrors.length" class="px-3">
          <ul class="text-red-500">
            <li v-for="(error, index) in validationErrors" :key="`error-${index}`">
              {{ error }}
            </li>
          </ul>
        </div>
        <div>
          <button
            type="button"
            @click="validate"
            class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-black hover:text-white bg-gray-300 hover:bg-gray-400 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
          >
            Sign in
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "../router";
import { reactive, ref } from "vue";
import axios from "axios";
export default {
  setup() {
    const user = reactive({
      id: "test",
      password: "test",
    });
    const validationErrors = ref([]);

    const validate = () => {
      validationErrors.value = [];

      if (!user.id || !user.password) {
        if (!user.id) {
          validationErrors.value.push("E-mail cannot be empty");
        }
        if (!user.password) {
          validationErrors.value.push("Password cannot be empty");
        }
      } else {
        axios
          .post(`${process.env.VUE_APP_URL}api/login`, {
            userId: user.id,
            password: user.password,
          })
          .then((res) => {
            localStorage.setItem("personallogin", JSON.stringify(res.data));
            router.push({ name: "Home" });
          })
          .catch((e) => {
            alert(e.response.data.message);
            if (e.response.data.message == "帳號輸入錯誤，為方便測試直接建立帳號密碼") {
              axios
                .post(`${process.env.VUE_APP_URL}pi/register`, {
                  userId: user.id,
                  password: user.password,
                })
                .then((res) => {
                  localStorage.setItem("personallogin", JSON.stringify(res.data));
                  router.push({ name: "Home" });
                });
            }
          });
      }
    };

    return {
      user,
      validate,
      validationErrors,
    };
  },
};
</script>
