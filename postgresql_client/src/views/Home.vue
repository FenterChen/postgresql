<template>
  <div class="max-w-8xl m-auto min-h-screen flex items-center justify-center">
    <!-- <div
      class="fixed z-10 inset-0 overflow-y-auto"
      aria-labelledby="modal-title"
      role="dialog"
      aria-modal="true"
      :class="{ invisible: showdelete }"
    >
      <div
        class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"
      >
        <div
          class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
          aria-hidden="true"
        ></div>
        <span
          class="hidden sm:inline-block sm:align-middle sm:h-screen"
          aria-hidden="true"
          >&#8203;</span
        >
        <div
          class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
        >
          <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div class="sm:flex sm:items-start">
              <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                  Warning!
                </h3>
                <div class="mt-2">
                  <p class="text-sm text-gray-500">
                    Are you sure you want to destroy this equipment?
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button
              type="button"
              class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-gray-400 text-base font-medium text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 sm:ml-3 sm:w-auto sm:text-sm"
              @click="Cancel"
            >
              Cancel
            </button>
            <button
              type="button"
              class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm"
              @click="deleteConfirm"
            >
              Confirm
            </button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="fixed z-10 inset-0 overflow-y-auto"
      aria-labelledby="modal-title"
      role="dialog"
      aria-modal="true"
      :class="{ invisible: showdedit }"
    >
      <div
        class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"
      >
        <div
          class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
          aria-hidden="true"
        ></div>
        <span
          class="hidden sm:inline-block sm:align-middle sm:h-screen"
          aria-hidden="true"
          >&#8203;</span
        >

        <div
          class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
        >
          <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div class="sm:flex sm:items-start">
              <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                  Edit
                </h3>
                <div class="mt-2">
                  <label
                    for="editEquipment"
                    class="block text-sm font-medium text-gray-700"
                    >equipmentName</label
                  >
                  <input
                    v-if="editId"
                    v-model="editId.equipmentName"
                    type="text"
                    name="equipmentName"
                    id="editEquipment"
                    class="mt-1 py-2 px-3 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button
              type="button"
              class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-gray-400 text-base font-medium text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 sm:ml-3 sm:w-auto sm:text-sm"
              @click="Cancel"
            >
              Cancel
            </button>
            <button
              type="button"
              class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm"
              @click="editConfirm"
            >
              Confirm
            </button>
          </div>
        </div>
      </div>
    </div>  -->
    <div class="md:grid md:grid-cols-2 md:gap-6 items-center">
      <div class="mt-5 md:mt-0 md:col-span-3">
        <div class="shadow overflow-hidden sm:rounded-md">
          <div class="px-4 py-3 bg-gray-50 sm:px-6">
            <button
              @click="showUserinfo"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': infoIsclick }"
            >
              UserInfo
            </button>
            <button
              @click="showEquip"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': equipIsclick }"
            >
              Equipments
            </button>
            <button
              @click="showForge"
              class="justify-center py-1 mx-2 shadow-sm text-sm font-medium text-black hover:text-sky-500"
              :class="{ ' border-b-2 border-sky': forgeIsclick }"
            >
              Forge
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

import axios from "axios";
import store from "@/store";
import { ref } from "vue";
export default {
  components: {
    UserInfo,
    Equipments,
    Forge,
  },
  setup() {
    const personal = JSON.parse(localStorage.getItem("personallogin"));
    // axios.post("http://localhost:5050/api/personal",{建置後
    axios
      .post("http://localhost:8080/api/personal", {
        userId: personal.userId,
      })
      .then((res) => {
        store.state.userContent = res.data;
        // if (userContent.value.role == "Warrior") {
        //   roleimg.value = require("../assets/warrior.png");
        // } else if (userContent.value.role == "Ninja") {
        //   roleimg.value = require("../assets/ninja.png");
        // }
        // for (let i = 0; i < userContent.value.userEquipment.length; i++) {
        //   if (userContent.value.userEquipment[i].userUse == true) {
        //     currentEquip.value = userContent.value.userEquipment[i].equipmentName;
        //   }
        // }
      })
      .catch((e) => {
        alert(e.response.data.message);
      });
    // const currentEquip = ref();
    const infoIsclick = ref(true);

    const equipIsclick = ref(false);
    const forgeIsclick = ref(false);
    // const userUseEquipmentsid = ref();
    // const userContent = ref([]);
    // const switchArmor = () => {
    //   if (userContent.value.role == "Warrior") {
    //     roleimg.value = require("../assets/warrior.png");
    //   } else if (userContent.value.role == "Ninja") {
    //     roleimg.value = require("../assets/ninja.png");
    //   }
    // };
    // const deleteConfirm = () => {
    //   axios
    //     .post("api/Gameusers/deleteuserdata", {
    //       Equipmentid: deleteId.value,
    //     })
    //     .then(() => {
    //       showdelete.value = true;
    //       currentEquip.value = "";
    //       getUserinfo();
    //     })
    //     .catch(function (err) {
    //       alert(err);
    //     });
    // };

    // const Cancel = () => {
    //   showdedit.value = true;
    //   getUserinfo();
    // };
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
      // userUseEquipmentsid,

      // currentEquip,
      // switchArmor,
      showUserinfo,
      showEquip,
      showForge,
      // deleteConfirm,
      // Cancel,
    };
  },
};
</script>
