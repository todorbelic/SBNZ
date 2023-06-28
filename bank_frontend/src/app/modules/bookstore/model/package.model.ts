import {BankAccountModel} from "./bank-account.model";

export class PackageModel {
  id: number = 0;
  type: number = 0;
  bankAccount: BankAccountModel = new BankAccountModel();

  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.type = obj.type;
      this.bankAccount = obj.bankAccount;
    }
  }
}
