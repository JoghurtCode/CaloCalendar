import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profiles: any[] = [];

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.loadProfiles();
  }

  loadProfiles(): void {
    this.profileService.getUserProfiles().subscribe(
      data => {
        this.profiles = data;
      },
      error => {
        console.error('Error loading profiles', error);
        if (error.error) {
          console.error('Error details:', error.error);
        }
      }
    );
  }
}
