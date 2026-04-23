import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { transaction } from '../../../core/services/transaction';

@Component({
  selector: 'app-transaction-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './transaction-list.html',
  styleUrl: './transaction-list.css',
})
export class TransactionList {
  transactionService = inject(transaction);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  transactionList: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      const status = params['status'];
      const customerId = params['customerId'];
      const from = params['fromDate'];
      const to = params['toDate'];

      if (id) {
        this.transactionService.getById(Number(id)).subscribe({
          next: (res: any) => {
            const record = Array.isArray(res) ? res[0] : res.data ?? res;

            if (!record) {
              alert('Transaction not found');
              this.router.navigate(['/transactions-dashboard']);
              return;
            }

            this.transactionList = [record];
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Transaction ID not found');
            this.router.navigate(['/transactions-dashboard']);
          }
        });
      } else if (status) {
        this.transactionService.getByStatus(status.toUpperCase()).subscribe({
          next: (res: any) => {
            this.transactionList = Array.isArray(res) ? res : res.data ?? [];
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch by status')
        });
      } else if (customerId) {
        this.transactionService.getByCustomerId(Number(customerId)).subscribe({
          next: (res: any) => {
            this.transactionList = Array.isArray(res) ? res : res.data ?? [];
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch customer transactions')
        });
      } else if (from && to) {
        this.transactionService.getByDateRange(from, to).subscribe({
          next: (res: any) => {
            this.transactionList = Array.isArray(res) ? res : res.data ?? [];
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch by date')
        });
      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.transactionService.getAll().subscribe({
      next: (res: any) => {
        this.transactionList = res.data || res;
        this.cdr.detectChanges();
      },
      error: () => alert('Failed to load transactions')
    });
  }
}
