import axios, { AxiosError } from "axios";
import { store } from "../store/store";
import { logout, refresh } from "../store/authSlice";

const axiosSetting = {
  baseURL: process.env.REACT_APP_BASE_URL,
  withCredentials: true
};

const apiInstance = axios.create(axiosSetting);

apiInstance.interceptors.request.use(
  function (config) {
    const memberInfo = store.getState().auth;
    if (memberInfo.accessToken) {
      config.headers.Authorization = `Bearer ${memberInfo.accessToken}`;
    }
    return config;
  },
  function (err: AxiosError) {
    console.error(err);
    alert(err);
    return Promise.reject(err);
  }
);

apiInstance.interceptors.response.use(
  function (res) {
    console.log(res);
    return res;
  },
  async function (err: AxiosError) {
    const config = err.config!;
    if (err.response?.status === 401) {
      const memberInfo = store.getState().auth;
      if (memberInfo.accessToken) {
        await axios.post("/auth/refresh", null, axiosSetting)
        .then((res) => {
          store.dispatch(refresh({accessToken: res.data.accessToken}));
        }).catch((err) => {
          console.error(err);
          store.dispatch(logout());
        });
        const newMemberInfo = store.getState().auth;
        config.headers.Authorization = `Bearer ${newMemberInfo.accessToken}`;
        return apiInstance(config);
      }
    }
    console.error(err);
    alert(err);
    return Promise.reject(err);
  }
);

export {apiInstance};