import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {BookWithAuthorsModel} from "../dto/book-with-authors.model";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:8082/api/v1/books';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<BookWithAuthorsModel[]> {
    return this.http.get<BookWithAuthorsModel[]>(this.baseUrl + '/all', {headers: this.headers});
  }

  addBook(newBook: any): Observable<BookWithAuthorsModel> {
    return this.http.post<any>(this.baseUrl + '/addBook', newBook, {headers: this.headers})
  }

}
