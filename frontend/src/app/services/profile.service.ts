import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getUserProfiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/userProfile`);
  }

  getUserProfileById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/userProfile/${id}`);
  }

  createUserProfile(userProfile: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/userProfile`, userProfile);
  }

  updateUserProfile(userProfile: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/userProfile`, userProfile);
  }

  deleteUserProfile(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/userProfile/${id}`);
  }
}
