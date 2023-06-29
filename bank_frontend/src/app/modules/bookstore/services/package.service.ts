import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {PackageModel} from "../model/package.model";
import {PackageDTOModel} from "../dto/packageDTO.model";


@Injectable({
  providedIn: 'root'
})
export class PackageService {
  private baseUrl = 'http://localhost:8083/api/v1/package-account';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<PackageModel[]> {
    return this.http.get<PackageModel[]>(this.baseUrl + '/all', {headers: this.headers});
  }

  createPackage(packageDTO: any): Observable<PackageDTOModel> {

    return this.http.post<any>(this.baseUrl + '/create-package', packageDTO, {headers: this.headers})
  }


}
