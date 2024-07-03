import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ViewStateService } from '../../services/view-state.service';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {

  goal: any = {
    name: '',
    targetWeight: 0
  };
  profile: any = {};

  constructor(
    private profileService: ProfileService,
    private snackBar: MatSnackBar,
    public viewState: ViewStateService
  ) {}

  ngOnInit(): void {
    this.loadProfile();
    this.loadGoalFromLocalStorage();
  }

  updateGoal(): void {
    localStorage.setItem('goalName', this.goal.name);
    this.snackBar.open('Ziel erfolgreich aktualisiert', 'Schließen', {
      duration: 1000,
    });
  }

  loadProfile(): void {
    this.profileService.getUserProfileById(1).subscribe(
      (data: any) => {
        this.profile = data;
        this.goal.targetWeight = data.weight;
      },
      error => {
        console.error('Fehler beim Laden des Profils', error);
      }
    );
  }

  loadGoalFromLocalStorage(): void {
    const storedGoalName = localStorage.getItem('goalName');
    if (storedGoalName) {
      this.goal.name = storedGoalName;
    }
  }

  openDetailView(componentName: string): void {
    this.viewState.setActiveComponent(componentName);
  }

  closeDetailView(): void {
    this.viewState.setActiveComponent(null);
  }
}
