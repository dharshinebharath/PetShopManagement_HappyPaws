import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
<<<<<<< HEAD

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template:'<router-outlet></router-outlet>' 
    
=======
import { Sidebar } from "./shared/components/sidebar/sidebar";
import { Dashboard } from "./features/dashboard/dashboard";
import { Home } from "./features/home/home";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Sidebar, Dashboard, Home],
  templateUrl: './app.html',
  styleUrl: './app.css'
>>>>>>> b6ae5c2dade2e3b92eba78d3054823df8a3ae120
})
export class App {

  
}