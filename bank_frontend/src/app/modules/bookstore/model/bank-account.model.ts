export class BankAccountModel {
  id: number = 0;
  accountNumber: string = '';
  moneyBalance: number = 0;


  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.accountNumber = obj.accountNumber;
      this.moneyBalance = obj.moneyBalance;
    }
  }

}


