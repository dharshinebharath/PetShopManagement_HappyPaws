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
export class Home {

  router: Router = inject(Router);

  goToLogin(module: string) {
    console.log("Clicked:", module);
    this.router.navigate(['/login', module]);
  }
}

