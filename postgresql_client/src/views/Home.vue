<template>
  <div class="max-w-8xl m-auto min-h-screen flex items-center">
    <div
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
              @click="deleteCancel"
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
              @click="editCancel"
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
    <HomeSave :Open="rightOpen" @update="selfConfirm"></HomeSave>
    <div class="md:grid md:grid-cols-3 md:gap-6 items-center">
      <div class="p-4 box-border flex flex-col items-center">
        <img class="p-2 box-border w-4/5" :src="roleimg" />
        <div class="flex justify-around">
          <img class="p-2 box-border w-1/5" src="../assets/armor.png" />
          <p class="box-border flex items-center w-2/5">{{ currentEquip }}</p>
        </div>
      </div>
      <div class="mt-5 md:mt-0 md:col-span-2">
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
          </div>
          <div v-if="infoIsclick" class="px-4 py-5 bg-white sm:p-6">
            <div v-if="userContent" class="grid grid-cols-6 gap-6">
              <div class="col-span-6 sm:col-span-3">
                <p class="text-gray-700">
                  Id:<span class="px-2">{{ userContent.id }}</span>
                </p>
              </div>

              <div class="col-span-6 sm:col-span-3">
                <p class="text-gray-700">
                  Userid:<span class="px-2">{{ userContent.userId }}</span>
                </p>
              </div>
              <div class="col-span-6 sm:col-span-4">
                <label for="Username" class="block text-sm font-medium text-gray-700"
                  >Username</label
                >
                <input
                  v-model="userContent.userName"
                  type="text"
                  name="Username"
                  id="Username"
                  class="mt-1 py-2 px-3 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                />
              </div>
              <div class="col-span-6 sm:col-span-3">
                <label for="Role" class="block text-sm font-medium text-gray-700"
                  >Role</label
                >
                <select
                  @change="switchRole"
                  v-model="userContent.role"
                  id="Role"
                  name="Role"
                  class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                >
                  <option>Warrior</option>
                  <option>Ninja</option>
                </select>
              </div>
            </div>
          </div>
          <div v-if="equipIsclick" class="px-4 py-5 bg-white sm:p-6">
            <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
              <div class="mx-2 my-5">
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
                    class="px-6 py-2 w-1/4 bg-gray-400 border-2 border-gray-500 text-white font-bold text-l leading-tight rounded hover:bg-gray hover:bg-gray-600"
                    type="button"
                    id="button-addon3"
                  >
                    Add Equipment
                  </button>
                </div>
              </div>
              <div class="flex justify-center"></div>
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
                    v-for="item in userContent.userEquipment"
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
                        class="px-4 py-2 mx-1 font-bold bg-regal-blue text-white rounded-full"
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
          <div
            class="px-4 py-3 bg-gray-50 flex justify-between sm:px-6"
            v-if="infoIsclick"
          >
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
      </div>
    </div>
  </div>
</template>
<script>
import HomeSave from "@/components/Homesave";
import { ref, onMounted } from "vue";
import axios from "axios";
import router from "../router";
export default {
  components: {
    HomeSave,
  },
  setup() {
    const roleimg = ref();
    const newEquip = ref();
    const deleteId = ref();
    const editId = ref();
    const currentEquip = ref();
    const infoIsclick = ref(true);
    const showdelete = ref(true);
    const showdedit = ref(true);
    const equipIsclick = ref(false);
    const userUseEquipmentsid = ref();
    const userContent = ref([]);
    const rightOpen = ref(true);
    onMounted(() => {
      getUserinfo();
    });
    const getUserinfo = () => {
      let personal = JSON.parse(localStorage.getItem("personallogin"));
      // axios.post("http://localhost:5050/api/personal",{建置後
      axios
        .post("http://localhost:8080/api/personal", {
          userId: personal.userId,
        })
        .then((res) => {
          userContent.value = res.data;
          if (userContent.value.role == "Warrior") {
            roleimg.value = require("../assets/warrior.png");
          } else if (userContent.value.role == "Ninja") {
            roleimg.value = require("../assets/ninja.png");
          }
          for (let i = 0; i < userContent.value.userEquipment.length; i++) {
            if (userContent.value.userEquipment[i].userUse == true) {
              currentEquip.value = userContent.value.userEquipment[i].equipmentName;
            }
          }
        })
        .catch((e) => {
          alert(e.response.data.message);
        });
    };
    const switchRole = () => {
      if (userContent.value.role == "Warrior") {
        roleimg.value = require("../assets/warrior.png");
      } else if (userContent.value.role == "Ninja") {
        roleimg.value = require("../assets/ninja.png");
      }
    };
    const switchArmor = () => {
      if (userContent.value.role == "Warrior") {
        roleimg.value = require("../assets/warrior.png");
      } else if (userContent.value.role == "Ninja") {
        roleimg.value = require("../assets/ninja.png");
      }
    };
    const save = () => {
      axios
        // .post("http://localhost:5050/saveuser",{建置後
        .put("http://localhost:8080/api/saver", {
          userId: userContent.value.userId,
          userName: userContent.value.userName,
          role: userContent.value.role,
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
      router.push({ name: "Login" });
    };
    const selfConfirm = () => {
      rightOpen.value = true;
    };
    const showUserinfo = () => {
      infoIsclick.value = true;
      equipIsclick.value = false;
    };
    const showEquip = () => {
      equipIsclick.value = true;
      infoIsclick.value = false;
    };
    const deleteCheck = (e) => {
      showdelete.value = false;
      deleteId.value = e;
    };
    const editCheck = (e) => {
      showdedit.value = false;
      editId.value = e;
    };
    const deleteConfirm = () => {
      axios
        .post("api/Gameusers/deleteuserdata", {
          Equipmentid: deleteId.value,
        })
        .then(() => {
          showdelete.value = true;
          currentEquip.value = "";
          getUserinfo();
        })
        .catch(function (err) {
          alert(err);
        });
    };
    const deleteCancel = () => {
      showdelete.value = true;
      getUserinfo();
    };
    const Add = () => {
      axios
        .post("api/Gameusers/adduserdata", {
          Userid: userContent.value.userid,
          Equipment: newEquip.value,
        })
        .then(() => {
          getUserinfo();
          newEquip.value = "";
        })
        .catch(function (err) {
          alert(err);
        });
    };
    const editConfirm = () => {
      axios
        .post("api/Gameusers/edituserdata", {
          Equipmentid: editId.value.equipmentid,
          Equipment: editId.value.equipment,
        })
        .then(() => {
          showdedit.value = true;
          getUserinfo();
        })
        .catch(function (err) {
          alert(err);
        });
    };
    const editCancel = () => {
      showdedit.value = true;
      getUserinfo();
    };
    const useEquipment = (item) => {
      // axios.post("http://localhost:5050/api/useEquipment",{建置後

      axios
        .put("http://localhost:8080/api/useEquipment", {
          id: userContent.value.id,
          equipmentId: item.equipmentId,
          userUse: true,
        })
        .then(() => {
          getUserinfo();
        })
        .catch(function (err) {
          alert(err);
        });
    };

    return {
      showdelete,
      newEquip,
      editId,
      deleteId,
      userContent,
      infoIsclick,
      equipIsclick,
      userUseEquipmentsid,
      roleimg,
      rightOpen,
      showdedit,
      currentEquip,
      getUserinfo,
      switchRole,
      save,
      editCheck,
      selfConfirm,
      logout,
      switchArmor,
      showUserinfo,
      showEquip,
      deleteCheck,
      deleteConfirm,
      deleteCancel,
      Add,
      editCancel,
      editConfirm,
      useEquipment,
    };
  },
};
</script>
