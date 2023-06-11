import { Button, Card, Stack, Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";

interface Props {
  total: string | number;
}

function Checkout({ total }: Props) {
  const { t } = useTranslation();
  return (
    <Card sx={{ p: 2 }}>
      <Stack spacing={2}>
        <Typography>
          {t("Total Price")}:{" "}
          <Typography color="primary" fontWeight={500} component="span">
            {total}
          </Typography>
        </Typography>
        <Link to="/user/checkout">
          <Button variant="contained" fullWidth>
            {t("Checkout")}
          </Button>
        </Link>
      </Stack>
    </Card>
  );
}

export default Checkout;
