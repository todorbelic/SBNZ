export class EmploymentInfo {
    employed: boolean = false;
    indefinitely: boolean = false;
    employmentStartDate: string = '';
    employmentEndDate: string = '';
    monthlyIncome: number = 0;
  
    public constructor(obj?: any) {
      if(obj) {
        this.employed = obj.employed;
        this.indefinitely = obj.indefinitely;
        this.employmentStartDate = obj.employmentStartDate;
        this.employmentEndDate = obj.employmentEndDate;
        this.monthlyIncome = obj.monthlyIncome;
      }
    }
  }