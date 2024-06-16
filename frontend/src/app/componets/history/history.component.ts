import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js/auto';
import 'chart.js';

declare module 'chart.js' {
  interface TickOptions {
    beginAtZero?: boolean;
  }
}

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  view: 'week' | 'month' | 'year' = 'week'; // default view is week
  weekDates: Date[] = [];
  currentDate: Date = new Date();
  chart: Chart | undefined;

  constructor() {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.generateCharts();
  }

  changeView(view: 'week' | 'month' | 'year'): void {
    this.view = view;
    this.generateCharts();
  }

  generateCharts(): void {
    // Calculate the dates for the current week
    const firstDay = new Date(this.currentDate);
    firstDay.setDate(firstDay.getDate() - firstDay.getDay() + 1); // Monday
    const lastDay = new Date(this.currentDate);
    lastDay.setDate(lastDay.getDate() - lastDay.getDay() + 7); // Sunday
    this.weekDates = this.getDatesBetween(firstDay, lastDay);

    // Dummy data for demonstration purposes
    const caloriesData = [2000, 1800, 2200, 2400, 2100, 1900, 2300];
    const weightData = [70, 69.5, 69.8, 69.3, 68.9, 68.7, 68.5];

    // Use Chart.js to create combined chart
    this.createCombinedChart(this.weekDates.map(date => date.toLocaleDateString()), caloriesData, weightData);
  }

  createCombinedChart(labels: string[], caloriesData: number[], weightData: number[]): void {
    const ctx = document.getElementById('combinedChart') as HTMLCanvasElement;
    if (this.chart) {
      this.chart.destroy();
    }
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Kalorien',
            data: caloriesData,
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            yAxisID: 'calories-axis'
          },
          {
            label: 'Gewicht',
            data: weightData,
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1,
            yAxisID: 'weight-axis'
          }
        ]
      },
      options: {
        scales: {
          'calories-axis': {
            type: 'linear',
            position: 'left',
            title: {
              display: true,
              text: 'Kalorien'
            },
            ticks: {
              beginAtZero: true
            }
          },
          'weight-axis': {
            type: 'linear',
            position: 'right',
            title: {
              display: true,
              text: 'Gewicht (kg)'
            },
            ticks: {
              beginAtZero: true
            }
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
    this.currentDate.setDate(this.currentDate.getDate() - 7);
    this.generateCharts();
  }

  nextWeek(): void {
    this.currentDate.setDate(this.currentDate.getDate() + 7);
    this.generateCharts();
  }
}
