import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CaloCalendar';
  isLoggedIn = !!localStorage.getItem('token');
  username = localStorage.getItem('username') || '';
  showLogout = false;

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    this.isLoggedIn = false;
    this.username = '';
  }
}
