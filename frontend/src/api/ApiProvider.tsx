import React, { useMemo } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import i18n from "../i18n";

export const BASE_URL = "http://localhost:8088/api/v1";

axios.defaults.baseURL = BASE_URL;

const ApiProvider = (props) =>
  useMemo(() => {
    axios.interceptors.request.use(function (config) {
      const token = localStorage.getItem("token");
      config.headers["Accept-Language"] = i18n.language;
      if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
      }
      return config;
    });

    axios.interceptors.response.use(
      function (response) {
        if (response.data.token) {
          localStorage.setItem("token", response.data.token);
        }
        return response;
      },
      function (error) {
        if (error?.response?.status === 400) {
          if (error.response.data.length > 0) {
            error.response.data.map((v) => toast.error(v.message));
          } else {
            toast.error("Bir hata meydana geldi! Daha sonra tekrar deneyin.");
          }
        } else if (error?.response?.status === 403) {
          toast.error("Bu işlem için yetkiniz bulunmamaktadır!");
        } else if (error?.response?.status === 500) {
          toast.error("Hay aksi! Sistem yöneticinize başvurun.");
        } else if (
          error?.response?.status === 503 ||
          error?.response?.status === 0
        ) {
          toast.error(
            "Sunucuya ulaşılamıyor! Lütfen daha sonra tekrar deneyin."
          );
        }
        return Promise.reject(error);
      }
    );

    return props.children;
  }, []);

export default ApiProvider;
