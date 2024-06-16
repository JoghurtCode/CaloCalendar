import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  view: 'week' | 'month' | 'year' = 'week';
  weekDates: Date[] = [];
  currentDate: Date = new Date();
  myChart: any;

  constructor() { }

  ngOnInit(): void {
    this.generateChart();
  }

  changeView(view: 'week' | 'month' | 'year'): void {
    this.view = view;
    if (view === 'week') {
      this.generateChart();
    }
  }

  generateChart(): void {
    const firstDay = new Date(this.currentDate);
    firstDay.setDate(firstDay.getDate() - firstDay.getDay());
    const lastDay = new Date(this.currentDate);
    lastDay.setDate(lastDay.getDate() - lastDay.getDay() + 6);
    this.weekDates = this.getDatesBetween(firstDay, lastDay);

    const values = [10, 20, 15, 25, 30, 35, 20];

    const ctx = document.getElementById('myChart') as HTMLCanvasElement;

    if (this.myChart) {
      this.myChart.destroy();
    }

    this.myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.weekDates.map(date => date.toLocaleDateString()),
        datasets: [{
          label: 'Werte',
          data: values,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  getDatesBetween(startDate: Date, endDate: Date): Date[] {
    const dates = [];
    let currentDate = new Date(startDate);
    while (currentDate <= endDate) {
      dates.push(new Date(currentDate));
      currentDate.setDate(currentDate.getDate() + 1);
    }
    return dates;
  }

  previousWeek(): void {
    console.log('Vorherige Woche Button geklickt');
    this.currentDate.setDate(this.currentDate.getDate() - 7);
    this.generateChart();
  }

  nextWeek(): void {
    console.log('Nächste Woche Button geklickt');
    this.currentDate.setDate(this.currentDate.getDate() + 7);
    this.generateChart();
  }
}
