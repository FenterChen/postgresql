<template>
  <Delequipment
    :DelOpen="showdelete"
    @cancel="Cancel"
    @confirm="deleteConfirm"
  ></Delequipment>
  <Editquipment
    :EditOpen="showdedit"
    :currentEquipment="currentItem"
    @cancel="Cancel"
  ></Editquipment>
  <div class="grid grid-cols-2">
    <div class="box-border grid grid-cols-2 place-items-center">
      <img class="p-2 box-border w-full" :src="roleimg" />
      <div class="grid grid-rows-2 h-full place-content-between">
        <div class="grid grid-cols-2 place-items-center pr-4 box-border">
          <div class="w-2/3">
            <img class="box-border w-full" src="@/assets/sword.png" />
          </div>
          <div class="grid grid-rows-2 w-full" v-if="user.weaponSlot">
            <p class="box-border text-left py-2 truncate pr-4">
              {{ user.weaponSlot.equipmentName }}
            </p>
            <p class="text-left">攻+{{ user.weaponSlot.equipmentAtk }}</p>
          </div>
        </div>
        <div class="grid grid-cols-2 place-items-center pr-4 box-border">
          <div class="p-4">
            <img class="box-border w-full" src="@/assets/armor.png" />
          </div>
          <div class="grid grid-rows-2 w-full" v-if="user.armorSlot">
            <p class="box-border text-left py-2 truncate pr-4">
              {{ user.armorSlot.equipmentName }}
            </p>
            <p class="text-left">防+{{ user.armorSlot.equipmentDef }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="relative shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead
          class="text-l text-gray-700 bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="py-3 text-center">Id</th>
            <th scope="col" class="py-3 text-center">Type</th>
            <th scope="col" class="py-3 text-center">Ability</th>
            <th scope="col" class="px-6 py-3 text-center">Name</th>
            <th scope="col" class="px-6 py-3 text-center">Operate</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="item in user.userEquipment"
            :key="item.equipmentId"
            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
          >
            <td
              class="px-4 py-2 font-medium text-gray-900 dark:text-white text-center whitespace-nowrap"
            >
              {{ item.equipmentId }}
            </td>
            <td
              class="px-4 py-2 font-medium text-gray-900 dark:text-white text-center whitespace-nowrap border-l-2"
            >
              {{ item.equipmentType }}
            </td>
            <td
              class="px-4 py-2 font-medium text-gray-900 dark:text-white text-center whitespace-nowrap border-l-2"
            >
              <div class="grid grid-rows-2">
                <p>Atk +{{ item.equipmentAtk }}</p>
                <p>Def +{{ item.equipmentDef }}</p>
              </div>
            </td>
            <td
              class="px-4 py-3 font-medium text-gray-900 dark:text-white box-border min-h-full border-l-2"
            >
              <div class="w-14">{{ item.equipmentName }}</div>
            </td>
            <td class="px-4 py-2 text-center border-l-2">
              <div class="grid grid-cols-3">
                <button
                  @click="useEquipment(item)"
                  class="px-4 py-2 mx-4 font-bold bg-regal-blue text-white rounded-full"
                  v-if="
                    item.equipmentId != user.weaponSlot.equipmentId &&
                    item.equipmentId != user.armorSlot.equipmentId
                  "
                >
                  Use
                </button>
                <button
                  @click="useEquipment(item)"
                  class="px-4 py-2 mx-4 font-bold bg-gray-400 text-white rounded-full cursor-not-allowed"
                  v-if="
                    item.equipmentId == user.weaponSlot.equipmentId ||
                    item.equipmentId == user.armorSlot.equipmentId
                  "
                >
                  Used
                </button>
                <button
                  @click="editCheck(item)"
                  class="my-2 md:my-0 px-4 py-2 mx-2 font-bold bg-cyan text-white rounded-full"
                >
                  Edit
                </button>
                <button
                  @click="deleteCheck(item.equipmentId)"
                  class="px-4 py-2 mx-2 font-bold bg-red-500 text-white rounded-full"
                >
                  Delete
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import store from "@/store";
import Delequipment from "@/modal/Delequipment";
import Editquipment from "@/modal/Editquipment";
import axios from "axios";
import { ref, computed, onMounted } from "vue";

export default {
  components: {
    Delequipment,
    Editquipment,
  },
  setup() {
    const user = computed(() => {
      const res = store.state.userContent;
      for (let index = 0; index < store.state.userContent.userEquipment.length; index++) {
        if (store.state.userContent.userEquipment[index].equipmentType == 1) {
          res.userEquipment[index].equipmentType = "Weapon";
        } else if (store.state.userContent.userEquipment[index].equipmentType == 2) {
          res.userEquipment[index].equipmentType = "Armor";
        }
      }
      if (store.state.userContent.weaponSlot == null) {
        res.weaponSlot = 0;
      }
      if (store.state.userContent.armorSlot == null) {
        res.armorSlot = 0;
      }

      return res;
    });

    onMounted(() => {
      if (user.value.role == "Warrior") {
        roleimg.value = require("@/assets/warrior.png");
      } else if (user.value.role == "Ninja") {
        roleimg.value = require("@/assets/ninja.png");
      } else {
        roleimg.value = require("@/assets/defaultRole.png");
      }
    });

    const currentItem = ref([]);
    const editId = ref();
    const showdedit = ref(true);
    const showdelete = ref(true);
    const deleteId = ref();
    const roleimg = ref();
    const useOrNot = ref(true);

    const useEquipment = (item) => {
      var weaponSlotId = null;
      var armorSlotId = null;

      if (user.value.weaponSlot) {
        weaponSlotId = user.value.weaponSlot.equipmentId;
      } else if (user.value.armorSlot) {
        armorSlotId = user.value.armorSlot.equipmentId;
      }
      if (user.value.weaponSlot && user.value.armorSlot) {
        weaponSlotId = user.value.weaponSlot.equipmentId;
        armorSlotId = user.value.armorSlot.equipmentId;
      }

      if (item.equipmentType == "Armor") {
        armorSlotId = item.equipmentId;
      } else if (item.equipmentType == "Weapon") {
        weaponSlotId = item.equipmentId;
      }
      axios
        .put(`${process.env.VUE_APP_URL}equipment/useEquipment`, {
          id: user.value.id,
          weaponSlot: weaponSlotId,
          armorSlot: armorSlotId,
        })
        .then(() => {
          store.dispatch("refresh");
        })
        .catch((e) => {
          alert(e.response.data.message);
        });
    };

    const deleteConfirm = () => {
      const equipmentId= deleteId.value;
      axios
        .delete(`${process.env.VUE_APP_URL}equipment/${equipmentId}`)
        .then((res) => {
          if (res.data == 1) {
            store.dispatch("refresh");
            showdelete.value = true;
          } else if (res.data == 0) {
            alert("沒有此裝備，無法刪除");
          }
        });
    };

    const editCheck = (e) => {
      showdedit.value = false;
      currentItem.value = e;
    };

    const deleteCheck = (e) => {
      showdelete.value = false;
      deleteId.value = e;
    };

    const Cancel = () => {
      store.dispatch("refresh");
      showdelete.value = true;
      showdedit.value = true;
    };

    return {
      currentItem,
      deleteId,
      editId,
      user,
      showdedit,
      showdelete,
      roleimg,
      useOrNot,
      useEquipment,
      editCheck,
      deleteCheck,
      Cancel,
      deleteConfirm,
    };
  },
};
</script>
