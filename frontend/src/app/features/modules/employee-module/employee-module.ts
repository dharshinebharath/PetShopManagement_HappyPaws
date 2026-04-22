import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-employee-module',
  imports: [RouterLink, RouterOutlet],
  templateUrl: './employee-module.html',
  styleUrls: ['./employee-module.css']
})
export class EmployeeModule {

  constructor(private router: Router) {}

  navigate(action: string) {
    this.router.navigate([`/employee/${action}`]);
  }
  goToDashboard() {
  this.router.navigate(['/employee-dashboard']);
}

}