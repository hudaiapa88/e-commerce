import { Button, Card, Grid, Stack, Typography } from "@mui/material";
import React, { useEffect } from "react";
import CartSidebar from "../components/organisms/CartSidebar";
import { useParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../redux/store";
import { fetchProduct } from "../redux/slices/products";
import { addToCart } from "../redux/slices/cart";
import { BASE_URL } from "../api/ApiProvider";
import { useTranslation } from "react-i18next";

function ProductDetail() {
  let { productId } = useParams();
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const product = useAppSelector((state) => {
    return state.products.selected;
  });

  useEffect(() => {
    dispatch(fetchProduct(Number(productId)));
    return () => {
      dispatch(fetchProduct(null));
    };
  }, []);

  return (
    product && (
      <Grid container spacing={4}>
        <Grid component="main" item xs={12} sm={7} md={8} lg={9} xl={10}>
          <Card sx={{ p: 2 }}>
            <Grid container spacing={4}>
              <Grid item xs={12} md={6}>
                <img
                  src={`${BASE_URL}/photo/product/${product.id}`}
                  style={{
                    aspectRatio: "4/3",
                    width: "100%",
                  }}
                  alt={product.title}
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <Stack spacing={6} mt={1}>
                  <Stack spacing={1}>
                    <Typography component="h2" fontSize={24}>
                      {product.title}
                    </Typography>
                    <Typography color="primary" fontWeight={500} fontSize={24}>
                      {product.price}₺
                    </Typography>
                  </Stack>
                  <Stack spacing={2}>
                    <Button
                      variant="contained"
                      onClick={() => dispatch(addToCart(product))}
                    >
                      {t("Add to Cart")}
                    </Button>
                  </Stack>
                </Stack>
              </Grid>
            </Grid>
          </Card>
        </Grid>
        <Grid item xs={12} sm={5} md={4} lg={3} xl={2}>
          <CartSidebar />
        </Grid>
      </Grid>
    )
  );
}

export default ProductDetail;
