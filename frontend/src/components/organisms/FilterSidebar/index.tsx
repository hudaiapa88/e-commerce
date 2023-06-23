import { Grid } from "@mui/material";
import React, { useState, useEffect } from "react";
import OptionFilter from "../../molecules/OptionFilter";
import { FilterState, changeSort, changeCategory } from "../../../redux/slices/filter";
import { useAppDispatch } from "../../../redux/store";
import { useTranslation } from "react-i18next";
import SelectFilter from "../../molecules/SelectFilter";
import { getCategorysRequest } from "../../../api/controllers/category";

interface Props {
  filterData: FilterState;
}

function FilterSidebar({ filterData }: Props) {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [categories, setCategories] = useState([]);

  const getCategories = async () => {
    await getCategorysRequest().then(res => setCategories(res.data))
  }

  useEffect(() => {
    getCategories();
  }, []);


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
      <Grid item xs={12}>
        <SelectFilter
          title={t("Categories")}
          onChange={(e) => {
            dispatch(changeCategory(e));
          }}
          options={categories}
          checked={filterData.category}
        />
      </Grid>
    </Grid>
  );
}

export default FilterSidebar;
