import { Grid } from "@mui/material";
import React from "react";
import OptionFilter from "../../molecules/OptionFilter";
import { Product } from "../../../types/Product";
import { FilterState, changeSort } from "../../../redux/slices/filter";
import { useAppDispatch } from "../../../redux/store";
import { useTranslation } from "react-i18next";

interface Props {
  products: Product[];
  filterData: FilterState;
}

function FilterSidebar({ products, filterData }: Props) {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  return (
    <Grid component="aside" container spacing={4}>
      <Grid item xs={12}>
        <OptionFilter
          title={t("Sort by")}
          onChange={(e) => {
            dispatch(changeSort(e.target.value));
          }}
          options={[
            "Old to new",
            "New to old",
            "Price hight to low",
            "Price low to High",
          ]}
          value={filterData.sort}
        />
      </Grid>
    </Grid>
  );
}

export default FilterSidebar;
