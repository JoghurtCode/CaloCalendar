import {Component, OnInit} from '@angular/core';
import {ProfileService} from '../../services/profile.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: any

  constructor(private profileService: ProfileService) {
  }

  ngOnInit(): void {
    this.loadProfiles();
  }
  updateProfile(): void {
    this.profileService.updateUserProfile(this.profile).subscribe(
      (data: any) => {
        this.profile = data;
      },
      error => {
        console.error('Error updating profile', error);
      }
    );
  }
  loadProfiles(): void {
    this.profileService.getUserProfileById(1).subscribe(
      (data: any[]) => {
        this.profile = data;

      },
      error => {
        console.error('Error loading profiles', error);
      }
    );
  }
}
