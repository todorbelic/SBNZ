import { UserToken } from "../model/user-token.model";
import { Address } from "./adress.model";

export class UserCart {
    userId!: number
    processedOrder!: any
    address: Address
    paymentMethod!: number

    constructor(userId: number, processedOrder: any, address: Address, paymentMethod: number) {
        this.userId = userId;
        this.processedOrder = processedOrder;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }
}