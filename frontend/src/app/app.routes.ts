import { Routes } from '@angular/router';
import { InventoryComponent } from './features/inventory/inventory';

export const routes: Routes = [
  { path: '', redirectTo: 'inventory', pathMatch: 'full' },
  { path: 'inventory', component: InventoryComponent },
];