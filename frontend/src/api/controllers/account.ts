import axios from "axios";
import { LoginForm } from "../../types/Account";
import { User } from "../../types/Account";

export const getCurrentUserRequest = ({
  hideMessage,
}: {
  hideMessage: boolean;
}) => axios.get<User>(`/account`);

export const getActiveUserRequest = (id: number) =>
  axios.get(`/account/active/${id}`);

export const logoutRequest = () => axios.get(`/account/logout`);

export const loginRequest = (form: LoginForm) =>
  axios.post(`/account/login`, form);
