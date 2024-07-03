import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'weight', 'calories', 'actions'];
  dataSource = new MatTableDataSource<any>([]);
  newItem: any = { name: '', weight: 0, calories: 0 };

  initialData = [
    { id: 1, name: "Reis", calories: 100, weight: 250 },
    { id: 2, name: "Kartoffeln", calories: 150, weight: 500 },
    { id: 3, name: "Tomaten", calories: 20, weight: 200 },
    { id: 4, name: "Zwiebeln", calories: 50, weight: 100 },
    { id: 5, name: "Paprika", calories: 30, weight: 200 },
    { id: 6, name: "Gurke", calories: 10, weight: 200 },
    { id: 7, name: "Karotten", calories: 30, weight: 200 },
    { id: 8, name: "Brokkoli", calories: 50, weight: 200 },
    { id: 9, name: "Blumenkohl", calories: 50, weight: 200 },
    { id: 10, name: "Kürbis", calories: 40, weight: 200 },
    { id: 11, name: "Spinat", calories: 20, weight: 200 },
    { id: 12, name: "Salat", calories: 10, weight: 200 },
    { id: 13, name: "Erdbeeren", calories: 50, weight: 200 },
    { id: 14, name: "Himbeeren", calories: 50, weight: 200 },
    { id: 15, name: "Blaubeeren", calories: 50, weight: 200 }
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.loadInventoryItems();
  }

  loadInventoryItems(): void {
    const storedItems = JSON.parse(localStorage.getItem('inventoryItems') || '[]');
    this.dataSource.data = storedItems.length > 0 ? storedItems : this.initialData;
  }

  addItem(): void {
    if (this.newItem.name.trim() === '' || this.newItem.weight <= 0 || this.newItem.calories <= 0) {
      this.snackBar.open('Bitte füllen Sie alle Felder korrekt aus', 'Schließen', {
        duration: 2000,
      });
      return;
    }
    const items = this.dataSource.data;
    this.newItem.id = new Date().getTime(); // simple id generation
    items.push(this.newItem);
    localStorage.setItem('inventoryItems', JSON.stringify(items));
    this.dataSource.data = items;
    this.newItem = { name: '', weight: 0, calories: 0 };
    this.snackBar.open('Item added successfully', 'Close', {
      duration: 1000,
    });
  }

  deleteItem(id: number): void {
    let items = this.dataSource.data;
    items = items.filter((item: any) => item.id !== id);
    localStorage.setItem('inventoryItems', JSON.stringify(items));
    this.dataSource.data = items;
    this.snackBar.open('Item deleted successfully', 'Close', {
      duration: 1000,
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
