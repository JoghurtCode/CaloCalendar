import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {MatNativeDateModule, MatOption} from '@angular/material/core';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatTableModule} from '@angular/material/table';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ProfileService } from './services/profile.service';
import { RecipesComponent } from './componets/recipes/recipes.component';
import { DashboardComponent } from './componets/dashboard/dashboard.component';
import { HistoryComponent } from './componets/history/history.component';
import { ProfileComponent } from './componets/profile/profile.component';
import { MealsComponent } from './componets/meals/meals.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ViewStateService } from './services/view-state.service';
import { GoalsComponent } from './componets/goals/goals.component';
import {MatSelect} from "@angular/material/select";
import { InventoryComponent } from './componets/inventory/inventory.component';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow,
  MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HistoryComponent,
    ProfileComponent,
    MealsComponent,
    RecipesComponent,
    GoalsComponent,
    InventoryComponent,

  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatListModule,
    MatGridListModule,
    MatSelect,
    MatOption,
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatCellDef,
    MatHeaderCellDef,
    MatHeaderRowDef,
    MatRowDef,
    MatRow,
    MatHeaderRow,
    MatPaginator,
    MatSort,
    MatTableModule
  ],
  providers: [
    ProfileService,
    ViewStateService,
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
