import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USERS_KEY = 'auth_users';
  private readonly CURRENT_USER_KEY = 'current_user';

  private authStatus = new BehaviorSubject<boolean>(this.isAuthenticated());
  authStatus$ = this.authStatus.asObservable();

  constructor(private router: Router) {}

  login(username: string, password: string): boolean {
    const users = this.getUsers();
    const user = users.find(u => u.username === username && u.password === password);
    if (user) {
      localStorage.setItem(this.TOKEN_KEY, 'dummy-token');
      localStorage.setItem(this.CURRENT_USER_KEY, username);
      this.authStatus.next(true);
      return true;
    }
    return false;
  }

  register(username: string, password: string): boolean {
    let users = this.getUsers();
    if (users.find(u => u.username === username)) {
      return false; // Benutzername bereits vorhanden
    }
    users.push({ username, password });
    localStorage.setItem(this.USERS_KEY, JSON.stringify(users));
    localStorage.setItem(this.TOKEN_KEY, 'dummy-token');
    localStorage.setItem(this.CURRENT_USER_KEY, username);
    this.authStatus.next(true);
    return true;
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.CURRENT_USER_KEY);
    this.authStatus.next(false);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return localStorage.getItem(this.TOKEN_KEY) !== null;
  }

  getUsername(): string | null {
    return localStorage.getItem(this.CURRENT_USER_KEY);
  }

  private getUsers(): { username: string, password: string }[] {
    return JSON.parse(localStorage.getItem(this.USERS_KEY) || '[]');
  }
}
