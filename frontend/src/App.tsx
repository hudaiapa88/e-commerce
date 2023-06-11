import { ThemeProvider } from "@mui/material";
import React from "react";
import "./styles/reset.css";
import { themeOptions } from "./styles/mui_theme";
import Router from "./components/Router";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import Contexts from "./context";

function App() {
  return (
    <ThemeProvider theme={themeOptions}>
      <Contexts>
        <Router />
      </Contexts>
      <ToastContainer />
    </ThemeProvider>
  );
}

export default App;
