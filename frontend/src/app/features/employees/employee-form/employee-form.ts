import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

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

  private baseUrl = 'http://localhost:8081/api/v1/employees';

  employeeId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    position: new FormControl('', [Validators.required, Validators.minLength(2)]),
    hireDate: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{3}-[0-9]{4}$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    addressId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
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
            error: () => {
              alert('Employee not found');
              this.router.navigate(['/employee/list']);
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
      const firstName = this.form.get('firstName');
      const lastName = this.form.get('lastName');
      const position = this.form.get('position');
      const hireDate = this.form.get('hireDate');
      const phoneNumber = this.form.get('phoneNumber');
      const email = this.form.get('email');
      const addressId = this.form.get('addressId');

      if (firstName?.errors) {
        if (firstName.errors['required']) errors.push('First name is required');
        if (firstName.errors['minlength']) errors.push('First name must be at least 2 characters');
      }
      if (lastName?.errors) {
        if (lastName.errors['required']) errors.push('Last name is required');
        if (lastName.errors['minlength']) errors.push('Last name must be at least 2 characters');
      }
      if (position?.errors) {
        if (position.errors['required']) errors.push('Position is required');
        if (position.errors['minlength']) errors.push('Position must be at least 2 characters');
      }
      if (hireDate?.errors) errors.push('Hire date is required');
      if (phoneNumber?.errors) {
        if (phoneNumber.errors['required']) errors.push('Phone number is required');
        if (phoneNumber.errors['pattern']) errors.push('Phone number must be in 555-1234 format');
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
          console.log('BACKEND MSG:', err.error);

          if (err.status === 404) {
            alert('Employee not found');
          } else {
            alert('Update failed');
          }
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
            alert('Create failed');
          }
        });
    }
  }
}
