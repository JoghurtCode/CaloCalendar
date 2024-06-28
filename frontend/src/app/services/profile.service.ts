import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserProfile } from '../models/user-profile.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getUserProfiles(): Observable<UserProfile[]> {
    return this.http.get<UserProfile[]>(`${this.baseUrl}/userProfile`);
  }

  getUserProfileById(id: number): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${this.baseUrl}/userProfile/${id}`);
  }

  createUserProfile(userProfile: UserProfile): Observable<UserProfile> {
    return this.http.post<UserProfile>(`${this.baseUrl}/userProfile`, userProfile);
  }

  updateUserProfile(userProfile: UserProfile): Observable<UserProfile> {
    return this.http.put<UserProfile>(`${this.baseUrl}/userProfile`, userProfile);
  }

  deleteUserProfile(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/userProfile/${id}`);
  }
}
