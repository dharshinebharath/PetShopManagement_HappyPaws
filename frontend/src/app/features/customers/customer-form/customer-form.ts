import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,FormsModule],
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
    firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern('^[A-Za-z ]+$')]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern('^[A-Za-z ]+$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
    addressId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
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
          error: () => {
            alert('Error fetching customer');
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
        if (firstName.errors['required']) errors.push('First name is required');
        if (firstName.errors['minlength']) errors.push('First name must be at least 2 characters');
        if (firstName.errors['pattern']) errors.push('First name must contain only letters');
      }
      if (lastName?.errors) {
        if (lastName.errors['required']) errors.push('Last name is required');
        if (lastName.errors['minlength']) errors.push('Last name must be at least 2 characters');
        if (lastName.errors['pattern']) errors.push('Last name must contain only letters');
      }
      if (email?.errors) {
        if (email.errors['required']) errors.push('Email is required');
        if (email.errors['email']) errors.push('Email must be valid');
      }
      if (phoneNumber?.errors) {
        if (phoneNumber.errors['required']) errors.push('Phone number is required');
        if (phoneNumber.errors['pattern']) errors.push('Phone number must be a 10-digit number like 5555678001');
      }
      if (addressId?.errors) {
        if (addressId.errors['required']) errors.push('Address ID is required');
        if (addressId.errors['min']) errors.push('Address ID must be greater than 0');
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
          alert(err.error?.message || 'Update failed');
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
          alert(err.error?.message || 'Create failed');
        }
      });
    }
  }
}
