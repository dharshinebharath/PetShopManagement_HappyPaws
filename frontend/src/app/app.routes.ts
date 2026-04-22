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
// import { TransactionsDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { AddressList } from './features/address/address-list/address-list';
import { AddressForm } from './features/address/address-form/address-form';
import { TransactionList } from './features/transactions/transaction-list/transaction-list';
import { TransactionDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';
import { TransactionForm } from './features/transactions/transaction-form/transaction-form';



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


];