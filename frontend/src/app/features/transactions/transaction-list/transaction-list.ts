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
      const from = params['from'];
      const to = params['to'];

      // ================= GET BY ID =================
      if (id) {
        this.transactionService.getById(Number(id)).subscribe({
          next: (res: any) => {
            if (!res || !res.data) {
              alert('Transaction not found ❌');
              this.router.navigate(['/transactions']);
              return;
            }

        this.transactionList = [res.data || res];
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Transaction ID not found ❌');
            this.router.navigate(['/transactions']);
          }
        });
      }

      // ================= GET BY STATUS =================
      else if (status) {
        this.transactionService.getByStatus(status.toUpperCase()).subscribe({
          next: (res: any) => {
            this.transactionList = res.data;
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch by status ❌')
        });
      }

      // ================= GET BY CUSTOMER =================
      else if (customerId) {
        this.transactionService.getByCustomerId(Number(customerId)).subscribe({
          next: (res: any) => {
            this.transactionList = res.data;
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch customer transactions ❌')
        });
      }

      // ================= GET BY DATE RANGE =================
      else if (from && to) {
        this.transactionService.getByDateRange(from, to).subscribe({
          next: (res: any) => {
            this.transactionList = res.data;
            this.cdr.detectChanges();
          },
          error: () => alert('Failed to fetch by date ❌')
        });
      }

      // ================= DEFAULT (GET ALL) =================
      else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.transactionService.getAll().subscribe({
  next: (res: any) => {
    console.log(res);  // 👈 paste here
    this.transactionList = res.data || res;
    this.cdr.detectChanges();
  },
  error: () => alert('Failed to load transactions ❌')
});
  }}