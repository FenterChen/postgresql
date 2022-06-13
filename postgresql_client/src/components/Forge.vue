<template>
  <div class="relative grid grid-row-2 shadow-md sm:rounded-lg">
    <ShowNewEquipment
      :Open="rightOpen"
      :item="showNewItem"
      @update="selfConfirm"
    ></ShowNewEquipment>
    <h1 class="text-center text-xl">Click the hammer to forging</h1>
    <div class="grid grid-cols-2">
      <div class="m-2 p-6 grid grid-rows-2 place-items-center">
        <div class="col-span-6 sm:col-span-3">
          <label for="Type" class="block text-sm font-medium text-gray-700"
            >Equipment Type</label
          >
          <select
            @change="chooseType"
            id="Type"
            name="Type"
            class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          >
            <option value="1">Weapon</option>
            <option value="2">Armor</option>
          </select>
        </div>
        <div class="col-span-6 sm:col-span-4">
          <label for="EquipmentName" class="block text-sm font-medium text-gray-700"
            >Equipment Name</label
          >
          <input
            type="text"
            v-model="newEquip.name"
            placeholder="No Name"
            name="EquipmentName"
            id="EquipmentName"
            class="mt-1 py-2 px-3 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
          />
        </div>
      </div>
      <div class="my-4 grid grid-rows-2 place-items-center">
        <div class="relative right-10 cursor-pointer">
          <img src="../assets/hammer.svg" class="hammer" @click="Add" />
        </div>
        <div class="table">
          <img src="../assets/table.svg" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { ref, reactive, computed, onMounted } from "vue";
import { gsap } from "gsap";
import axios from "axios";
import ShowNewEquipment from "@/modal/ShowNewEquipment";

export default {
  components: {
    ShowNewEquipment,
  },
  setup() {
    const rightOpen = ref(true);
    const showNewItem = ref([]);

    const newEquip = reactive({
      userid: null,
      type: 1,
      name: null,
    });
    const equipmentTypeList = ref([
      { id: 1, typename: "Weapon" },
      { id: 2, typename: "Armor" },
    ]);

    const user = computed(() => {
      return store.state.userContent;
    });

    const hammar = () => {
      gsap.to(".hammer", {
        rotation: 20,
        y: 20,
        x: 8,
        duration: 0.25,
        repeat: 7,
        yoyo: true,
      });
    };
    onMounted(() => {
      newEquip.userid = user.value.id;
    });
    const Add = () => {
      hammar();

      let name;
      if (newEquip.name == null) {
        name = "No Name";
      } else {
        name = newEquip.name;
      }
      setTimeout(() => {
        axios
          .post(`${process.env.VUE_APP_URL}equipment/addEquipment`, {
            userId: newEquip.userid,
            equipmentType: newEquip.type,
            equipmentName: name,
          })
          .then((res) => {
            showNewItem.value = res.data;
            rightOpen.value = false;
            store.dispatch("refresh");
          })
          .catch(function (err) {
            alert(err);
          });
      }, 2000);
    };
    const chooseType = () => {
      let type = parseInt(document.getElementById("Type").value);
      newEquip.type = type;
    };
    const selfConfirm = () => {
      rightOpen.value = true;
    };
    return {
      user,
      newEquip,
      equipmentTypeList,
      rightOpen,
      showNewItem,
      Add,
      hammar,
      chooseType,
      selfConfirm,
    };
  },
};
</script>
