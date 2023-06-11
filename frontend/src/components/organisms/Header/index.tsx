import {
  Check,
  LogoutRounded,
  Search,
  TranslateRounded,
} from "@mui/icons-material";
import {
  AppBar,
  Container,
  Grid,
  IconButton,
  InputAdornment,
  ListItemText,
  MenuItem,
  MenuList,
  Popover,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import React from "react";
import { LocalMallOutlined, PersonOutlineOutlined } from "@mui/icons-material";
import { Link, useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../../redux/store";
import { changeSearch } from "../../../redux/slices/filter";
import { useAuth } from "../../../context/Auth";
import { logoutRequest } from "../../../api/controllers/account";
import { useTranslation } from "react-i18next";
import i18n from "../../../i18n";

function Header() {
  const { t } = useTranslation();
  const { currentUser } = useAuth();
  const cart = useAppSelector((state) => {
    return state.cart.items;
  });
  const dispatch = useAppDispatch();
  const total = cart.reduce((a, b) => Number(a) + Number(b.price), 0);

  const handleLogout = () => {
    localStorage.removeItem("token");
    logoutRequest();
    window.location.reload();
  };

  const [anchorEl, setAnchorEl] = React.useState<HTMLButtonElement | null>(
    null
  );

  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLangChange = (lang) => {
    localStorage.setItem("lang", lang);
    i18n.changeLanguage(lang);
  };

  const open = Boolean(anchorEl);
  const id = open ? "simple-popover" : undefined;

  return (
    <AppBar position="relative" elevation={0} sx={{ py: 1 }}>
      <Container
        maxWidth="xl"
        sx={{ height: "100%", display: "flex", alignItems: "center" }}
      >
        <Grid
          container
          spacing={2}
          direction={{ xs: "column", sm: "row" }}
          alignItems={{ xs: "center", sm: "flex-start" }}
        >
          <Grid item xs={4} sm={4} md={3} xl={2}>
            <Typography
              component={Link}
              to="/"
              sx={{
                color: "inherit",
                textDecoration: "none",
                fontSize: 24,
                fontWeight: 800,
              }}
            >
              E-Commerce
            </Typography>
          </Grid>
          <Grid item xs={8} sm={8} md={9} xl={10}>
            <Stack
              direction={{ xs: "column-reverse", sm: "row" }}
              spacing={2}
              justifyContent="space-between"
              alignItems="center"
            >
              <TextField
                variant="outlined"
                placeholder={t("Search")}
                onChange={(e) => dispatch(changeSearch(e.target.value))}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">
                      <Search />
                    </InputAdornment>
                  ),
                  sx: (theme) => ({
                    backgroundColor: theme.palette.background.paper,
                  }),
                }}
                size="small"
              />
              <Stack direction="row" spacing={2} alignItems="center">
                <IconButton
                  color="inherit"
                  aria-describedby={id}
                  onClick={handleClick}
                >
                  <TranslateRounded />
                </IconButton>
                <Popover
                  id={id}
                  open={open}
                  anchorEl={anchorEl}
                  onClose={handleClose}
                  anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                  }}
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                >
                  <MenuList dense>
                    <MenuItem onClick={() => handleLangChange("tr")}>
                      <ListItemText
                        sx={(theme) => ({
                          fontWeight: 500,
                          color:
                            i18n.language === "tr"
                              ? theme.palette.primary.main
                              : theme.palette.text.primary,
                        })}
                      >
                        TR
                      </ListItemText>
                    </MenuItem>
                    <MenuItem onClick={() => handleLangChange("en")}>
                      <ListItemText
                        sx={(theme) => ({
                          fontWeight: 500,
                          color:
                            i18n.language === "en"
                              ? theme.palette.primary.main
                              : theme.palette.text.primary,
                        })}
                      >
                        EN
                      </ListItemText>
                    </MenuItem>
                  </MenuList>
                </Popover>
                {currentUser?.role !== "ADMIN" && (
                  <Stack direction="row" spacing={1}>
                    <LocalMallOutlined />
                    <Typography>
                      {new Intl.NumberFormat("tr-TR", {
                        style: "currency",
                        currency: "TRY",
                      }).format(total || 0)}
                    </Typography>
                  </Stack>
                )}
                <Stack direction="row" spacing={1}>
                  <PersonOutlineOutlined />
                  <Typography
                    component={Link}
                    sx={{ color: "inherit", textDecoration: "none" }}
                    to={
                      currentUser
                        ? currentUser.role === "ADMIN"
                          ? "/admin"
                          : "/user"
                        : "/login"
                    }
                  >
                    {currentUser ? currentUser.firstName : t("Login")}
                  </Typography>
                </Stack>
                {currentUser && (
                  <IconButton
                    title={t("Logout")}
                    color="inherit"
                    onClick={handleLogout}
                  >
                    <LogoutRounded color="inherit" />
                  </IconButton>
                )}
              </Stack>
            </Stack>
          </Grid>
        </Grid>
      </Container>
    </AppBar>
  );
}

export default Header;
