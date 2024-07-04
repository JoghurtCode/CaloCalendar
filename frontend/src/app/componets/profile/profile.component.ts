import {Component, OnInit} from '@angular/core';
import {ProfileService} from '../../services/profile.service';
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: any

  constructor(private profileService: ProfileService, private snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.loadProfiles();
  }

  updateProfile(): void {
    this.profileService.updateUserProfile(this.profile).subscribe(
      (data: any) => {
        this.profile = data;
        this.snackBar.open('Profile updated successfully', 'Close', {
          duration: 1000,
        });

      },
      error => {
        console.error('Error updating profile', error);
        this.snackBar.open('Error updating profile', 'Close', {
          duration: 2000,
        });
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
