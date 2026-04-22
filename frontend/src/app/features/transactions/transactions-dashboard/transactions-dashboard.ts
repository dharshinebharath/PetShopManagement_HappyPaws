import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { transaction } from '../../../core/services/transaction';


@Component({
  selector: 'app-transactions-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './transactions-dashboard.html'
})
export class TransactionDashboard {

  router = inject(Router);
  transactionService = inject(transaction);

  // ================= GET ALL =================
  goToList() {
    this.router.navigate(['/transactions/list']);
  }

  // ================= GET BY ID =================
  viewTransaction(id: string) {
    if (!id) {
      alert('Please enter Transaction ID');
      return;
    }

    this.transactionService.getById(Number(id)).subscribe({
      next: () => {
        // ✅ Navigate with ID → list will show single record
        this.router.navigate(['/transactions/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Transaction not found ❌');
        } else {
          alert('Something went wrong ⚠️');
        }
      }
    });
  }

  // ================= GET CUSTOMER TRANSACTIONS =================
  getCustomerTransactions(customerId: string) {
    if (!customerId) {
      alert('Enter Customer ID');
      return;
    }

    this.transactionService.getByCustomerId(Number(customerId)).subscribe({
      next: (res: any) => {
        console.log(res);
        alert('Check console for customer transactions');
      },
      error: () => alert('Failed to fetch transactions ❌')
    });
  }

  // ================= GET BY STATUS =================
  getByStatus(status: string) {
    if (!status) {
      alert('Enter Status');
      return;
    }

   this.transactionService.getByStatus(status).subscribe({
  next: () => {
    this.router.navigate(['/transactions/list'], {
      queryParams: { status }
    });
  },
  error: (err) => {
    if (err.status === 404) {
      alert('No transactions found for status ❌');
    } else {
      alert('Something went wrong ⚠️');
    }
  }
});}

  // ================= GET BY DATE RANGE =================
  getByDateRange(fromDate: string, toDate: string) {

  if (!fromDate || !toDate) {
    alert('Select both dates');
    return;
  }

  this.transactionService.getByDateRange(fromDate, toDate).subscribe({
    next: (res: any) => {

      console.log("Filtered Data:", res.data);

      // 👉 Pass data to list page
      this.router.navigate(['/transactions/list'], {
        state: { transactions: res.data }
      });
    },
    error: () => {
      alert('Failed to fetch date range ❌');
    }
  });
  }}