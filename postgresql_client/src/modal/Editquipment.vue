<template>
  <div
    class="fixed z-10 inset-0 overflow-y-auto"
    aria-labelledby="modal-title"
    role="dialog"
    aria-modal="true"
    :class="{ invisible: EditOpen }"
  >
    <div
      class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"
    >
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
        aria-hidden="true"
      />
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true"
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
                  class="block my-2 text-sm font-medium text-gray-700"
                  >equipmentName</label
                >
                <input
                  v-if="item"
                  v-model="item.equipmentName"
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
            @click="$emit('cancel')"
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
  </div>
</template>

<script>
import { computed } from "vue";
import axios from "axios";
import store from "@/store";

export default {
  props: ["EditOpen", "currentEquipment"],
  setup(props, target) {
    const item = computed({
      get: () => props.currentEquipment,
      set: (val) => {
        item.value = val;
      },
    });

    const editConfirm = () => {
      axios
        .put(`${process.env.VUE_APP_URL}equipment/equipmentName`, {
          equipmentId: item.value.equipmentId,
          equipmentName: item.value.equipmentName,
        })
        .then(() => {
          store.dispatch("refresh");
          target.emit("cancel");
        })
        .catch((err) => {
          alert(err);
        });
    };

    return {
      editConfirm,
      item,
    };
  },
};
</script>
