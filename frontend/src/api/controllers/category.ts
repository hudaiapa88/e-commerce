import axios from "axios";
import { Category, CategoryForm } from "../../types/Product";

export const deleteCategoryRequest = (id: number) =>
  axios.delete(`/category/${id}`);

export const getCategorysRequest = () =>
  axios.get<Category[]>(`/category/parent`);

export const getCategoryRequest = (id: number) =>
  axios.get<Category>(`/category/parent/${id}`);

export const createCategoryRequest = (form: CategoryForm) =>
  axios.post<Category>(`/category/parent`, form);

export const updateCategoryRequest = (form: CategoryForm) =>
  axios.put<Category>(`/category/{id}`, form);
