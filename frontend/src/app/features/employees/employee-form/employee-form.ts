// This file holds the Angular logic for employee form.
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './employee-form.html'
})
export class EmployeeForm {
  http = inject(HttpClient);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);
  addressService = inject(AddressService);

  private baseUrl = 'http://localhost:8081/api/v1/employees';

  employeeId: number | null = null;
  isLoading = true;
  addresses: any[] = [];

  form = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
    position: new FormControl('', [Validators.required]),
    hireDate: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    addressId: new FormControl<number | null>(null, [Validators.required])
  });

  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  ngOnInit() {
    this.loadAddresses();

    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.employeeId = Number(params['id']);

        this.http.get<any>(`${this.baseUrl}/${this.employeeId}`, this.getAuthHeaders())
          .subscribe({
            next: (res) => {
              const data = res.data;

              this.form.patchValue({
                firstName: data.firstName,
                lastName: data.lastName,
                position: data.position,
                hireDate: data.hireDate ? new Date(data.hireDate).toISOString().split('T')[0] : '',
                phoneNumber: data.phoneNumber,
                email: data.email,
                addressId: data.addressId
              });

              this.isLoading = false;
              this.cdr.detectChanges();
            },
            error: (err) => {
              const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Employee not found');
              alert(msg);
              this.router.navigate(['/employee/list']);
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
      const firstName = this.form.get('firstName');
      const lastName = this.form.get('lastName');
      const position = this.form.get('position');
      const hireDate = this.form.get('hireDate');
      const phoneNumber = this.form.get('phoneNumber');
      const email = this.form.get('email');
      const addressId = this.form.get('addressId');

      if (firstName?.errors) {
        if (firstName.errors['required']) errors.push('First name cannot be empty');
        if (firstName.errors['minlength'] || firstName.errors['maxlength']) errors.push('First name must be between 2 and 30 characters');
      }
      if (lastName?.errors) {
        if (lastName.errors['required']) errors.push('Last name cannot be empty');
        if (lastName.errors['minlength'] || lastName.errors['maxlength']) errors.push('Last name must be between 2 and 30 characters');
      }
      if (position?.errors) {
        if (position.errors['required']) errors.push('Position cannot be empty');
      }
      if (hireDate?.errors) errors.push('Hire date cannot be null');
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

    const payload = {
      ...this.form.value,
      hireDate: this.form.value.hireDate ? this.form.value.hireDate.split('T')[0] : ''
    };

    if (this.employeeId !== null && this.employeeId !== undefined) {
      this.http.put(`${this.baseUrl}/${this.employeeId}`, payload, this.getAuthHeaders()).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/employee/list']);
        },
        error: (err) => {
          console.log('FULL ERROR:', err);
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Update failed');
          alert(msg);
        }
      });
    } else {
      this.http.post(this.baseUrl, [payload], this.getAuthHeaders())
        .subscribe({
          next: () => {
            alert('Created successfully');
            this.router.navigate(['/employee/list']);
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
