import { Component, OnInit } from '@angular/core';
import { CreditRequest } from '../../bookstore/model/credit-request';
import { CreditApprovalService } from '../../bookstore/services/credit-approval-service';

@Component({
  selector: 'app-create-credit-request',
  templateUrl: './create-credit-request.component.html',
  styleUrls: ['./create-credit-request.component.css']
})
export class CreateCreditRequestComponent implements OnInit {

  public loanApplication: CreditRequest = new CreditRequest();
  public requestId: number = 0;
  constructor(private creditApprovalService: CreditApprovalService) { }

  ngOnInit(): void {

  }

  submitForm() {
    // Log the form data for testing
    console.log(this.loanApplication.employmentInfo.employmentEndDate);
    this.creditApprovalService.creditRequest(this.loanApplication).subscribe(res=>{
      console.log(res)
      this.requestId = res.id
      if(res.approved) {
        window.alert("Client is suitable for credit")
      }else {
        window.alert("Client is NOT suitable for credit");
      }
    })
    // You can perform further processing or send the data to a server here
  }

  approveCredit() {
    this.creditApprovalService.approveRequest(this.requestId).subscribe(res=>{
      window.alert("Successfully approved request!")
    })
  }

  rejectCredit() {
    this.creditApprovalService.rejectRequest(this.requestId).subscribe(res=>{
      window.alert("Successfully rejected request!")
    })
  }
}
