<template>
  <!-- <HomeSave :Open="rightOpen" @update="selfConfirm"></HomeSave> -->
  <div class="grid grid-cols-2">
    <div class="box-border grid grid-cols-2 place-items-center">
      <img class="p-2 box-border w-full" :src="roleimg" />
      <div class="grid grid-rows-2 h-full place-content-between">
        <div class="grid grid-cols-2 w-2/3 place-items-center">
          <img class="p-2 box-border" src="@/assets/sword.png" />
          <p class="box-border">{{ currentEquip }}</p>
        </div>
        <div class="grid grid-cols-2 w-2/3 place-items-center">
          <img class="p-2 box-border" src="@/assets/armor.png" />
          <p class="box-border grid">{{ currentEquip }}</p>
        </div>
      </div>
    </div>
    <div class="relative shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead
          class="text-l text-gray-700 bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">Equipments name</th>
            <th scope="col" class="px-6 py-3 border-l-2">Operate</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="item in user.userEquipment"
            :key="item.equipmentId"
            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
          >
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 dark:text-white whitespace-nowrap flex justify-between items-center"
            >
              {{ item.equipmentName }}
              <button
                @click="useEquipment(item)"
                class="px-4 py-2 mx-4 font-bold bg-regal-blue text-white rounded-full"
              >
                Use
              </button>
            </th>
            <td class="px-6 py-4 text-right border-l-2">
              <button
                @click="editCheck(item)"
                class="my-2 md:my-0 px-4 py-2 mx-2 font-bold bg-cyan text-white rounded-full"
              >
                Edit
              </button>
              <button
                @click="deleteCheck(item.equipmentid)"
                class="px-4 py-2 mx-2 font-bold bg-red-500 text-white rounded-full"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
// import router from "@/router";
import store from "@/store";
// import HomeSave from "@/components/Homesave";
import axios from "axios";
import { ref, computed } from "vue";

export default {
  // components: {
  //   // HomeSave,
  // },
  setup() {
    const user = computed(() => {
      if (store.state.userContent.role == "Warrior") {
        roleimg.value = require("@/assets/warrior.png");
      } else if (store.state.userContent.role == "Ninja") {
        roleimg.value = require("@/assets/ninja.png");
      }
      return store.getters.doneContent;
    });

    const currentEquip = ref();
    const editId = ref();
    const showdedit = ref(true);
    const showdelete = ref(true);
    const deleteId = ref();
    const roleimg = ref();
    const editConfirm = () => {
      axios
        .post("api/Gameusers/edituserdata", {
          Equipmentid: editId.value.equipmentid,
          Equipment: editId.value.equipment,
        })
        .then(() => {
          showdedit.value = true;
        })
        .catch(function (err) {
          alert(err);
        });
    };

    const useEquipment = (item) => {
      // axios.post("http://localhost:5050/api/useEquipment",{建置後

      axios
        .put("http://localhost:8080/api/useEquipment", {
          id: user.value.id,
          equipmentId: item.equipmentId,
          userUse: true,
        })
        .then(() => {})
        .catch(function (err) {
          alert(err);
        });
    };

    const editCheck = (e) => {
      showdedit.value = false;
      editId.value = e;
    };

    const deleteCheck = (e) => {
      showdelete.value = false;
      deleteId.value = e;
    };

    return {
      deleteId,
      editId,
      user,
      showdedit,
      showdelete,
      roleimg,
      currentEquip,
      useEquipment,
      editConfirm,
      editCheck,
      deleteCheck,
    };
  },
};
</script>
