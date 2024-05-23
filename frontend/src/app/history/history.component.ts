import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  entries: any[] = [];

  ngOnInit(): void {
    this.loadEntries();
  }

  loadEntries(): void {
    const entries = localStorage.getItem('calorieEntries');
    if (entries) {
      this.entries = JSON.parse(entries);
    }
  }
}
