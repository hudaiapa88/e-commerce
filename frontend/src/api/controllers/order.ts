import axios from "axios";
import { Order, OrderForm } from "../../types/Order";

export const deleteOrderRequest = (id: number) => axios.delete(`/order/${id}`);

export const getOrdersRequest = () => axios.get<Order[]>(`/order`);

export const getOrderRequest = (id: number) => axios.get<Order>(`/order/${id}`);

export const createOrderRequest = (isSave: boolean, form: OrderForm) =>
  axios.post<Order>(`/order?isSaveCard=${isSave}`, form);

export const updateOrderRequest = (id: number, form: OrderForm) =>
  axios.put<Order>(`/order/${id}`, form);

export const updateOrderShipRequest = (id: number) =>
  axios.put<Order>(`/order/${id}/ship-it`);
