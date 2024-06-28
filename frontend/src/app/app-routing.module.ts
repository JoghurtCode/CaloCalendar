import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './componets/dashboard/dashboard.component';
import { HistoryComponent } from './componets/history/history.component';
import { ProfileComponent } from './componets/profile/profile.component';
import { FridgeComponent } from './componets/fridge/fridge.component';
import { MealsComponent } from './componets/meals/meals.component';
import { RecipesComponent } from './componets/recipes/recipes.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'fridge', component: FridgeComponent },
  { path: 'meals', component: MealsComponent },
  { path: 'recipes', component: RecipesComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: '/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
