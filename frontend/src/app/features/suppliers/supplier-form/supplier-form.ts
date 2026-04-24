// This file holds the Angular logic for supplier form.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplierService } from '../../../core/services/supplier';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-supplier-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './supplier-form.html'
})
export class SupplierForm {
  supplierService = inject(SupplierService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);
  addressService = inject(AddressService);

  supplierId: number | null = null;
  isLoading = true;
  addresses: any[] = [];

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    contactPerson: new FormControl('', [Validators.required, Validators.minLength(3)]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{3}-[0-9]{3}-[0-9]{4}$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    addressId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
  });

  ngOnInit() {
    this.loadAddresses();

    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.supplierId = Number(params['id']);

        this.supplierService.getById(this.supplierId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              name: data.name,
              contactPerson: data.contactPerson,
              phoneNumber: data.phoneNumber,
              email: data.email,
              addressId: data.addressId
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Supplier not found');
            this.router.navigate(['/supplier-dashboard']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  private loadAddresses() {
    this.addressService.getAll().subscribe({
      next: (res: any) => {
        this.addresses = res?.data || [];
      },
      error: () => {
        this.addresses = [];
        alert('Unable to load addresses for selection');
      }
    });
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();

      const errors: string[] = [];
      const name = this.form.get('name');
      const contactPerson = this.form.get('contactPerson');
      const phoneNumber = this.form.get('phoneNumber');
      const email = this.form.get('email');
      const addressId = this.form.get('addressId');

      if (name?.errors) {
        if (name.errors['required']) errors.push('Name is required');
        if (name.errors['minlength']) errors.push('Name must be at least 3 characters');
      }
      if (contactPerson?.errors) {
        if (contactPerson.errors['required']) errors.push('Contact person is required');
        if (contactPerson.errors['minlength']) errors.push('Contact person must be at least 3 characters');
      }
      if (phoneNumber?.errors) {
        if (phoneNumber.errors['required']) errors.push('Phone number is required');
        if (phoneNumber.errors['pattern']) errors.push('Phone number must be in 123-456-7890 format');
      }
      if (email?.errors) {
        if (email.errors['required']) errors.push('Email is required');
        if (email.errors['email']) errors.push('Email must be valid');
      }
      if (addressId?.errors) {
        if (addressId.errors['required']) errors.push('Address ID is required');
        if (addressId.errors['min']) errors.push('Address ID must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = this.form.value;

    if (this.supplierId) {
      this.supplierService.update(this.supplierId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/supplier/list']);
        },
        error: () => alert('Update failed')
      });
    } else {
      this.supplierService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/supplier/list']);
        },
        error: () => alert('Create failed')
      });
    }
  }
}
