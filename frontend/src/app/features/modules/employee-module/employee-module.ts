import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-employee-module',
  standalone:true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './employee-module.html',
  styleUrls: ['./employee-module.css']
})
export class EmployeeModule {

 
router = inject(Router);

  goToDashboard() {
    this.router.navigate(['/employee-dashboard']);
  }
}