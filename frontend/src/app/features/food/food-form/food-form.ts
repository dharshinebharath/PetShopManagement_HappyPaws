// This file holds the Angular logic for food form.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodService } from '../../../core/services/food';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-food-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './food-form.html'
})
export class FoodForm {
  foodService = inject(FoodService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  foodId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    brand: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    type: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    quantity: new FormControl(0, [Validators.required, Validators.min(0)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)])
  });

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.foodId = Number(params['id']);

        this.foodService.getById(this.foodId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              name: data.name,
              brand: data.brand,
              type: data.type,
              quantity: data.quantity,
              price: data.price
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Food item not found');
            alert(msg);
            this.router.navigate(['/food-dashboard']);
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
      const brand = this.form.get('brand');
      const type = this.form.get('type');
      const quantity = this.form.get('quantity');
      const price = this.form.get('price');

      if (name?.errors) {
        if (name.errors['required']) errors.push('Food name cannot be empty');
        if (name.errors['minlength'] || name.errors['maxlength']) errors.push('Food name must be between 2 and 50 characters');
      }
      if (brand?.errors) {
        if (brand.errors['required']) errors.push('Brand cannot be empty');
        if (brand.errors['minlength'] || brand.errors['maxlength']) errors.push('Brand must be between 2 and 50 characters');
      }
      if (type?.errors) {
        if (type.errors['required']) errors.push('Type cannot be empty');
        if (type.errors['minlength'] || type.errors['maxlength']) errors.push('Type must be between 2 and 50 characters');
      }
      if (quantity?.errors) {
        if (quantity.errors['required']) errors.push('Quantity cannot be null');
        if (quantity.errors['min']) errors.push('Quantity cannot be negative');
      }
      if (price?.errors) {
        if (price.errors['required']) errors.push('Price cannot be null');
        if (price.errors['min']) errors.push('Price must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = this.form.value;

    if (this.foodId) {
      this.foodService.update(this.foodId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/food/list']);
        },
        error: (err) => {
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Update failed');
          alert(msg);
        }
      });
    } else {
      this.foodService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/food/list']);
        },
        error: (err) => {
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Create failed');
          alert(msg);
        }
      });
    }
  }
}
