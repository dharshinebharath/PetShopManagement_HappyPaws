// This file holds the Angular logic for address form.
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

  // Reactive form for address validation.
  form = new FormGroup({
    street: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    city: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    state: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    zipCode: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{5}$')])
  });

  // Initialize the component.
  ngOnInit() {
    // Get address id from query params.
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
          error: (err) => {
            let msg = 'Address not found';
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
            this.router.navigate(['/address']);
          }
        });
      } else {
        // Set loading to false when address is not found.
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

      // Validate each field.
      if (street?.errors) {
        if (street.errors['required']) errors.push('Street cannot be empty');
        if (street.errors['minlength'] || street.errors['maxlength']) errors.push('Street must be between 2 and 50 characters');
      }
      if (city?.errors) {
        if (city.errors['required']) errors.push('City cannot be empty');
        if (city.errors['minlength'] || city.errors['maxlength']) errors.push('City must be between 2 and 50 characters');
      }
      if (state?.errors) {
        if (state.errors['required']) errors.push('State cannot be empty');
        if (state.errors['minlength'] || state.errors['maxlength']) errors.push('State must be between 2 and 50 characters');
      }
      if (zipCode?.errors) {
        if (zipCode.errors['required']) errors.push('Zip code cannot be null');
        if (zipCode.errors['pattern']) errors.push('Zip code must be 5 digits');
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

    // Update an existing address.
    if (this.addressId !== null && this.addressId !== undefined) {
      this.addressService.update(this.addressId, payload).subscribe({
        next: () => {
          alert('Address updated successfully');
          this.router.navigate(['/address/list']);
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
    // Create a new address.
    else {
      this.addressService.create([payload]).subscribe({
        next: () => {
          alert('Address created successfully');
          this.router.navigate(['/address/list']);
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
