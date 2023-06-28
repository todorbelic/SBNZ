import { EmploymentInfo } from "./employmentInfo";

export class CreditRequest {
    clientUsername: string = '';
    totalAmount: number = 0;
    minPaymentDeadline: string = '';
    maxPaymentDeadline: string = '';
    numOfInstallments: number = 0;
    employmentInfo: EmploymentInfo = new EmploymentInfo();
  
    public constructor(obj?: any) {
      if(obj) {
        this.clientUsername = obj.clientUsername;
        this.totalAmount = obj.totalAmount;
        this.minPaymentDeadline = obj.minPaymentDeadline;
        this.maxPaymentDeadline = obj.maxPaymentDeadline;
        this.numOfInstallments = obj.numOfInstallments;
        this.employmentInfo = obj.employmentInfo;
      }
    }
  }