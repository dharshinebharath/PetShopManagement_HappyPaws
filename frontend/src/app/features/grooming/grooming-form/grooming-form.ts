import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

@Component({
  selector: 'app-grooming-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './grooming-form.html'
})
export class GroomingForm {
  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  serviceId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [Validators.required, Validators.minLength(5)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    available: new FormControl(true)
  });

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
          error: () => {
            alert('Service not found');
            this.router.navigate(['/grooming']);
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
      const name = this.form.get('name');
      const description = this.form.get('description');
      const price = this.form.get('price');

      if (name?.errors) {
        if (name.errors['required']) errors.push('Name is required');
        if (name.errors['minlength']) errors.push('Name must be at least 3 characters');
      }

      if (description?.errors) {
        if (description.errors['required']) errors.push('Description is required');
        if (description.errors['minlength']) errors.push('Description must be at least 5 characters');
      }

      if (price?.errors) {
        if (price.errors['required']) errors.push('Price is required');
        if (price.errors['min']) errors.push('Price must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    if (this.serviceId !== null && this.serviceId !== undefined) {
      this.groomingService.update(this.serviceId, this.form.value).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/grooming/list']);
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
      const payload = [{
        name: this.form.value.name,
        description: this.form.value.description,
        price: Number(this.form.value.price),
        available: this.form.value.available
      }];

      this.groomingService.create(payload).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/grooming/list']);
        },
        error: (err) => {
          console.log(err);
          alert('Create failed');
        }
      });
    }
  }
}
