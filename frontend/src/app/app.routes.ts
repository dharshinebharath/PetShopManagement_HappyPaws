import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';
import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';
import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';
import { PetsModule } from './features/modules/pets-module/pets-module';
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { PetsList } from './features/pets/pet-list/pet-list';
import { PetForm } from './features/pets/pet-form/pet-form';
import { CategoryDashboard } from './features/categories/category-dashboard/category-dashboard';
import { CategoryList } from './features/categories/category-list/category-list';
import { CategoryForm } from './features/categories/category-form/category-form';
import { PetGroomingDashboard } from './features/pet-mapping/pet-grooming-dashboard/pet-grooming-dashboard';
import { PetGroomingList } from './features/pet-mapping/pet-grooming-list/pet-grooming-list';
import { PetVaccinationDashboard } from './features/pet-mapping/pet-vaccination-dashboard/pet-vaccination-dashboard';
import { PetVaccinationList } from './features/pet-mapping/pet-vaccination-list/pet-vaccination-list';
import { CustomertransactionModule } from './features/modules/customertransaction-module/customertransaction-module';
import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { CustomerList } from './features/customers/customer-list/customer-list';
import { CustomerForm } from './features/customers/customer-form/customer-form';
import { AddressList } from './features/address/address-list/address-list';
import { AddressForm } from './features/address/address-form/address-form';
import { TransactionList } from './features/transactions/transaction-list/transaction-list';
import { TransactionDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';
import { TransactionForm } from './features/transactions/transaction-form/transaction-form';
import { EmployeeModule } from './features/modules/employee-module/employee-module';
import { EmployeeDashboard } from './features/employees/employee-dashboard/employee-dashboard';
import { EmployeeList } from './features/employees/employee-list/employee-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:module', component: Login },

  { path: 'pet-services-module', component: PetServicesModule },
  { path: 'grooming-dashboard', component: GroomingDashboard },
  { path: 'grooming/list', component: GroomingList },
  { path: 'grooming/form', component: GroomingForm },
  { path: 'vaccination-dashboard', component: VaccinationDashboard },
  { path: 'vaccination', component: VaccinationDashboard },
  { path: 'vaccination/list', component: VaccinationList },
  { path: 'vaccination/form', component: VaccinationForm },

  { path: 'pets-module', component: PetsModule },
  { path: 'pets-dashboard', component: PetsDashboard },
  { path: 'pets/list', component: PetsList },
  { path: 'pets/form', component: PetForm },
  { path: 'category-dashboard', component: CategoryDashboard },
  { path: 'category/list', component: CategoryList },
  { path: 'category/form', component: CategoryForm },

  { path: 'pet-mapping/grooming', component: PetGroomingDashboard },
  { path: 'pet-mapping/grooming/list', component: PetGroomingList },
  { path: 'pet-mapping/vaccination', component: PetVaccinationDashboard },
  { path: 'pet-mapping/vaccination/list', component: PetVaccinationList },

  { path: 'customertransaction-module', component: CustomertransactionModule },
  { path: 'customers-dashboard', component: CustomersDashboard },
  { path: 'customer/list', component: CustomerList },
  { path: 'customer/add', component: CustomerForm },
  { path: 'customer/edit/:id', component: CustomerForm },
  { path: 'transactions-dashboard', component: TransactionDashboard },
  { path: 'transactions/list', component: TransactionList },
  { path: 'transactions/add', component: TransactionForm },
  { path: 'address/add', component: AddressForm },
  { path: 'address/list', component: AddressList },
  { path: 'address/edit/:id', component: AddressForm },

  { path: 'employee-module', component: EmployeeModule },
  { path: 'employee-dashboard', component: EmployeeDashboard },
  { path: 'employee/list', component: EmployeeList },
  { path: 'employee/form', component: EmployeeForm },
];
