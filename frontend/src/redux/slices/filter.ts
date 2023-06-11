import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface FilterState {
  sort: string;
  brand: string[];
  model: string[];
  search: string;
}

const initialState: FilterState = {
  sort: "Old to new",
  brand: [],
  model: [],
  search: "",
};

export const filterSlice = createSlice({
  name: "filter",
  initialState,
  reducers: {
    changeSort: (state, action: PayloadAction<string>) => {
      state.sort = action.payload;
    },
    changeBrand: (state, action: PayloadAction<string>) => {
      const has = state.brand.some((v) => v === action.payload);
      if (has) {
        const newList = state.brand.filter((v) => v !== action.payload);
        state.brand = newList;
      } else {
        const newList = [...state.brand, action.payload];
        state.brand = newList;
      }
    },
    changeModel: (state, action: PayloadAction<string>) => {
      const has = state.model.some((v) => v === action.payload);
      if (has) {
        const newList = state.model.filter((v) => v !== action.payload);
        state.model = newList;
      } else {
        const newList = [...state.model, action.payload];
        state.model = newList;
      }
    },
    changeSearch: (state, action: PayloadAction<string>) => {
      state.search = action.payload;
    },
  },
});

export const { changeSort, changeBrand, changeModel, changeSearch } =
  filterSlice.actions;

export default filterSlice.reducer;
