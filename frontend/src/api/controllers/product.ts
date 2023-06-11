import axios from "axios";
import { Product, ProductForm } from "../../types/Product";

export const deleteProductRequest = (id: number) =>
  axios.delete(`/product/${id}`);

export const getProductsRequest = () => axios.get<Product[]>(`/product`);

export const getProductRequest = (id: number) =>
  axios.get<Product>(`/product/${id}`);

export const createProductRequest = (form: ProductForm) =>
  axios.post<Product>(`/product`, form);

export const updateProductRequest = (form: ProductForm) =>
  axios.put<Product>(`/product/{id}`, form);
