import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Product } from "../../types/Product";
import {
  getProductRequest,
  getProductsRequest,
} from "../../api/controllers/product";

interface ProductState {
  all: Product[];
  selected: Product | null;
}

const initialState: ProductState = {
  all: [],
  selected: null,
};

export const fetchProducts = createAsyncThunk(
  "products/fetchProducts",
  async () => {
    const response = await getProductsRequest();
    return response.data;
  }
);

export const fetchProduct = createAsyncThunk(
  "products/fetchProduct",
  async (productId: number) => {
    if (productId) {
      const response = await getProductRequest(productId);
      return response.data;
    } else {
      return null;
    }
  }
);

export const productSlice = createSlice({
  name: "product",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchProducts.fulfilled, (state, action) => {
      state.all = action.payload;
    });
    builder.addCase(fetchProduct.fulfilled, (state, action) => {
      state.selected = action.payload;
    });
  },
});

export default productSlice.reducer;
