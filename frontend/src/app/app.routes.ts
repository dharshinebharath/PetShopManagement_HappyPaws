import { Routes } from '@angular/router';
import { Dashboard } from './features/dashboard/dashboard';
import { PetList } from './features/pets/pet-list/pet-list';
import { CategoryList } from './features/categories/category-list/category-list';
import { CustomerList } from './features/customers/customer-list/customer-list';
import { TransactionList } from './features/transactions/transaction-list/transaction-list';
import { Component } from '@angular/core';
import { Grooming } from './core/services/grooming'

import { GroomingList } from './features/grooming/grooming-list/grooming-list';
import { VaccinationList } from './features/vaccinations/vaccination-list/vaccination-list';

import { Assignment } from './features/assignment/assignment';
import { SupplierList } from './features/inventory/supplier-list/supplier-list';
import { FoodList } from './features/inventory/food-list/food-list';
import { EmployeeForm } from './features/employees/employee-form/employee-form';
import { EmployeeList } from './features/employees/employee-list/employee-list';

import { PetForm } from './features/pets/pet-form/pet-form';
import { CustomerForm } from './features/customers/customer-form/customer-form';
import { CategoryForm } from './features/categories/category-form/category-form';


export const routes: Routes = [


import { VaccinationForm } from './features/vaccinations/vaccination-form/vaccination-form';
import { PetForm } from './features/pets/pet-form/pet-form';
import { CustomerForm } from './features/customers/customer-form/customer-form';
import { CategoryForm } from './features/categories/category-form/category-form';
import { GroomingForm } from './features/grooming/grooming-form/grooming-form';

export const routes: Routes = [

    {path:'', component:Dashboard},
    {path:'pets',component:PetList},

  { path: 'pets/add', component: PetForm },
  { path: 'pets/edit/:id', component: PetForm },
      { path: 'categories', component: CategoryList },
        { path: 'categories/add', component: CategoryForm },

      { path: 'customers', component: CustomerList },
        { path: 'customers/add', component: CustomerForm },

    {path:'transactions', component:TransactionList},
    {path:'grooming', component:GroomingList},
      { path: 'grooming/add', component: GroomingForm},

    {path:'vaccinations', component:VaccinationList},
    { path: 'vaccinations/add', component: VaccinationForm},

  // Employees
  { path: 'employees', component: EmployeeList },
  { path: 'employees/add', component: EmployeeForm},

  // Inventory
  { path: 'food', component: FoodList },
  { path: 'suppliers', component: SupplierList },

  // Assignment
  { path: 'assignment', component: Assignment },

  { path: '**', redirectTo: '' }


];

