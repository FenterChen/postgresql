<template>
  <div class="grid grid-cols-6 gap-6" v-if="user">
    <HomeSave :Open="rightOpen" @update="selfConfirm"></HomeSave>
    <div class="col-span-6 sm:col-span-3">
      <p class="text-gray-700">
        Id:<span class="px-2">{{ user.id }}</span>
      </p>
    </div>
    <div class="col-span-6 sm:col-span-3">
      <p class="text-gray-700">
        Userid:<span class="px-2">{{ user.userId }}</span>
      </p>
    </div>
    <div class="col-span-6 sm:col-span-4">
      <label for="Username" class="block text-sm font-medium text-gray-700"
        >Username</label
      >
      <input
        v-model="user.userName"
        type="text"
        name="Username"
        id="Username"
        class="mt-1 py-2 px-3 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
      />
    </div>
    <div class="col-span-6 sm:col-span-3">
      <label for="Role" class="block text-sm font-medium text-gray-700">Role</label>
      <select
        v-model="user.role"
        id="Role"
        name="Role"
        class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
      >
        <option>Warrior</option>
        <option>Ninja</option>
      </select>
    </div>
    <div class="px-4 py-3 col-span-6 flex justify-between sm:px-6">
      <button
        @click="save"
        class="justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
      >
        Save
      </button>
      <button
        @click="logout"
        class="justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
      >
        Logout
      </button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import store from "@/store";
import HomeSave from "@/modal/Homesave";
import axios from "axios";
import { ref, computed } from "vue";

export default {
  components: {
    HomeSave,
  },
  setup() {
    const user = computed(() => {
      return store.state.userContent;
    });
    const rightOpen = ref(true);
    const save = () => {
      axios
        .put(`${process.env.VUE_APP_URL}user`, {
          id: user.value.id,
          userName: user.value.userName,
          role: user.value.role,
        })
        .then(() => {
          rightOpen.value = false;
        })
        .catch((e) => {
          alert(e.response.data.message);
        });
    };

    const logout = () => {
      localStorage.removeItem("personallogin");
      store.commit('cleanUser')
      router.push({ name: "Login" });
    };


    const selfConfirm = () => {
      rightOpen.value = true;
    };

    return {
      save,
      logout,
      selfConfirm,
      rightOpen,
      user,
    };
  },
};
</script>
