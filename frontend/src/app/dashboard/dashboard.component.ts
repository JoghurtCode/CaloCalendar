import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  heutigeKalorien = 1800;
  letzteEintraege = [
    { name: 'Frühstück', calories: 500, date: 'May 17, 2024' },
    { name: 'Mittagessen', calories: 700, date: 'May 17, 2024' },
    { name: 'Abendessen', calories: 600, date: 'May 17, 2024' }
  ];
}
