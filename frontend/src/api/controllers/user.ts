import axios from "axios";
import { RegisterForm } from "../../types/Account";

export const getUsersRequest = () => axios.get(`/user`);

export const getUserRequest = (id: number) => axios.get(`/user/${id}`);

export const getActiveUserRequest = (id: number) =>
  axios.get(`/user/${id}/active`);

export const createUserRequest = (form: RegisterForm) =>
  axios.post(`/user`, form);

export const updateUserRequest = (id: number, form: RegisterForm) =>
  axios.put(`/user/${id}`, form);
