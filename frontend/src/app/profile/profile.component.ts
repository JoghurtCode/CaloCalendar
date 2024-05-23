import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  username = 'JohnDoe';
  email = 'john.doe@example.com';
  password = '';

  updateProfile() {
    console.log('Profile updated:', {
      username: this.username,
      email: this.email,
      password: this.password
    });
  }
}
