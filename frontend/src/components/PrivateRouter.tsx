import React, { Fragment, useState, useEffect } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { toast } from "react-toastify";
import { useAuth } from "../context/Auth";
import { getCurrentUserRequest } from "../api/controllers/account";
import AdminTemplate from "./templates/AdminTemplate";
import { useTranslation } from "react-i18next";

const PrivateRouter = ({ role }: { role: Array<"ADMIN" | "USER"> }) => {
  const { t } = useTranslation();
  const [AUTH_STATUS, setAUTH_STATUS] = useState("WAITING");
  const { setCurrentUser } = useAuth();

  const checkAuthentication = async () => {
    try {
      let res = await getCurrentUserRequest({ hideMessage: false });
      if (res && res.status === 200) {
        setCurrentUser(res.data);
        setAUTH_STATUS("SUCCESS");
      }
    } catch (error) {
      if (error?.response?.status === 401) {
        toast.warn("Oturumunuz kapatıldı! Lütfen yeniden giriş yapın.");
        setAUTH_STATUS("FAILED");
        setCurrentUser(null);
      }
    }
  };

  useEffect(() => {
    if (localStorage.getItem("token")) {
      checkAuthentication();
    }
  }, []);

  return (
    <Fragment>
      {role.length > 1 ? (
        <Outlet />
      ) : AUTH_STATUS === "WAITING" ? (
        <div>{t("Loading")}</div>
      ) : AUTH_STATUS === "FAILED" ? (
        <Navigate to="/" />
      ) : AUTH_STATUS === "SUCCESS" ? (
        role.includes("ADMIN") ? (
          <AdminTemplate>
            <Outlet />
          </AdminTemplate>
        ) : (
          <Outlet />
        )
      ) : (
        <div>{t("Error")}</div>
      )}
    </Fragment>
  );
};

export default PrivateRouter;
