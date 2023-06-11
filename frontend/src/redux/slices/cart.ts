import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Product } from "../../types/Product";

interface CartState {
  items: Product[];
}

const initialState: CartState = {
  items: JSON.parse(localStorage.getItem("cart")) || [],
};

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart: (state, action: PayloadAction<Product>) => {
      const newList = [...state.items, action.payload];
      localStorage.setItem("cart", JSON.stringify(newList));
      state.items = newList;
    },
    removeFromCart: (state, action: PayloadAction<Product>) => {
      let newList = state.items;
      const index = newList.findIndex((v) => v.id === action.payload.id);
      if (index > -1) {
        newList.splice(index, 1);
        localStorage.setItem("cart", JSON.stringify(newList));
        state.items = newList;
      }
    },
    clearCart: (state) => {
      localStorage.setItem("cart", JSON.stringify([]));
      state.items = [];
    },
  },
});

export const { addToCart, removeFromCart, clearCart } = cartSlice.actions;

export default cartSlice.reducer;
