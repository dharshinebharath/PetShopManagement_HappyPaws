import { Routes } from '@angular/router';
import { InventoryComponent } from './features/inventory/inventory';
import { FoodListComponent } from './features/inventory/food-list/food-list';
import { FoodFormComponent} from './features/inventory/food-form/food-form';
export const routes: Routes = [
  { path: 'inventory', component: InventoryComponent },

  { path: 'inventory/food/list', component: FoodListComponent },

  { path: 'inventory/food/form', component: FoodFormComponent },

  { path: '', redirectTo: 'inventory', pathMatch: 'full' }
];


