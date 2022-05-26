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
    refresh(state) {
      axios
      .post("http://localhost:8080/api/personal", {
        userId: state.userContent.userId,
      })
      .then((res) => {
        state.userContent = res.data;
      })
      .catch((e) => {
        alert(e.response.data.message);
      });
    },
  },
  actions: {},
  getters: {
     doneContent: state => {
      return state.userContent
    }
  },
});