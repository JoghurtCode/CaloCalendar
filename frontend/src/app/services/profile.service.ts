import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getUserProfiles(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/userProfile`);
  }

  getUserProfileById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/userProfile/${id}`);
  }

  createUserProfile(userProfile: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/userProfile`, userProfile);
  }

  updateUserProfile(userProfile: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/userProfile`, userProfile);
  }

  deleteUserProfile(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/userProfile/${id}`);
  }
}
