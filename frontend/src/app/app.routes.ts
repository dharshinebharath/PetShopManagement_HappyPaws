import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { EmployeesDashboard } from './features/employees/employees-dashboard/employees-dashboard';
import { CustomertransactionModule } from './features/modules/customertransaction-module/customertransaction-module';
import { CustomerList } from './features/customers/customer-list/customer-list';
import { CustomerForm } from './features/customers/customer-form/customer-form';
import { TransactionsDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';


export const routes: Routes = [

  // 🔹 HOME
  { path: '', component: Home },

  // 🔹 LOGIN
  { path: 'login/:module', component: Login },

{ path: 'pet-services-module', component: PetServicesModule },

{ path: 'grooming-dashboard', component: GroomingDashboard },
{ path: 'vaccination-dashboard', component: VaccinationDashboard },
{path:'customertransaction-module',component:CustomertransactionModule},
{ path: 'customers-dashboard', component: CustomersDashboard },
  { path: 'customer/list', component: CustomerList },
  { path: 'customer/add', component: CustomerForm },
  { path: 'customer/edit/:id', component: CustomerForm },
  {path:'transactions-dashboard',component:TransactionsDashboard}

];