import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';

import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';

import { EmployeeModule } from './features/modules/employee-module/employee-module';
import { EmployeeDashboard } from './features/employees/employee-dashboard/employee-dashboard';
import { EmployeeList } from './features/employees/employee-list/employee-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form'; // ✅ ADD THIS

export const routes: Routes = [

  // HOME
  { path: '', component: Home },

  // LOGIN
  { path: 'login/:module', component: Login },

  // PET SERVICES MODULE
  { path: 'pet-services-module', component: PetServicesModule },
  { path: 'grooming-dashboard', component: GroomingDashboard },
  { path: 'vaccination-dashboard', component: VaccinationDashboard },
  { path: 'grooming/list', component: GroomingList },
  { path: 'grooming/form', component: GroomingForm },

  // EMPLOYEE MODULE
  { path: 'employee-module', component: EmployeeModule },
  { path: 'employee-dashboard', component: EmployeeDashboard },
  { path: 'employee/list', component: EmployeeList },
  { path: 'employee/form', component: EmployeeForm }, // ✅ IMPORTANT

];