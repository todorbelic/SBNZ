import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { CreditRequest } from '../model/credit-request';


@Injectable({
  providedIn: 'root'
})
export class CreditApprovalService {

  apiHost: string = 'http://localhost:8083/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  creditRequest(request: CreditRequest): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/v1/credit/credit-request-processing', request, {headers: this.headers});
  }

  rejectRequest(id: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/v1/credit/credit-request-rejection/'+ id , {headers: this.headers});
  }

  approveRequest(id: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/v1/credit/credit-request-approval/' + id, {headers: this.headers});
  }

}