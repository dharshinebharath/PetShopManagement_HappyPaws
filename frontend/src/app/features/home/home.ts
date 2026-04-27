// This file holds the Angular logic for home.
import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
// Home component
export class Home {
  // Injecting required services
  router: Router = inject(Router);

  // Navigate to login page
  goToLogin(module: string) {
    console.log("Clicked:", module);
    this.router.navigate(['/login', module]);
  }
}

