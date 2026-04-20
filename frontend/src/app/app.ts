import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";
import { Home } from "./features/home/home";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Sidebar, Dashboard, Home],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
