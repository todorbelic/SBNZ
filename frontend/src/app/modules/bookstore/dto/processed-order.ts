import { BookWithAuthorsModel } from "./book-with-authors.model";

export class ProcessedOrder {
    orderItems!: BookWithAuthorsModel[];
    totalPrice: number = 0;
    discount: number = 0;
  
    public constructor(obj?: any) {
      if (obj) {
        this.orderItems = obj.orderItems;
        this.discount = obj.discount;
        this.totalPrice = obj.totalPrice;
      }
    }
  }