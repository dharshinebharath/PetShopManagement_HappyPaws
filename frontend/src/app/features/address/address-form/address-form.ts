import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-address-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './address-form.html'
})
export class AddressForm {
  addressService = inject(AddressService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  addressId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    street: new FormControl('', [Validators.required, Validators.minLength(3)]),
    city: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern('^[A-Za-z ]+$')]),
    state: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern('^[A-Za-z ]+$')]),
    zipCode: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{5,6}$')])
  });

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.addressId = Number(params['id']);

        this.addressService.getById(this.addressId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              street: data.street,
              city: data.city,
              state: data.state,
              zipCode: data.zipCode
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Address not found');
            this.router.navigate(['/address']);
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
      const street = this.form.get('street');
      const city = this.form.get('city');
      const state = this.form.get('state');
      const zipCode = this.form.get('zipCode');

      if (street?.errors) {
        if (street.errors['required']) errors.push('Street is required');
        if (street.errors['minlength']) errors.push('Street must be at least 3 characters');
      }
      if (city?.errors) {
        if (city.errors['required']) errors.push('City is required');
        if (city.errors['minlength']) errors.push('City must be at least 2 characters');
        if (city.errors['pattern']) errors.push('City must contain only letters');
      }
      if (state?.errors) {
        if (state.errors['required']) errors.push('State is required');
        if (state.errors['minlength']) errors.push('State must be at least 2 characters');
        if (state.errors['pattern']) errors.push('State must contain only letters');
      }
      if (zipCode?.errors) {
        if (zipCode.errors['required']) errors.push('Zip code is required');
        if (zipCode.errors['pattern']) errors.push('Zip code must be 5 or 6 digits');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = {
      street: this.form.value.street,
      city: this.form.value.city,
      state: this.form.value.state,
      zipCode: this.form.value.zipCode
    };

    if (this.addressId !== null && this.addressId !== undefined) {
      this.addressService.update(this.addressId, payload).subscribe({
        next: () => {
          alert('Address updated successfully');
          this.router.navigate(['/address/list']);
        },
        error: (err) => {
          if (err.status === 404) {
            alert('ID not found');
          } else {
            alert('Update failed');
          }
        }
      });
    } else {
      this.addressService.create([payload]).subscribe({
        next: () => {
          alert('Address created successfully');
          this.router.navigate(['/address/list']);
        },
        error: (err) => {
          console.log(err);
          alert('Create failed');
        }
      });
    }
  }
}
