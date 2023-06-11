import { Button, Stack, TextField, Typography } from "@mui/material";
import React, { useState, useEffect } from "react";
import { RegisterForm } from "../types/Account";
import { useTranslation } from "react-i18next";
import { Link, useNavigate } from "react-router-dom";
import { createUserRequest } from "../api/controllers/user";
import { toast } from "react-toastify";

function Register() {
  const { t } = useTranslation();
  const navigate = useNavigate();

  const [form, setForm] = useState<RegisterForm>({
    address: {
      buildingInformation: "",
      country: "",
      district: "",
      fullAddress: "",
      neighborhood: "",
      province: "",
      street: "",
    },
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    phone: {
      areaCode: "90",
      countryCode: "",
      number: "",
    },
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createUserRequest(form).then((res) => {
      if (res) {
        navigate("/login");
        toast.success(t("Kayıt işlemi başarılı"));
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
        {t("Üye Ol")}
      </Typography>
      <Stack component="form" onSubmit={handleSubmit} spacing={2}>
        <TextField
          label={t("First Name")}
          value={form.firstName}
          onChange={(e) =>
            setForm((prev) => ({ ...prev, firstName: e.target.value }))
          }
          required
        />
        <TextField
          label={t("Last Name")}
          value={form.lastName}
          onChange={(e) =>
            setForm((prev) => ({ ...prev, lastName: e.target.value }))
          }
          required
        />
        <TextField
          label={t("E-Mail")}
          value={form.email}
          onChange={(e) =>
            setForm((prev) => ({ ...prev, email: e.target.value }))
          }
          required
          type="email"
        />
        <TextField
          label={t("Phone Number")}
          value={form.phone.number}
          onChange={(e) =>
            setForm((prev) => ({
              ...prev,
              phone: { ...prev.phone, number: e.target.value },
            }))
          }
          required
        />
        <TextField
          label={t("Address")}
          value={form.address.fullAddress}
          onChange={(e) =>
            setForm((prev) => ({
              ...prev,
              address: { ...prev.address, fullAddress: e.target.value },
            }))
          }
          required
        />
        <TextField
          label={t("District")}
          value={form.address.district}
          onChange={(e) =>
            setForm((prev) => ({
              ...prev,
              address: { ...prev.address, district: e.target.value },
            }))
          }
          required
        />
        <TextField
          label={t("Province")}
          value={form.address.province}
          onChange={(e) =>
            setForm((prev) => ({
              ...prev,
              address: { ...prev.address, province: e.target.value },
            }))
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
          {t("Signup")}
        </Button>
        <Link to="/login">
          <Button type="button" fullWidth>
            {t("Login")}
          </Button>
        </Link>
      </Stack>
    </Stack>
  );
}

export default Register;
