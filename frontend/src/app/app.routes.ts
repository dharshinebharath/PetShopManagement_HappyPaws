import { Routes } from '@angular/router';




import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';


import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { PetsModule } from './features/modules/pets-module/pets-module';
import { CategoryDashboard } from './features/categories/category-dashboard/category-dashboard';
import { PetsList } from './features/pets/pet-list/pet-list';
import { PetForm } from './features/pets/pet-form/pet-form';
import { CategoryList } from './features/categories/category-list/category-list';
import { CategoryForm } from './features/categories/category-form/category-form';



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


{ path: 'pets-module', component: PetsModule },
{ path: 'pets-dashboard', component: PetsDashboard },
{ path: 'category-dashboard', component: CategoryDashboard },

{ path: 'pets/list', component: PetsList },

{path: 'pets/form', component:PetForm},

{ path: 'category/list', component: CategoryList },

{path: 'category/form', component:CategoryForm},



];