import { Routes } from '@angular/router';

import { Dashboard } from './features/dashboard/dashboard';

import { PetList } from './features/pets/pet-list/pet-list';
import { PetForm } from './features/pets/pet-form/pet-form';

import { CategoryList } from './features/categories/category-list/category-list';
import { CategoryForm } from './features/categories/category-form/category-form';

import { CustomerList } from './features/customers/customer-list/customer-list';
import { CustomerForm } from './features/customers/customer-form/customer-form';

import { TransactionList } from './features/transactions/transaction-list/transaction-list';

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';

import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';
import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';

import { EmployeeList } from './features/employees/employee-list/employee-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form';

import { FoodList } from './features/inventory/food-list/food-list';
import { SupplierList } from './features/inventory/supplier-list/supplier-list';

import { Assignment } from './features/assignment/assignment';

export const routes: Routes = [
  { path: '', component: Dashboard },

  // Inventory
  { path: 'food', component: FoodList },
  { path: 'suppliers', component: SupplierList },

  // Pets
  { path: 'pets', component: PetList },
  { path: 'pets/add', component: PetForm },
  { path: 'pets/edit/:id', component: PetForm },

  // Categories
  { path: 'categories', component: CategoryList },
  { path: 'categories/add', component: CategoryForm },

  // Customers
  { path: 'customers', component: CustomerList },
  { path: 'customers/add', component: CustomerForm },

  // Transactions
  { path: 'transactions', component: TransactionList },

  // Grooming
  { path: 'grooming', component: GroomingList },
  { path: 'grooming/add', component: GroomingForm },

  // Vaccinations
  { path: 'vaccinations', component: VaccinationList },
  { path: 'vaccinations/add', component: VaccinationForm },

  // Employees
  { path: 'employees', component: EmployeeList },
  { path: 'employees/add', component: EmployeeForm },

  // Assignment
  { path: 'assignment', component: Assignment },

  { path: '**', redirectTo: '' }
];