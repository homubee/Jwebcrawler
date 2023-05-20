import axios, { AxiosError } from "axios";

const axiosSetting = {
  baseURL: process.env.REACT_APP_BASE_URL,
  withCredentials: true
};

const apiInstance = axios.create(axiosSetting);

apiInstance.interceptors.request.use(
  function (config) {
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
  function (err: AxiosError) {
    console.error(err);
    alert(err);
    return Promise.reject(err);
  }
);

export {apiInstance};