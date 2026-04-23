import { Routes } from '@angular/router';

import { Home } from './features/home/home';
import { Login } from './features/auth/login/login';

import { PetsModule } from './features/modules/pets-module/pets-module';
import { PetServicesModule } from './features/modules/pet-services-module/pet-services-module';
import { CustomertransactionModule } from './features/modules/customertransaction-module/customertransaction-module';
import { EmployeeModule } from './features/modules/employee-module/employee-module';

import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';
import { PetsList } from './features/pets/pet-list/pet-list';
import { PetForm } from './features/pets/pet-form/pet-form';

import { CategoryDashboard } from './features/categories/category-dashboard/category-dashboard';
import { CategoryList } from './features/categories/category-list/category-list';
import { CategoryForm } from './features/categories/category-form/category-form';

import { GroomingDashboard } from './features/grooming/grooming-dashboard/grooming-dashboard';
import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';

import { VaccinationDashboard } from './features/vaccinations/vaccination-dashboard/vaccination-dashboard';
import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';
import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';

import { PetGroomingDashboard } from './features/pet-mapping/pet-grooming-dashboard/pet-grooming-dashboard';
import { PetGroomingList } from './features/pet-mapping/pet-grooming-list/pet-grooming-list';
import { PetFoodDashboard } from './features/pet-mapping/pet-food-dashboard/pet-food-dashboard';
import { PetFoodList } from './features/pet-mapping/pet-food-list/pet-food-list';
import { PetSupplierDashboard } from './features/pet-mapping/pet-supplier-dashboard/pet-supplier-dashboard';
import { PetSupplierList } from './features/pet-mapping/pet-supplier-list/pet-supplier-list';
import { PetVaccinationDashboard } from './features/pet-mapping/pet-vaccination-dashboard/pet-vaccination-dashboard';
import { PetVaccinationList } from './features/pet-mapping/pet-vaccination-list/pet-vaccination-list';
import { EmployeePetList } from './features/pet-mapping/employees-pet-list/employees-pet-list';
import { EmployeePetDashboard } from './features/pet-mapping/employees-pet-dashboard/employees-pet-dashboard';
import { PetEmployeesList } from './features/pet-mapping/pet-employees-list/pet-employees-list';
import { EmployeeReportsDashboard } from './features/pet-mapping/employee-reports-dashboard/employee-reports-dashboard';
import { EmployeeReportsList } from './features/pet-mapping/employee-reports-list/employee-reports-list';
import { PetsFilterDashboard } from './features/pet-mapping/pets-filter-dashboard/pets-filter-dashboard';
import { PetsFilterList } from './features/pet-mapping/pets-filter-list/pets-filter-list';

import { CustomersDashboard } from './features/customers/customers-dashboard/customers-dashboard';
import { CustomerList } from './features/customers/customer-list/customer-list';
import { CustomerForm } from './features/customers/customer-form/customer-form';

import { TransactionDashboard } from './features/transactions/transactions-dashboard/transactions-dashboard';
import { TransactionList } from './features/transactions/transaction-list/transaction-list';
import { TransactionForm } from './features/transactions/transaction-form/transaction-form';

import { AddressList } from './features/address/address-list/address-list';
import { AddressForm } from './features/address/address-form/address-form';

import { EmployeeDashboard } from './features/employees/employee-dashboard/employee-dashboard';
import { EmployeeList } from './features/employees/employee-list/employee-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form';
import { Assignment } from './features/employees/assignment/assignment';

import { FoodDashboard } from './features/food/food-dashboard/food-dashboard';
import { FoodList } from './features/food/food-list/food-list';
import { FoodForm } from './features/food/food-form/food-form';
import { SupplierDashboard } from './features/suppliers/supplier-dashboard/supplier-dashboard';
import { SupplierList } from './features/suppliers/supplier-list/supplier-list';
import { SupplierForm } from './features/suppliers/supplier-form/supplier-form';

import { InventoryModule } from './features/modules/inventory-module/inventory-module';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:module', component: Login },

  // Module landing pages
  { path: 'pets-module', component: PetsModule },
  { path: 'pet-services-module', component: PetServicesModule },
  { path: 'customers-module', component: CustomertransactionModule },
  { path: 'customertransaction-module', component: CustomertransactionModule },
  { path: 'employee-module', component: EmployeeModule },
  { path: 'inventory-module', component: InventoryModule },

  // Dashboard paths
  { path: 'pets-dashboard', component: PetsDashboard },
  { path: 'category-dashboard', component: CategoryDashboard },
  { path: 'grooming-dashboard', component: GroomingDashboard },
  { path: 'vaccination-dashboard', component: VaccinationDashboard },
  { path: 'vaccination', component: VaccinationDashboard },
  { path: 'customers-dashboard', component: CustomersDashboard },
  { path: 'transactions-dashboard', component: TransactionDashboard },
  { path: 'food-dashboard', component: FoodDashboard },
  { path: 'supplier-dashboard', component: SupplierDashboard },
  { path: 'employee-dashboard', component: EmployeeDashboard },
  { path: 'pets-filter-dashboard', component: PetsFilterDashboard },
  { path: 'pets-filter/list', component: PetsFilterList },

  // Pets
  { path: 'pets/list', component: PetsList },
  { path: 'pets/form', component: PetForm },

  // Categories
  { path: 'category/list', component: CategoryList },
  { path: 'category/form', component: CategoryForm },

  // Grooming
  { path: 'grooming/list', component: GroomingList },
  { path: 'grooming/form', component: GroomingForm },

  // Vaccinations
  { path: 'vaccination/list', component: VaccinationList },
  { path: 'vaccination/form', component: VaccinationForm },

  // Pet mapping
  { path: 'pet-mapping/grooming', component: PetGroomingDashboard },
  { path: 'pet-mapping/grooming/list', component: PetGroomingList },
  { path: 'pet-mapping/food', component: PetFoodDashboard },
  { path: 'pet-mapping/food/list', component: PetFoodList },
  { path: 'pet-mapping/suppliers', component: PetSupplierDashboard },
  { path: 'pet-mapping/suppliers/list', component: PetSupplierList },
  { path: 'pet-mapping/vaccination', component: PetVaccinationDashboard },
  { path: 'pet-mapping/vaccination/list', component: PetVaccinationList },
  { path: 'employee-pet-mapping', component: EmployeePetDashboard },
  { path: 'employee-pet-mapping/list', component: EmployeePetList },
  { path: 'employee-pet-mapping/employees-list', component: PetEmployeesList },
  { path: 'employee-reports', component: EmployeeReportsDashboard },
  { path: 'employee-reports/list', component: EmployeeReportsList },

  // Customers
  { path: 'customer/list', component: CustomerList },
  { path: 'customer/form', component: CustomerForm },
  { path: 'customer/add', component: CustomerForm },
  { path: 'customer/edit/:id', component: CustomerForm },

  // Transactions
  { path: 'transactions/list', component: TransactionList },
  { path: 'transactions/form', component: TransactionForm },
  { path: 'transactions/add', component: TransactionForm },

  // Address
  { path: 'address/list', component: AddressList },
  { path: 'address/form', component: AddressForm },
  { path: 'address/add', component: AddressForm },
  { path: 'address/edit/:id', component: AddressForm },

  // Inventory
  { path: 'food/list', component: FoodList },
  { path: 'food/form', component: FoodForm },
  { path: 'supplier/list', component: SupplierList },
  { path: 'supplier/form', component: SupplierForm },

  // Employees
  { path: 'employee/list', component: EmployeeList },
  { path: 'employee/form', component: EmployeeForm },
  { path: 'employee/assignment', component: Assignment },

  { path: '**', redirectTo: '' }
];
