import { createStore } from "vuex";

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
  },
  actions: {},
  getters: {
     doneContent: state => {
      return state.userContent
    }
  },
});