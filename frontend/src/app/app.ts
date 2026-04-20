import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";
import { CustomersDashboard } from "./features/customers/customers-dashboard/customers-dashboard";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Sidebar, Dashboard, CustomersDashboard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
