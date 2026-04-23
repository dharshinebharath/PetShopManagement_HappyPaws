import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { transaction } from '../../../core/services/transaction';

@Component({
  selector: 'app-transaction-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './transaction-form.html'
})
export class TransactionForm {
  transactionService = inject(transaction);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  transactionId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    amount: new FormControl(0, [Validators.required, Validators.min(1)]),
    transactionDate: new FormControl('', [Validators.required]),
    transactionStatus: new FormControl('', [Validators.required]),
    customerId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)]),
    petId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
  });

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.transactionId = Number(params['id']);

        this.transactionService.getById(this.transactionId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              amount: data.amount,
              transactionDate: data.transactionDate,
              transactionStatus: data.transactionStatus,
              customerId: data.customerId,
              petId: data.petId
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Transaction not found');
            this.router.navigate(['/transactions']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();

      const errors: string[] = [];
      const amount = this.form.get('amount');
      const transactionDate = this.form.get('transactionDate');
      const transactionStatus = this.form.get('transactionStatus');
      const customerId = this.form.get('customerId');
      const petId = this.form.get('petId');

      if (amount?.errors) {
        if (amount.errors['required']) errors.push('Amount is required');
        if (amount.errors['min']) errors.push('Amount must be greater than 0');
      }
      if (transactionDate?.errors) errors.push('Transaction date is required');
      if (transactionStatus?.errors) errors.push('Transaction status is required');
      if (customerId?.errors) {
        if (customerId.errors['required']) errors.push('Customer ID is required');
        if (customerId.errors['min']) errors.push('Customer ID must be greater than 0');
      }
      if (petId?.errors) {
        if (petId.errors['required']) errors.push('Pet ID is required');
        if (petId.errors['min']) errors.push('Pet ID must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const transactionDate = this.form.value.transactionDate || '';
    if (transactionDate) {
      const selectedDate = new Date(transactionDate);
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      if (selectedDate > today) {
        alert('Transaction date cannot be in the future');
        return;
      }
    }

    const allowedStatus = ['SUCCESS', 'FAILED', 'PENDING'];
    if (!allowedStatus.includes(this.form.value.transactionStatus || '')) {
      alert('Invalid transaction status');
      return;
    }

    const payload = {
      amount: Number(this.form.value.amount),
      transactionDate: this.form.value.transactionDate,
      transactionStatus: this.form.value.transactionStatus,
      customerId: this.form.value.customerId,
      petId: this.form.value.petId
    };
    if (this.transactionId) {
      this.transactionService.update(this.transactionId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/transactions/list']);
        },
        error: () => alert('Update failed')
      });
    } else {
      this.transactionService.create(payload).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/transactions/list']);
        },
        error: () => alert('Create failed')
      });
    }
  }
}
