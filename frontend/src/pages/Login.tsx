import { Button, Stack, TextField, Typography } from "@mui/material";
import React, { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { LoginForm } from "../types/Account";
import { loginRequest } from "../api/controllers/account";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const [form, setForm] = useState<LoginForm>({
    password: "",
    rememberMe: true,
    username: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await loginRequest(form).then((res) => {
      if (res) {
        localStorage.setItem("token", res.data.token);
        if (res.data.role === "ADMIN") {
          navigate("/admin");
          window.location.reload();
        } else if (res.data.role === "USER") {
          navigate("/");
        }
      }
    });
  };

  useEffect(() => {
    if (localStorage.getItem("token")) {
      navigate("/");
    }
  }, []);

  return (
    <Stack spacing={4} maxWidth={400} margin="auto">
      <Typography variant="h4" textAlign="center">
        {t("Login")}
      </Typography>
      <Stack component="form" onSubmit={handleSubmit} spacing={2}>
        <TextField
          label={t("Username")}
          value={form.username}
          onChange={(e) =>
            setForm((prev) => ({ ...prev, username: e.target.value }))
          }
          required
        />
        <TextField
          label={t("Password")}
          value={form.password}
          onChange={(e) =>
            setForm((prev) => ({ ...prev, password: e.target.value }))
          }
          required
          type="password"
        />
        <Button type="submit" variant="contained">
          {t("Login")}
        </Button>
        <Link to="/register">
          <Button type="button" fullWidth>
            {t("Signup")}
          </Button>
        </Link>
      </Stack>
    </Stack>
  );
}

export default Login;
