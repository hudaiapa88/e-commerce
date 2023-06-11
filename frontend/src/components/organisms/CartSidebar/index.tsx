import { Grid } from "@mui/material";
import React from "react";
import Cart from "../../molecules/Cart";
import { useAppSelector } from "../../../redux/store";
import Checkout from "../../molecules/Checkout";

function CartSidebar() {
  const cart = useAppSelector((state) => {
    return state.cart.items;
  });
  const total = cart.reduce((a, b) => Number(a) + Number(b.price), 0);
  return (
    <Grid component="aside" container spacing={4}>
      {cart.length > 0 && (
        <Grid item xs={12}>
          <Cart cart={cart} />
        </Grid>
      )}
      <Grid item xs={12}>
        <Checkout
          total={new Intl.NumberFormat("tr-TR", {
            style: "currency",
            currency: "TRY",
          }).format(total || 0)}
        />
      </Grid>
    </Grid>
  );
}

export default CartSidebar;
