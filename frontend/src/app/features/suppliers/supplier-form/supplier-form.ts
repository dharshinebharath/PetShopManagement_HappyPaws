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
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    contactPerson: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    addressId: new FormControl<number | null>(null, [Validators.required])
  });

  // Initialize the component.
  ngOnInit() {
    this.loadAddresses();

    // Get supplier ID from query params.
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
          error: (err) => {
            let msg = 'Supplier not found';
            if (err.error) {
              if (err.error.message) {
                msg = err.error.message;
              } else if (err.error.errors) {
                msg = err.error.errors.join('\n');
              } else if (typeof err.error === 'string') {
                msg = err.error;
              }
            }
            alert(msg);
            this.router.navigate(['/supplier-dashboard']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  // Load addresses.
  private loadAddresses() {
    this.addressService.getAll().subscribe({
      next: (res: any) => {
        this.addresses = res?.data || [];
      },
      error: (err) => {
        this.addresses = [];
        let msg = 'Unable to load addresses for selection';
        if (err.error) {
          if (err.error.message) {
            msg = err.error.message;
          } else if (err.error.errors) {
            msg = err.error.errors.join('\n');
          } else if (typeof err.error === 'string') {
            msg = err.error;
          }
        }
        alert(msg);
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

      // Check for validation errors.
      if (name?.errors) {
        if (name.errors['required']) errors.push('Supplier name cannot be empty');
        if (name.errors['minlength'] || name.errors['maxlength']) errors.push('Supplier name must be between 2 and 50 characters');
      }
      if (contactPerson?.errors) {
        if (contactPerson.errors['required']) errors.push('Contact person cannot be empty');
        if (contactPerson.errors['minlength'] || contactPerson.errors['maxlength']) errors.push('Contact person must be between 2 and 30 characters');
      }
      if (phoneNumber?.errors) {
        if (phoneNumber.errors['required']) errors.push('Phone number cannot be empty');
        if (phoneNumber.errors['pattern']) errors.push('Phone number must be 10 digits');
      }
      if (email?.errors) {
        if (email.errors['required']) errors.push('Email cannot be empty');
        if (email.errors['email']) errors.push('Invalid email address');
      }
      if (addressId?.errors) {
        if (addressId.errors['required']) errors.push('Address ID cannot be null');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = this.form.value;

    // Update an existing supplier.
    if (this.supplierId) {
      this.supplierService.update(this.supplierId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/supplier/list']);
        },
        error: (err) => {
          let msg = 'Update failed';
          if (err.error) {
            if (err.error.message) {
              msg = err.error.message;
            } else if (err.error.errors) {
              msg = err.error.errors.join('\n');
            } else if (typeof err.error === 'string') {
              msg = err.error;
            }
          }
          alert(msg);
        }
      });
    }
    // Create a new supplier.
    else {
      this.supplierService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/supplier/list']);
        },
        error: (err) => {
          let msg = 'Create failed';
          if (err.error) {
            if (err.error.message) {
              msg = err.error.message;
            } else if (err.error.errors) {
              msg = err.error.errors.join('\n');
            } else if (typeof err.error === 'string') {
              msg = err.error;
            }
          }
          alert(msg);
        }
      });
    }
  }
}
