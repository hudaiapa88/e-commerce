import {
  Alert,
  AlertTitle,
  Button,
  Grid,
  Pagination,
  Stack,
  useTheme,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { fetchProducts } from "../redux/slices/products";
import { useAppDispatch, useAppSelector } from "../redux/store";
import { usePagination } from "../utils/Pagination";
import { Product } from "../types/Product";
import ProductCard from "../components/molecules/ProductCard";
import FilterSidebar from "../components/organisms/FilterSidebar";
import CartSidebar from "../components/organisms/CartSidebar";
import { useTranslation } from "react-i18next";

function Products() {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const products = useAppSelector((state) => {
    return state.products.all;
  });

  const filter = useAppSelector((state) => {
    return state.filter;
  });

  const theme = useTheme();
  const [responsive, setResponsive] = useState({
    show: true,
    downSm: false,
  });

  const filterProducts = () => {
    const sorted = [...products].sort((a, b) => {
      switch (filter.sort) {
        case "Old to new":
          return Date.parse(a.createdDateTime) - Date.parse(b.createdDateTime);
        case "New to old":
          return Date.parse(b.createdDateTime) - Date.parse(a.createdDateTime);
        case "Price hight to low":
          return Number(b.price) - Number(a.price);
        case "Price low to High":
          return Number(a.price) - Number(b.price);
        default:
          return 0;
      }
    });

    const filtered = sorted.filter((v) =>
      v.title.toLocaleUpperCase().includes(filter.search.toLocaleUpperCase())
    );

    return filtered;
  };

  const { paginationData, count, onChange, page } = usePagination(
    filterProducts(),
    12
  );

  useEffect(() => {
    dispatch(fetchProducts());
  }, []);

  useEffect(() => {
    if (window.innerWidth < theme.breakpoints.values.sm) {
      setResponsive({
        show: false,
        downSm: true,
      });
    }
  }, []);
  return (
    <Grid container spacing={4}>
      <Grid item xs={12} sm={4} md={3} xl={2}>
        {responsive.show && (
          <FilterSidebar products={products} filterData={filter} />
        )}
        {responsive.downSm && (
          <Button
            onClick={() =>
              setResponsive((prev) => ({ ...prev, show: !prev.show }))
            }
            variant="outlined"
            fullWidth
          >
            {responsive.show ? "Close Filter" : "Filter"}
          </Button>
        )}
      </Grid>
      <Grid component="main" item xs={12} sm={4} md={6} xl={8}>
        <Stack spacing={4}>
          <Grid container spacing={4}>
            {paginationData.length > 0 ? (
              paginationData.map((v: Product, i) => (
                <Grid key={i} item xs={12} md={6} lg={4} xl={3}>
                  <ProductCard {...v} />
                </Grid>
              ))
            ) : (
              <Grid item xs={12}>
                <Alert severity="warning">
                  <AlertTitle>{t("Sorry!")}</AlertTitle>
                  {t("We couldn't find the product you were looking for")}
                </Alert>
              </Grid>
            )}
          </Grid>
          {paginationData.length > 0 && (
            <Pagination
              sx={{ display: "flex", justifyContent: "center", pb: 4 }}
              count={count}
              page={page}
              variant="outlined"
              shape="rounded"
              onChange={onChange}
            />
          )}
        </Stack>
      </Grid>
      <Grid item xs={12} sm={4} md={3} xl={2}>
        <CartSidebar />
      </Grid>
    </Grid>
  );
}

export default Products;
