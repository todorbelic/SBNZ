import { BookWithAuthorsModel } from "./book-with-authors.model";

export class Order {
    orderItems!: BookWithAuthorsModel[];
  
    public constructor(obj?: any) {
      if (obj) {
        this.orderItems = obj.orderItems;
      }
    }
  }