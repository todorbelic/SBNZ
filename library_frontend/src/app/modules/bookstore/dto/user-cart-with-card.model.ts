import { UserToken } from "../model/user-token.model";
import { Address } from "./adress.model";
import {Card} from "./card.model";

export class UserCartCard {
  userId!: number
  processedOrder!: any
  address: Address
  card: Card
  paymentMethod!: number

  constructor(userId: number, processedOrder: any, address: Address, card: Card, paymentMethod: number) {
    this.userId = userId;
    this.processedOrder = processedOrder;
    this.address = address;
    this.card = card;
    this.paymentMethod = paymentMethod;
  }
}
