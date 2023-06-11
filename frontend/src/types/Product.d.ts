export interface Product {
  createdDateTime: string;
  id: number;
  category: Category;
  price: number;
  quantity: number;
  title: string;
  updatedDateTime: string;
}

export interface ProductForm {
  categoryId: number;
  price: number;
  quantity: number;
  title: string;
}

export interface Category {
  id: number;
  parent: string;
  title: string;
}

export interface CategoryForm {
  title: string;
}
