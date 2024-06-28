import { Component } from '@angular/core';
import { ViewStateService } from '../../services/view-state.service';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent {
  constructor(public viewState: ViewStateService) {}

  openDetailView(componentName: string): void {
    this.viewState.setActiveComponent(componentName);
  }

  closeDetailView(): void {
    this.viewState.setActiveComponent(null);
  }
}
