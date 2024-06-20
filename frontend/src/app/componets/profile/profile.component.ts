import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { UserProfile } from '../../models/user-profile.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profiles: UserProfile[] = [{
    id: 0,
    userName: 'Standardname',
    gender: 0,
    age: 0,
    weight: 0,
    levelOfPhysicalActivity: 0,
    goal: 'Standardziel',
    timeFrame: 'Standardzeitraum'
  }];

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.loadProfiles();
  }

  loadProfiles(): void {
    this.profileService.getUserProfiles().subscribe(
      (data: UserProfile[]) => {
        this.profiles = data.length > 0 ? data : this.profiles;
           console.log("Spring Boot API Profiles: ", this.profiles);
        },
      error => {
        console.error('Error loading profiles', error);
      }
    );
  }
}
