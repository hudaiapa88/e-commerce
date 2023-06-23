import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Product } from "../../types/Product";
import {
  FetchProductsParams,
  getProductRequest,
  getProductsWithFilterRequest,
} from "../../api/controllers/product";

interface ProductState {
  all: {
    content: Product[],
    totalPages: number
  };
  selected: Product | null;
}

const initialState: ProductState = {
  all: {
    content: [],
    totalPages: 1
  },
  selected: null,
};

export const fetchProducts = createAsyncThunk(
  "products/fetchProducts",
  async (params: FetchProductsParams) => {
    const response = await getProductsWithFilterRequest(params);
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
      state.all = action.payload as any;
    });
    builder.addCase(fetchProduct.fulfilled, (state, action) => {
      state.selected = action.payload;
    });
  },
});

export default productSlice.reducer;
