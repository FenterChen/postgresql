import { createStore } from "vuex";
import axios from "axios";

export default createStore({
  state() {
    return {
      userContent: [],
    };
  },
  mutations: {
    cleanUser(state) {
      state.userContent = null;
    },
    MuUserContent(state,res) {
      state.userContent = res.data
    },
  },
  actions: {
    refresh(context){
      const personal = JSON.parse(localStorage.getItem("personallogin"));
      let userId= personal.userId;
      axios
      .get(`${process.env.VUE_APP_URL}user/${userId}`)
      .then((res) => {
        context.commit('MuUserContent',res)
      })
      .catch((e) => {
        alert(e.response.data.message);
      });
    }
  },
  getters: {},
});