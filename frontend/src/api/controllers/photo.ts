import axios from "axios";

export const getPhotoRequest = (id: number) =>
  axios.get(`/photo/product/${id}`);

export const uploadPhotoRequest = (id: number, file: FormData) =>
  axios.post(`/photo/product/${id}`, file);
