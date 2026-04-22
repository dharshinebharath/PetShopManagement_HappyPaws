import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { EmployeesDashboard } from './features/employees/employees-dashboard/employees-dashboard';
import { InventoryModule } from './features/modules/inventory-module/inventory-module';
import { FoodDashboard } from './features/food/food-dashboard/food-dashboard';
import { SupplierDashboard } from './features/supplier/supplier-dashboard/supplier-dashboard';

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { FoodList } from './features/food/food-list/food-list';
import { FoodForm } from './features/food/food-form/food-form';
import { SupplierForm } from './features/supplier/supplier-form/supplier-form';
import { SupplierList } from './features/supplier/supplier-list/supplier-list';



export const routes: Routes = [

  // 🔹 HOME
  { path: '', component: Home },

  // 🔹 LOGIN
  { path: 'login/:module', component: Login },


{ path: 'pet-services-module', component: PetServicesModule },

{ path: 'grooming-dashboard', component: GroomingDashboard },
{ path: 'vaccination-dashboard', component: VaccinationDashboard },
 
{ path: 'grooming/list', component: GroomingList },

{path: 'grooming/form', component:GroomingForm},
{ path: 'food/list', component: FoodList },

{path: 'food/form', component:FoodForm},
{ path: 'supplier/list', component: SupplierList },

{path: 'supplier/form', component:SupplierForm},


{ path: 'pet-services-module', component: PetServicesModule },

{ path: 'grooming-dashboard', component: GroomingDashboard },
{ path: 'vaccination-dashboard', component: VaccinationDashboard },

  { path: 'inventory-module', component: InventoryModule },
  { path: 'food-dashboard', component: FoodDashboard },
  { path: 'supplier-dashboard', component: SupplierDashboard },
  




];