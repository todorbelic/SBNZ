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

  gradeBook(rating: any): Observable<any> {
    return this.http.post<any>(this.baseUrl + '/gradeBook', rating, {headers: this.headers})
  }

  getRecommendedNonAuth() {
    return this.http.get<BookWithAuthorsModel[]>(this.baseUrl + '/non-auth-recommendations', {headers: this.headers});
  }

  getRecommendedAuth(userId: any) {
    return this.http.get<BookWithAuthorsModel[]>(this.baseUrl + '/auth-user-recommendations/' + userId, {headers: this.headers});
  }
}
