// This file holds the Angular logic for transactions dashboard.
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

  // Go to transaction list.
  goToList() {
    this.router.navigate(['/transactions/list']);
  }

  // View a transaction.
  viewTransaction(id: string) {
    if (!id) {
      alert('Please enter Transaction ID');
      return;
    }

    this.transactionService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/transactions/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Get transactions by customer ID.
  getCustomerTransactions(customerId: string) {
    if (!customerId) {
      alert('Enter Customer ID');
      return;
    }

    this.transactionService.getByCustomerId(Number(customerId)).subscribe({
      next: () => {
        this.router.navigate(['/transactions/list'], {
          queryParams: { customerId }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Get transactions by status.
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
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Get transactions by date range.
  getByDateRange(fromDate: string, toDate: string) {
    if (!fromDate || !toDate) {
      alert('Select both dates');
      return;
    }

    this.transactionService.getByDateRange(fromDate, toDate).subscribe({
      next: (res: any) => {
        const records = Array.isArray(res) ? res : res.data ?? [];
        if (records.length === 0) {
          alert('No transactions found for the specified date range');
        } else {
          this.router.navigate(['/transactions/list'], {
            queryParams: { fromDate, toDate }
          });
        }
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }
}
