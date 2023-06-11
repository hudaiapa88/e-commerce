import React, { useLayoutEffect } from "react";
import { BrowserRouter, Route, Routes, useLocation } from "react-router-dom";
import Products from "../pages/Products";
import ProductDetail from "../pages/ProductDetail";
import MainTemplate from "./templates/MainTemplate";
import Register from "../pages/Register";
import Login from "../pages/Login";
import PrivateRouter from "./PrivateRouter";
import ProductsByAdmin from "../pages/Admin/Products";
import Orders from "../pages/Admin/Orders";
import Users from "../pages/Admin/Users";
import Checkout from "../pages/User/Checkout";

const Router = () => {
  const Wrapper = ({ children }) => {
    const location = useLocation();
    useLayoutEffect(() => {
      document.documentElement.scrollTo(0, 0);
    }, [location.pathname]);
    return children;
  };
  return (
    <BrowserRouter>
      <MainTemplate>
        <Wrapper>
          <Routes>
            <Route
              path="/"
              element={<PrivateRouter role={["ADMIN", "USER"]} />}
            >
              <Route index element={<Products />} />
              <Route path="login" element={<Login />} />
              <Route path="register" element={<Register />} />
              <Route path="product/:productId" element={<ProductDetail />} />
            </Route>
            <Route path="/admin" element={<PrivateRouter role={["ADMIN"]} />}>
              <Route index element={<ProductsByAdmin />} />
              <Route path="orders" element={<Orders />} />
              <Route path="users" element={<Users />} />
            </Route>
            <Route path="/user" element={<PrivateRouter role={["USER"]} />}>
              <Route path="checkout" element={<Checkout />} />
            </Route>
          </Routes>
        </Wrapper>
      </MainTemplate>
    </BrowserRouter>
  );
};

export default Router;
