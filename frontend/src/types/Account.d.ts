export interface LoginForm {
  password: string;
  rememberMe: boolean;
  username: string;
}

export interface RegisterForm {
  address: Address;
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  phone: Phone;
}

export interface Address {
  buildingInformation: string;
  country: string;
  district: string;
  fullAddress: string;
  neighborhood: string;
  province: string;
  street: string;
}

export interface Phone {
  areaCode: string;
  countryCode: string;
  number: string;
}

export interface User {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
  role: "ADMIN" | "USER";
  email: string;
}

export interface UserFull {
  address: Address;
  createdDateTime: string;
  email: string;
  firstName: string;
  id: number;
  isActive: true;
  lastName: string;
  phone: Phone;
  title: string;
  updatedDateTime: string;
  username: string;
}
