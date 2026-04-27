// This file holds the Angular logic for grooming form.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { GroomingService } from '../../../core/services/groomingService';

// Form component for adding/editing grooming services
@Component({
  selector: 'app-grooming-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './grooming-form.html'
})

export class GroomingForm {
  // Injecting required services
  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  // Service ID
  serviceId: number | null = null;
  isLoading = true;
  // Form with validation
  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    description: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    available: new FormControl(true)
  });

  // Initialize the form
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.serviceId = Number(params['id']);

        this.groomingService.getById(this.serviceId).subscribe({
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
            const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Service not found');
            alert(msg);
            this.router.navigate(['/grooming-dashboard']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  // Submit form
  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();

      // Validation error messages
      const errors: string[] = [];
      const name = this.form.get('name');
      const desc = this.form.get('description');
      const price = this.form.get('price');

      if (name?.errors) {
        if (name.errors['required']) errors.push('Service name cannot be empty');
        if (name.errors['minlength'] || name.errors['maxlength']) errors.push('Service name must be between 2 and 50 characters');
      }

      if (desc?.errors) {
        if (desc.errors['required']) errors.push('Description cannot be empty');
        if (desc.errors['minlength'] || desc.errors['maxlength']) errors.push('Description must be between 2 and 255 characters');
      }

      if (price?.errors) {
        if (price.errors['required']) errors.push('Price cannot be null');
        if (price.errors['min']) errors.push('Price must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = this.form.value;

    // Update service if ID is present, otherwise create new service
    if (this.serviceId) {
      this.groomingService.update(this.serviceId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/grooming/list']);
        },
        error: (err) => {
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Update failed');
          alert(msg);
        }
      });
    } else {
      this.groomingService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/grooming/list']);
        },
        error: (err) => {
          console.log("Full error:", err);
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Create failed ❌');
          alert(msg);
        }      });
    }
  }
}
