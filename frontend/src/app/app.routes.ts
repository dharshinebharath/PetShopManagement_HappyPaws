import { Routes } from '@angular/router';


import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';

import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';

import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { EmployeesDashboard } from './features/employees/employees-dashboard/employees-dashboard';
import { CustomertransactionModule } from './features/modules/customertransaction-module/customertransaction-module';
import { CustomerList } from './features/customers/customer-list/customer-list';
import { CustomerForm } from './features/customers/customer-form/customer-form';
// import { TransactionsDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { AddressList } from './features/address/address-list/address-list';
import { AddressForm } from './features/address/address-form/address-form';
import { TransactionList } from './features/transactions/transaction-list/transaction-list';
import { TransactionDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';
import { TransactionForm } from './features/transactions/transaction-form/transaction-form';

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';


import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';
import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';



import { PetGroomingDashboard } from './features/pet-mapping/pet-grooming-dashboard/pet-grooming-dashboard';
import { PetGroomingList } from './features/pet-mapping/pet-grooming-list/pet-grooming-list';
import { PetVaccinationDashboard } from './features/pet-mapping/pet-vaccination-dashboard/pet-vaccination-dashboard';
import { PetVaccinationList } from './features/pet-mapping/pet-vaccination-list/pet-vaccination-list';


import { EmployeeModule } from './features/modules/employee-module/employee-module';
import { EmployeeDashboard } from './features/employees/employee-dashboard/employee-dashboard';
import { EmployeeList } from './features/employees/employee-list/employee-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form'; // ✅ ADD THIS

export const routes: Routes = [

  // HOME
  { path: '', component: Home },

  // LOGIN

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



{ path: 'pet-services-module', component: PetServicesModule },

{ path: 'grooming-dashboard', component: GroomingDashboard },
{ path: 'vaccination-dashboard', component: VaccinationDashboard },

{path:'customertransaction-module',component:CustomertransactionModule},
{ path: 'customers-dashboard', component: CustomersDashboard },
  { path: 'customer/list', component: CustomerList },
  { path: 'customer/add', component: CustomerForm },
  { path: 'customer/edit/:id', component: CustomerForm },
  {path:'transactions-dashboard',component:TransactionDashboard},
 { path: 'address/add', component: AddressForm }   ,
{ path: 'address/list', component: AddressList },
{ path: 'address/edit/:id', component: AddressForm },
{path: 'transactions/list',component: TransactionList},
{path: 'transactions/add',component:TransactionForm}


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