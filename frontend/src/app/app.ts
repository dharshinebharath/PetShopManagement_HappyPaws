import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";

import { CustomersDashboard } from "./features/customers/customers-dashboard/customers-dashboard";
import { Home } from './features/home/home';





@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.html'

import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";
import { Home } from "./features/home/home";
import { Login } from './features/auth/login/login';
import { EmployeeModule } from './features/modules/employee-module/employee-module';


import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',

  imports: [RouterOutlet, Sidebar, Dashboard, Home, CustomersDashboard],

  imports: [RouterOutlet, Sidebar, Dashboard, Home,Login],

  templateUrl: './app.html',
  styleUrl: './app.css'

})
export class App {}