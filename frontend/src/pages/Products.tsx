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
import { Product } from "../types/Product";
import ProductCard from "../components/molecules/ProductCard";
import FilterSidebar from "../components/organisms/FilterSidebar";
import CartSidebar from "../components/organisms/CartSidebar";
import { useTranslation } from "react-i18next";
import { changePageable } from "../redux/slices/filter";
import { getSort } from "../utils/Sort";

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

  useEffect(() => {
    dispatch(fetchProducts({ search: filter.search, sort: getSort(filter.sort), categories: filter.category.map(v => v.id), pageable: { page: 0, size: 12 } }));
  }, [JSON.stringify(filter)]);

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
          <FilterSidebar filterData={filter} />
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
            {products.content.length > 0 ? (
              products.content.map((v: Product, i) => (
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
          {products.content.length > 0 && (
            <Pagination
              sx={{ display: "flex", justifyContent: "center", pb: 4 }}
              count={products.totalPages}
              page={filter.pageable.page + 1}
              variant="outlined"
              shape="rounded"
              onChange={(e, page) => {
                dispatch(changePageable(page - 1))
              }}
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
