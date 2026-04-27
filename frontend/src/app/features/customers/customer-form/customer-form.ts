// This file holds the Angular logic for customer form.
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './customer-form.html'
})
export class CustomerForm {
  customerService = inject(customer);
  addressService = inject(AddressService);
  route = inject(ActivatedRoute);
  router = inject(Router);

  customerId: number | null = null;
  isLoading = true;
  addresses: any[] = [];

  form = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
    addressId: new FormControl<number | null>(null, [Validators.required])
  });

  ngOnInit() {
    this.loadAddresses();

    this.route.queryParamMap.subscribe(queryParams => {
      const queryId = queryParams.get('id');

      this.route.paramMap.subscribe(pathParams => {
        const pathId = pathParams.get('id');
        const id = queryId ?? pathId;

        if (!id) {
          this.isLoading = false;
          return;
        }

        this.customerId = Number(id);

        this.customerService.getCustomerById(id).subscribe({
          next: (res: any) => {
            const data = res.data;

            if (!data) {
              alert('Customer not found');
              this.router.navigate(['/customer/list']);
              return;
            }

            this.form.patchValue({
              firstName: data.firstName,
              lastName: data.lastName,
              email: data.email,
              phoneNumber: data.phoneNumber,
              addressId: data.address?.addressId ?? data.addressId ?? null
            });

            this.isLoading = false;
          },
          error: (err) => {
            const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Error fetching customer');
            alert(msg);
            this.router.navigate(['/customer/list']);
          }
        });
      });
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

  submitCustomer() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();

      const errors: string[] = [];
      const firstName = this.form.get('firstName');
      const lastName = this.form.get('lastName');
      const email = this.form.get('email');
      const phoneNumber = this.form.get('phoneNumber');
      const addressId = this.form.get('addressId');

      if (firstName?.errors) {
        if (firstName.errors['required']) errors.push('First name cannot be empty');
        if (firstName.errors['minlength'] || firstName.errors['maxlength']) errors.push('First name must be between 2 and 30 characters');
      }
      if (lastName?.errors) {
        if (lastName.errors['required']) errors.push('Last name cannot be empty');
        if (lastName.errors['minlength'] || lastName.errors['maxlength']) errors.push('Last name must be between 2 and 30 characters');
      }
      if (email?.errors) {
        if (email.errors['required']) errors.push('Email cannot be empty');
        if (email.errors['email']) errors.push('Invalid email address');
      }
      if (phoneNumber?.errors) {
        if (phoneNumber.errors['required']) errors.push('Phone number cannot be empty');
        if (phoneNumber.errors['pattern']) errors.push('Phone number must be 10 digits');
      }
      if (addressId?.errors) {
        if (addressId.errors['required']) errors.push('Address ID cannot be null');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const selectedAddressId = this.form.value.addressId;
    if (selectedAddressId && this.addresses.length > 0) {
      const isValidAddress = this.addresses.some(a => a.addressId === selectedAddressId);
      if (!isValidAddress) {
        alert('Please select a valid Address from dropdown');
        return;
      }
    }

    const payload = {
      firstName: this.form.value.firstName?.trim(),
      lastName: this.form.value.lastName?.trim(),
      email: this.form.value.email,
      phoneNumber: this.form.value.phoneNumber,
      addressId: this.form.value.addressId
    };

    if (this.customerId) {
      this.customerService.updateCustomer(this.customerId.toString(), payload).subscribe({
        next: () => {
          alert('Customer updated successfully');
          this.router.navigate(['/customer/list']);
        },
        error: (err) => {
          console.log(err);
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Update failed');
          alert(msg);
        }
      });
    } else {
      this.customerService.addCustomer([payload]).subscribe({
        next: () => {
          alert('Customer created successfully');
          this.router.navigate(['/customer/list']);
        },
        error: (err) => {
          console.log(err);
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Create failed');
          alert(msg);
        }
      });
    }
  }
}
