import { configureStore } from "@reduxjs/toolkit";
import { cartSlice } from "./slices/cart";
import { productSlice } from "./slices/products";
import { useDispatch, useSelector, TypedUseSelectorHook } from "react-redux";
import { filterSlice } from "./slices/filter";

const store = configureStore({
  reducer: {
    products: productSlice.reducer,
    cart: cartSlice.reducer,
    filter: filterSlice.reducer,
  },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
export type AppDispatch = typeof store.dispatch;
export const useAppDispatch: () => AppDispatch = useDispatch;
