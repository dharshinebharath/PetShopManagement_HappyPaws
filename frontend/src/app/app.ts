import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";
import { PetsDashboard } from './features/pets/pets-dashboard/pets-dashboard';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,PetsDashboard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
