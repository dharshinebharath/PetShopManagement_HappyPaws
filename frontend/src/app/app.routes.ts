import { Routes } from '@angular/router';
<<<<<<< HEAD
import { InventoryComponent } from './features/inventory/inventory';
import { FoodListComponent } from './features/inventory/food-list/food-list';
import { FoodFormComponent} from './features/inventory/food-form/food-form';
=======

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { EmployeesDashboard } from './features/employees/employees-dashboard/employees-dashboard';


>>>>>>> b6ae5c2dade2e3b92eba78d3054823df8a3ae120
export const routes: Routes = [
  { path: 'inventory', component: InventoryComponent },

<<<<<<< HEAD
  { path: 'inventory/food/list', component: FoodListComponent },

  { path: 'inventory/food/form', component: FoodFormComponent },

  { path: '', redirectTo: 'inventory', pathMatch: 'full' }
];


=======
  // 🔹 HOME
  { path: '', component: Home },

  // 🔹 LOGIN
  { path: 'login/:module', component: Login },

{ path: 'pet-services-module', component: PetServicesModule },

{ path: 'grooming-dashboard', component: GroomingDashboard },
{ path: 'vaccination-dashboard', component: VaccinationDashboard },
 

];
>>>>>>> b6ae5c2dade2e3b92eba78d3054823df8a3ae120
