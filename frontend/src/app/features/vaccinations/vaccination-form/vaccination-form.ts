// This file holds the Angular logic for vaccination form.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VaccinationService } from '../../../core/services/vaccinationService';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vaccination-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './vaccination-form.html'
})
export class VaccinationForm {

  vaccinationService = inject(VaccinationService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  serviceId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    description: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    available: new FormControl(true)
  });

  // Initialize the component
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {

        this.serviceId = Number(params['id']);

        this.vaccinationService.getById(this.serviceId).subscribe({
          next: (res: any) => {

            const data = res.data;

            this.form.patchValue({
              name: data.name,
              description: data.description,
              price: data.price,
              available: data.available
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            let msg = 'Vaccination not found';
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
            this.router.navigate(['/vaccination']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {

    // Check if form is invalid
    if (this.form.invalid) {

      this.form.markAllAsTouched();

      let errors: string[] = [];

      const name = this.form.get('name');
      const desc = this.form.get('description');
      const price = this.form.get('price');

      if (name?.errors) {
        if (name.errors['required']) {
          errors.push('Vaccination name cannot be empty');
        }
        if (name.errors['minlength'] || name.errors['maxlength']) {
          errors.push('Vaccination name must be between 2 and 50 characters');
        }
      }

      if (desc?.errors) {
        if (desc.errors['required']) {
          errors.push('Description cannot be empty');
        }
        if (desc.errors['minlength'] || desc.errors['maxlength']) {
          errors.push('Description must be between 2 and 255 characters');
        }
      }

      if (price?.errors) {
        if (price.errors['required']) {
          errors.push('Price cannot be null');
        }
        if (price.errors['min']) {
          errors.push('Price must be greater than 0');
        }
      }

      alert('❌ Please fix errors:\n\n' + errors.join('\n'));

      return;
    }

    // Get payload
    const payload = this.form.value;

    if (this.serviceId) {
      // Update vaccination
      this.vaccinationService.update(this.serviceId, payload).subscribe({
        next: () => {
          alert('Updated successfully ✅');
          this.router.navigate(['/vaccination/list']);
        },
        error: (err) => {
          let msg = 'Update failed ❌';
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
    // Create vaccination
    else {

      const createPayload = [{
        name: this.form.value.name,
        description: this.form.value.description,
        price: Number(this.form.value.price),
        available: this.form.value.available
      }];

      this.vaccinationService.create(createPayload).subscribe({
        next: () => {
          alert('Created successfully ✅');
          this.router.navigate(['/vaccination/list']);
        },
        error: (err) => {
          let msg = 'Create failed ❌';
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
