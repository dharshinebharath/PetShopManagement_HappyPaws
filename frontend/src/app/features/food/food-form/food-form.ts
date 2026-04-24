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
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    brand: new FormControl('', [Validators.required, Validators.minLength(2)]),
    type: new FormControl('', [Validators.required, Validators.minLength(2)]),
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
          error: () => {
            alert('Food item not found');
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
        if (name.errors['required']) errors.push('Name is required');
        if (name.errors['minlength']) errors.push('Name must be at least 3 characters');
      }
      if (brand?.errors) {
        if (brand.errors['required']) errors.push('Brand is required');
        if (brand.errors['minlength']) errors.push('Brand must be at least 2 characters');
      }
      if (type?.errors) {
        if (type.errors['required']) errors.push('Type is required');
        if (type.errors['minlength']) errors.push('Type must be at least 2 characters');
      }
      if (quantity?.errors) {
        if (quantity.errors['required']) errors.push('Quantity is required');
        if (quantity.errors['min']) errors.push('Quantity cannot be negative');
      }
      if (price?.errors) {
        if (price.errors['required']) errors.push('Price is required');
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
        error: () => alert('Update failed')
      });
    } else {
      this.foodService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/food/list']);
        },
        error: () => alert('Create failed')
      });
    }
  }
}
