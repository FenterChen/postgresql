<template>
  <div
    class="fixed z-10 inset-0 overflow-y-auto"
    aria-labelledby="modal-title"
    role="dialog"
    aria-modal="true"
    :class="{ invisible: Open }"
  >
    <div
      class="flex items-end justify-center min-h-screen px-4 text-center sm:block sm:p-0"
    >
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
        aria-hidden="true"
      ></div>
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true"
        >&#8203;</span
      >
      <div
        class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
      >
        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
          <div class="sm:flex sm:items-start justify-center">
            <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
              <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                Successfully!
              </h3>
              <div class="m-4">
                <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                  <thead
                    class="text-l text-gray-700 bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
                  >
                    <tr>
                      <th scope="col" class="py-3 text-center">Id</th>
                      <th scope="col" class="py-3 text-center">Name</th>
                      <th scope="col" class="py-3 text-center">Type</th>
                      <th scope="col" class="px-6 py-3 text-center">Atk</th>
                      <th scope="col" class="px-6 py-3 text-center">Def</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
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
                        {{ item.equipmentName }}
                      </td>
                      <td
                        class="px-4 py-2 font-medium text-gray-900 dark:text-white text-center whitespace-nowrap border-l-2"
                      >
                        {{ item.equipmentType }}
                      </td>
                      <td
                        class="px-4 py-3 font-medium text-gray-900 dark:text-white whitespace-nowrap box-border flex justify-between items-center border-l-2"
                      >
                        +{{ item.equipmentAtk }}
                      </td>
                      <td class="px-4 py-2 text-center border-l-2">
                        +{{ item.equipmentDef }}
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
          <button
            type="button"
            class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm"
            @click="$emit('update')"
          >
            Confirm
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["Open", "item"],
  setup() {},
};
</script>
