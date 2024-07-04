import { Component } from '@angular/core';
import { ViewStateService } from '../../services/view-state.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor(public viewState: ViewStateService) {}


}
