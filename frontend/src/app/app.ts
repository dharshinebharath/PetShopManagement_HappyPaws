import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Dashboard } from './features/dashboard/dashboard';
import { Home } from './features/home/home';
import { Sidebar } from './shared/components/sidebar/sidebar';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Sidebar, Dashboard, Home],
  templateUrl: './app.html'
})
export class App {}