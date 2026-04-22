import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { EmployeesDashboard } from './features/employees/employees-dashboard/employees-dashboard';
import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';
import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';


import { PetGroomingDashboard } from './features/pet-mapping/pet-grooming-dashboard/pet-grooming-dashboard';
import { PetGroomingList } from './features/pet-mapping/pet-grooming-list/pet-grooming-list';
import { PetVaccinationDashboard } from './features/pet-mapping/pet-vaccination-dashboard/pet-vaccination-dashboard';
import { PetVaccinationList } from './features/pet-mapping/pet-vaccination-list/pet-vaccination-list';


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
{
  path: 'vaccination',
  component: VaccinationDashboard
},
{
  path: 'vaccination/list',
  component: VaccinationList
},
{
  path: 'vaccination/form',
  component: VaccinationForm
},
{ path: 'pet-mapping/grooming', component: PetGroomingDashboard },
{ path: 'pet-mapping/grooming/list', component: PetGroomingList },

{ path: 'pet-mapping/vaccination', component: PetVaccinationDashboard },
{ path: 'pet-mapping/vaccination/list', component: PetVaccinationList },

];