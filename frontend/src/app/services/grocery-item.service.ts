import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GroceryItemService {

  private apiUrl = 'http://localhost:8080/groceryItems';  // Replace with your actual API URL

  constructor(private http: HttpClient) { }

  getAllGroceryItems(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addGroceryItem(item: any): Observable<number> {
    return this.http.post<number>(this.apiUrl, item)
      .pipe(catchError(this.handleError));
  }

  updateGroceryItem(item: any): Observable<any> {
    return this.http.put<any>(this.apiUrl, item)
      .pipe(catchError(this.handleError));
  }

  getGroceryItemById(id: number): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<any>(url)
      .pipe(catchError(this.handleError));
  }

  deleteGroceryItem(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any): Observable<never> {
    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
