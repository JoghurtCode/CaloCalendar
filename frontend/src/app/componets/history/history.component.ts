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
  view: 'week' | 'month' | 'year' = 'week'; // Standardansicht ist Woche
  weekDates: Date[] = [];
  monthDates: Date[] = [];
  yearMonths: string[] = [];
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
    if (this.view === 'week') {
      this.generateWeeklyChart();
    } else if (this.view === 'month') {
      this.generateMonthlyChart();
    } else if (this.view === 'year') {
      this.generateYearlyChart();
    }
  }

  generateWeeklyChart(): void {
    const firstDay = new Date(this.currentDate);
    firstDay.setDate(firstDay.getDate() - firstDay.getDay() + 1); // Montag
    const lastDay = new Date(this.currentDate);
    lastDay.setDate(lastDay.getDate() - lastDay.getDay() + 7); // Sonntag
    this.weekDates = this.getDatesBetween(firstDay, lastDay);

    // Dummy-Daten für Kalorien und Gewicht
    const caloriesData = [2000, 1800, 2200, 2400, 2100, 1900, 2300];
    const weightData = [70, 69.5, 69.8, 69.3, 68.9, 68.7, 68.5];

    this.createCombinedChart(this.weekDates.map(date => date.toLocaleDateString()), caloriesData, weightData);
  }

  generateMonthlyChart(): void {
    const firstDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1);
    const lastDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1, 0);
    this.monthDates = this.getDatesBetween(firstDay, lastDay);

    // Dummy-Daten für Kalorien und Gewicht (pro Tag im Monat)
    const daysInMonth = new Array(this.monthDates.length).fill(0).map((_, i) => i + 1);
    const caloriesData = daysInMonth.map(() => Math.floor(Math.random() * 1000 + 1500));
    const weightData = daysInMonth.map(() => Math.random() * 5 + 65);

    this.createCombinedChart(this.monthDates.map(date => date.toLocaleDateString()), caloriesData, weightData);
  }

  generateYearlyChart(): void {
    const firstDay = new Date(this.currentDate.getFullYear(), 0, 1);
    const lastDay = new Date(this.currentDate.getFullYear(), 11, 31);
    const yearDates = this.getDatesBetween(firstDay, lastDay);

    // Dummy-Daten für Kalorien und Gewicht (pro Monat im Jahr)
    const monthsInYear = new Array(12).fill(0).map((_, i) => i + 1);
    const caloriesData = monthsInYear.map(() => Math.floor(Math.random() * 30000 + 50000));
    const weightData = monthsInYear.map(() => Math.random() * 20 + 50);

    this.yearMonths = yearDates
      .filter((_, i) => i % 30 === 0)
      .map(date => date.toLocaleDateString('default', { month: 'short' }));

    this.createCombinedChart(this.yearMonths, caloriesData, weightData);
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

  previous(): void {
    if (this.view === 'week') {
      this.currentDate.setDate(this.currentDate.getDate() - 7);
    } else if (this.view === 'month') {
      this.currentDate.setMonth(this.currentDate.getMonth() - 1);
    } else if (this.view === 'year') {
      this.currentDate.setFullYear(this.currentDate.getFullYear() - 1);
    }
    this.generateCharts();
  }

  next(): void {
    if (this.view === 'week') {
      this.currentDate.setDate(this.currentDate.getDate() + 7);
    } else if (this.view === 'month') {
      this.currentDate.setMonth(this.currentDate.getMonth() + 1);
    } else if (this.view === 'year') {
      this.currentDate.setFullYear(this.currentDate.getFullYear() + 1);
    }
    this.generateCharts();
  }

  getViewTitle(): string {
    if (this.view === 'month') {
      return this.currentDate.toLocaleDateString('default', { month: 'long', year: 'numeric' });
    } else if (this.view === 'year') {
      return this.currentDate.getFullYear().toString();
    }
    return '';
  }
  

  getMonthName(monthIndex: number): string {
    const monthNames = ['Januar', 'Februar', 'März', 'April', 'Mai', 'Juni',
                        'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember'];
    return monthNames[monthIndex];
  }
}
