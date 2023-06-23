import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Category } from "../../types/Product";

export interface FilterState {
  sort: string;
  category: Category[];
  pageable: {
    page: number,
    size: number
  }
  search: string;
}

const initialState: FilterState = {
  sort: "Old to new",
  category: [],
  pageable: {
    page: 0,
    size: 12
  },
  search: "",
};

export const filterSlice = createSlice({
  name: "filter",
  initialState,
  reducers: {
    changeSort: (state, action: PayloadAction<string>) => {
      state.sort = action.payload;
    },
    changeCategory: (state, action: PayloadAction<Category>) => {
      const has = state.category.some((v) => v.id === action.payload.id);
      if (has) {
        const newList = state.category.filter((v) => v.id !== action.payload.id);
        state.category = newList;
      } else {
        const newList = [...state.category, action.payload];
        state.category = newList;
      }
    },
    changePageable: (state, action: PayloadAction<number>) => {
      state.pageable.page = action.payload;
    },
    changeSearch: (state, action: PayloadAction<string>) => {
      state.search = action.payload;
    },
  },
});

export const { changeSort, changeCategory, changePageable, changeSearch } =
  filterSlice.actions;

export default filterSlice.reducer;
