import { Address, UserFull } from "./Account";

export interface Order {
  id: number;
  address: Address;
  orderLines: OrderLine[];
  totalPrice: number;
  user: UserFull;
}

export interface OrderForm {
  address: Address;
  orderLines: {
    productId: number;
    quantity: number;
  }[];
  saveCreditCardRequest: CreditCard;
}

export interface OrderLine {
  id: number;
  quantity: number;
}

export interface CreditCard {
  cvv2: string;
  date: string;
  no: string;
}
