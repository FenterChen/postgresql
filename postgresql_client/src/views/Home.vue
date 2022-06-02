<template>
  <div class="max-w-8xl m-auto min-h-screen flex items-center justify-center">
    <div class="md:grid md:grid-cols-2 md:gap-6 items-center">
      <div class="mt-5 md:mt-0 md:col-span-3">
        <div class="shadow overflow-hidden sm:rounded-md">
          <div class="px-4 py-3 bg-gray-50 sm:px-6">
            <button
              @click="showUserinfo"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': infoIsclick }"
            >
              <div class="text-base">UserInfo</div>
            </button>
            <button
              @click="showEquip"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': equipIsclick }"
            >
              <div class="text-base">Equipments</div>
            </button>
            <button
              @click="showForge"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': forgeIsclick }"
            >
              <div class="text-base">Forge Plant</div>
            </button>
          </div>
          <div v-if="infoIsclick" class="px-4 py-5 bg-white sm:p-6">
            <UserInfo></UserInfo>
          </div>
          <div v-if="equipIsclick" class="px-4 py-5 bg-white sm:p-6">
            <Equipments></Equipments>
          </div>
          <div v-if="forgeIsclick" class="px-4 py-5 bg-white sm:p-6">
            <Forge></Forge>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import UserInfo from "@/components/UserInfo";
import Equipments from "@/components/Equipments";
import Forge from "@/components/Forge";

import store from "@/store";
import { ref } from "vue";
export default {
  components: {
    UserInfo,
    Equipments,
    Forge,
  },
  setup() {
    store.dispatch("refresh");
    const infoIsclick = ref(true);
    const equipIsclick = ref(false);
    const forgeIsclick = ref(false);

    const showUserinfo = () => {
      infoIsclick.value = true;
      equipIsclick.value = false;
      forgeIsclick.value = false;
    };
    const showEquip = () => {
      equipIsclick.value = true;
      infoIsclick.value = false;
      forgeIsclick.value = false;
    };
    const showForge = () => {
      forgeIsclick.value = true;
      infoIsclick.value = false;
      equipIsclick.value = false;
    };

    return {
      infoIsclick,
      equipIsclick,
      forgeIsclick,
      showUserinfo,
      showEquip,
      showForge,
    };
  },
};
</script>
