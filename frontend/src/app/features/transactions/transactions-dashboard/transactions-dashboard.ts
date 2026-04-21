import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transactions-dashboard',
  imports: [],
  templateUrl: './transactions-dashboard.html',
  styleUrl: './transactions-dashboard.css',
})
export class TransactionsDashboard {
getByDateRange(arg0: string,arg1: string) {
throw new Error('Method not implemented.');
}
getByStatus(arg0: string) {
throw new Error('Method not implemented.');
}
getCustomerTransactions(arg0: string) {
throw new Error('Method not implemented.');
}
viewTransaction(arg0: string) {
throw new Error('Method not implemented.');
}
   constructor(private router: Router) {}

}
