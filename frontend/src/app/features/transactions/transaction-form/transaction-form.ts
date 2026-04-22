import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { transaction } from '../../../core/services/transaction';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transaction-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './transaction-form.html'
})
export class TransactionForm {

  transactionService = inject(transaction);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  transactionId: number | null = null;

  formData: any = {
    amount: 0,
    transactionDate: '',
    transactionStatus: '',
    customerId: null,
    petId: null
  };

  isLoading = true;

  // ================= LOAD BY ID =================
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.transactionId = Number(params['id']);

        this.transactionService.getById(this.transactionId).subscribe({
          next: (res: any) => {

            const data = res.data;

            this.formData = {
              amount: data.amount,
              transactionDate: data.transactionDate,
              transactionStatus: data.transactionStatus,
              customerId: data.customerId,
              petId: data.petId
            };

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Transaction not found ❌');
            this.router.navigate(['/transactions']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  // ================= CREATE ONLY =================
  submit() {

    console.log('FORM DATA:', this.formData);

    if (!this.formData.amount || !this.formData.transactionStatus) {
      alert('Please fill required fields ⚠️');
      return;
    }

    const payload = {
      amount: Number(this.formData.amount),
      transactionDate: this.formData.transactionDate,
      transactionStatus: this.formData.transactionStatus,
      customerId: this.formData.customerId,
      petId: this.formData.petId
    };

    this.transactionService.create(payload).subscribe({
      next: () => {
        alert('Created successfully ✅');
        this.router.navigate(['/transactions/list']);
      },
      error: () => alert('Create failed ❌')
    });
  }
}