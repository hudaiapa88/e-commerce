import { IconButton, Stack, Tooltip, Typography } from "@mui/material";
import React from "react";
import { Product } from "../../../types/Product";
import { useAppDispatch } from "../../../redux/store";
import { addToCart, removeFromCart } from "../../../redux/slices/cart";

interface Props {
  product: Product;
  count: number | string;
}

function CartItem({ product, count }: Props) {
  const dispatch = useAppDispatch();
  return (
    <Stack direction="row" spacing={2} justifyContent="space-between">
      <Stack>
        <Tooltip title={product.title}>
          <Typography
            variant="body2"
            sx={{
              whiteSpace: "nowrap",
              overflow: "hidden",
              textOverflow: "ellipsis",
              maxWidth: 90,
            }}
          >
            {product.title}
          </Typography>
        </Tooltip>
        <Typography variant="body2" color="primary" fontWeight={500}>
          {product.price}
        </Typography>
      </Stack>
      <Stack direction="row" alignItems="center">
        <IconButton onClick={() => dispatch(removeFromCart(product))}>
          -
        </IconButton>
        <Typography
          sx={(theme) => ({
            bgcolor: theme.palette.primary.main,
            color: theme.palette.primary.contrastText,
            px: 1,
          })}
        >
          {count}
        </Typography>
        <IconButton onClick={() => dispatch(addToCart(product))}>+</IconButton>
      </Stack>
    </Stack>
  );
}

export default CartItem;
