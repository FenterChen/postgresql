<template>
  <div class="flex">
    <div class="relative flex shadow-md sm:rounded-lg">
      <div class="m-2">
        <div class="input-group flex mb-4">
          <input
            type="search"
            v-model="newEquip"
            class="form-control flex-auto min-w-0 block px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-400 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-gray-500 focus:outline-none"
            aria-label="Search"
            aria-describedby="button-addon3"
          />
          <button
            @click="Add"
            class="px-6 py-2 bg-gray-400 border-2 border-gray-500 text-white font-bold text-l leading-tight rounded hover:bg-gray hover:bg-gray-600"
            type="button"
            id="button-addon3"
          >
            Forging
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { ref, computed } from "vue";
import axios from "axios";

export default {
  setup() {
    const newEquip = ref();
    const user = computed(() => {
      return store.state.userContent;
    });
    const Add = () => {
      axios
        .post("api/Gameusers/adduserdata", {
          Userid: user.value.userid,
          Equipment: newEquip.value,
        })
        .then(() => {
          newEquip.value = "";
        })
        .catch(function (err) {
          alert(err);
        });
    };
    return {
      user,
      newEquip,
      Add,
    };
  },
};
</script>
