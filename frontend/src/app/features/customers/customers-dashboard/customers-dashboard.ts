import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers-dashboard',
  imports: [],
  templateUrl: './customers-dashboard.html',
  styleUrl: './customers-dashboard.css',
})

export class CustomersDashboard {
  constructor(private router: Router) {}
deleteCustomer(arg0: string) {
throw new Error('Method not implemented.');
}
editCustomer(id: string) {
this.router.navigate(['/customer/edit', id]);
}
viewCustomer(arg0: string) {
throw new Error('Method not implemented.');
}
}
