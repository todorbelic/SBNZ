import { UserToken } from "../model/user-token.model";
import { Address } from "./adress.model";

export class UserCart {
    user: UserToken
    cartItems: any[]
    address: Address
    totalPrice: number = 0
    isCard: boolean = false

    constructor(user: UserToken, cartItems: any[], address: Address, totalPrice: number, isCard: boolean) {
        this.user = user;
        this.cartItems = cartItems;
        this.address = address;
        this.totalPrice = totalPrice;
        this.isCard = isCard;
    }
}