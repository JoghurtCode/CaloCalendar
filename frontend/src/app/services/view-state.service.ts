import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ViewStateService {
  activeComponent: string | null = null;

  setActiveComponent(componentName: string | null): void {
    this.activeComponent = componentName;
  }
}
