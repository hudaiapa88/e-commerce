import { createTheme } from "@mui/material/styles";

export const themeOptions = createTheme({
  palette: {
    primary: {
      main: "#2a59fe",
    },
    secondary: {
      main: "#ffffff",
    },
    background: {
      default: "#f9f9f9",
    },
  },
  typography: {
    fontFamily: "Montserrat",
  },
});
