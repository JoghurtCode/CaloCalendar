import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {GroceryItemService} from "../../services/grocery-item.service";

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements AfterViewInit, OnInit {
  constructor(private groceryItemService: GroceryItemService) {
  }
  displayedColumns: string[] = ['name', 'weight', 'calories', 'actions'];
  dataSource = new MatTableDataSource([
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
    { id: 15, name: "Blaubeeren", calories: 50, weight: 200 },
  ]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit() {
    this.groceryItemService.getAllGroceryItems().subscribe((data: any) => {
      this.dataSource.data = data;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
