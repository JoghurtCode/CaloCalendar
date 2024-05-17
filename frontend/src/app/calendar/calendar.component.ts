import { Component, OnInit } from '@angular/core';
// @ts-ignore
import { CalendarEvent } from '../models/calendar-event.model';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  entries: CalendarEvent[] = [];

  ngOnInit(): void {
    this.loadEntries();
  }

  get sortedEntries(): CalendarEvent[] {
    return this.entries.sort((a, b) => b.date.getTime() - a.date.getTime());
  }

  loadEntries(): void {
    const savedEntries = localStorage.getItem('caloriesEntries');
    if (savedEntries) {
      this.entries = JSON.parse(savedEntries);
    } else {
      // Beispiel Daten falls keine vorhanden sind
      this.entries = [
        { date: new Date(), calories: 1800 },
        { date: new Date(new Date().setDate(new Date().getDate() - 1)), calories: 1700 }
      ];
    }
  }

  saveEntries(): void {
    localStorage.setItem('caloriesEntries', JSON.stringify(this.entries));
  }

  // Beispielmethode um einen neuen Eintrag hinzuzufügen
  addEntry(entry: CalendarEvent): void {
    this.entries.push(entry);
    this.saveEntries();
  }
}
