import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-meals',
  templateUrl: './meals.component.html',
  styleUrls: ['./meals.component.css']
})
export class MealsComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'ingredients', 'calories', 'actions'];
  dataSource = new MatTableDataSource<any>([]);
  newMeal: any = { name: '', ingredients: '', calories: 0 };

  initialData = [
    { id: 1, name: "Gericht 1", ingredients: "Zutat 1, Zutat 2", calories: 300 },
    { id: 2, name: "Gericht 2", ingredients: "Zutat 3, Zutat 4", calories: 400 },
    { id: 3, name: "Gericht 3", ingredients: "Zutat 5, Zutat 6", calories: 500 }
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.loadMealItems();
  }

  loadMealItems(): void {
    const storedItems = JSON.parse(localStorage.getItem('mealItems') || '[]');
    this.dataSource.data = storedItems.length > 0 ? storedItems : this.initialData;
  }

  addMeal(): void {
    if (this.newMeal.name.trim() === '' || this.newMeal.ingredients.trim() === '' || this.newMeal.calories <= 0) {
      this.snackBar.open('Bitte füllen Sie alle Felder korrekt aus', 'Schließen', {
        duration: 2000,
      });
      return;
    }
    const items = this.dataSource.data;
    this.newMeal.id = new Date().getTime(); // simple id generation
    items.push(this.newMeal);
    localStorage.setItem('mealItems', JSON.stringify(items));
    this.dataSource.data = items;
    this.newMeal = { name: '', ingredients: '', calories: 0 };
    this.snackBar.open('Meal added successfully', 'Close', {
      duration: 1000,
    });
  }

  deleteMeal(id: number): void {
    let items = this.dataSource.data;
    items = items.filter((meal: any) => meal.id !== id);
    localStorage.setItem('mealItems', JSON.stringify(items));
    this.dataSource.data = items;
    this.snackBar.open('Meal deleted successfully', 'Close', {
      duration: 1000,
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
