import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider } from "react-redux";
import store from "./redux/store";
import "./i18n";
import ApiProvider from "./api/ApiProvider";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <ApiProvider>
    <Provider store={store}>
      <App />
    </Provider>
  </ApiProvider>
);
