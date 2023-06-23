import axios from "axios";
import { Product, ProductForm } from "../../types/Product";

export interface FetchProductsParams {
  pageable: {
    page: number,
    size: number
  },
  sort: string,
  categories: number[],
  search: string
}

export const deleteProductRequest = (id: number) =>
  axios.delete(`/product/${id}`);

export const getProductsRequest = () => axios.get<Product[]>(`/product`);

export const getProductsWithFilterRequest = (params: FetchProductsParams) => axios.get<Product[]>(`/product/filter?title=${params.search || ``}${params.categories.map(v => `&categoryIds=${v}`).join("")}&page=${params.pageable.page}&size=${params.pageable.size}&sort=${params.sort}`);

export const getProductRequest = (id: number) =>
  axios.get<Product>(`/product/${id}`);

export const createProductRequest = (form: ProductForm) =>
  axios.post<Product>(`/product`, form);

export const updateProductRequest = (form: ProductForm) =>
  axios.put<Product>(`/product/{id}`, form);
