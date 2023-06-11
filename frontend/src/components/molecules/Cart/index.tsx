import { Card, Stack } from "@mui/material";
import React from "react";
import CartItem from "../../atoms/CartItem";
import { Product } from "../../../types/Product";

interface Props {
  cart: Product[];
}

function Cart({ cart }: Props) {
  const noDuplicate = cart.filter(
    (value, index, self) => index === self.findIndex((t) => t.id === value.id)
  );

  return (
    <Card sx={{ p: 2 }}>
      <Stack spacing={1}>
        {noDuplicate.map((v, i) => (
          <CartItem
            key={i}
            product={v}
            count={cart.filter((c) => v.id === c.id).length}
          />
        ))}
      </Stack>
    </Card>
  );
}

export default Cart;
