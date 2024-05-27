import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  height: number = 1.8;
  goal: string = "Gewicht verlieren";
  weight: number = 75;
}
