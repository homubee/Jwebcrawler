import { createSlice } from "@reduxjs/toolkit";
import { MemberInfo } from "../type/common";

const initialState: MemberInfo = {
  id: 0,
  roleList: [],
  email: "",
  nickname: "",
  accessToken: "",
}

export const authSlice = createSlice({
  name: "auth",
  initialState: initialState,
  reducers: {
    login: (state, action) => {
      console.log(action);
      state.id = action.payload.id;
      state.accessToken = action.payload.accessToken;
      console.log(state);
    },
    setInfo: (state, action) => {
      state.roleList = action.payload.roleList;
      state.email = action.payload.email;
      state.nickname = action.payload.nickname;
    },
    logout: (state) => {
      state.id = 0;
      state.roleList = [];
      state.email = "";
      state.nickname = "";
      state.accessToken = "";
    },
    refresh: (state, action) => {
      state.accessToken = action.payload.accessToken;
    },
  }
});

export const { login, setInfo, logout, refresh } = authSlice.actions;