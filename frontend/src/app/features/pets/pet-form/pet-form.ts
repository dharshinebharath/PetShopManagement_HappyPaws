import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PetsService } from '../../../core/services/petsService';
import { CategoryService } from '../../../core/services/categoryService';

@Component({
  selector: 'app-pet-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './pet-form.html'
})
export class PetForm {
  petsService = inject(PetsService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);
  categoryService = inject(CategoryService);

  petId: number | null = null;
  isLoading = true;
  categories: any[] = [];

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    breed: new FormControl('', [Validators.required, Validators.minLength(2)]),
    age: new FormControl(0, [Validators.required, Validators.min(0)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    description: new FormControl('', [Validators.required, Validators.minLength(5)]),
    categoryId: new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
  });

  ngOnInit() {
    this.loadCategories();

    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.petId = Number(params['id']);

        this.petsService.getById(this.petId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              name: data.name,
              breed: data.breed,
              age: data.age,
              price: data.price,
              description: data.description,
              categoryId: data.category_id
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Pet not found');
            this.router.navigate(['/pets']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  private loadCategories() {
    this.categoryService.getAll().subscribe({
      next: (res: any) => {
        this.categories = res?.data || [];
      },
      error: () => {
        this.categories = [];
        alert('Unable to load categories for selection');
      }
    });
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();

      const errors: string[] = [];
      const name = this.form.get('name');
      const breed = this.form.get('breed');
      const age = this.form.get('age');
      const price = this.form.get('price');
      const description = this.form.get('description');
      const categoryId = this.form.get('categoryId');

      if (name?.errors) {
        if (name.errors['required']) errors.push('Pet name is required');
        if (name.errors['minlength']) errors.push('Pet name must be at least 3 characters');
      }
      if (breed?.errors) {
        if (breed.errors['required']) errors.push('Breed is required');
        if (breed.errors['minlength']) errors.push('Breed must be at least 2 characters');
      }
      if (age?.errors) {
        if (age.errors['required']) errors.push('Age is required');
        if (age.errors['min']) errors.push('Age cannot be negative');
      }
      if (price?.errors) {
        if (price.errors['required']) errors.push('Price is required');
        if (price.errors['min']) errors.push('Price must be greater than 0');
      }
      if (description?.errors) {
        if (description.errors['required']) errors.push('Description is required');
        if (description.errors['minlength']) errors.push('Description must be at least 5 characters');
      }
      if (categoryId?.errors) {
        if (categoryId.errors['required']) errors.push('Category ID is required');
        if (categoryId.errors['min']) errors.push('Category ID must be greater than 0');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = {
      name: this.form.value.name,
      breed: this.form.value.breed,
      age: Number(this.form.value.age),
      price: Number(this.form.value.price),
      description: this.form.value.description,
      category_id: Number(this.form.value.categoryId),
      image_url: 'default.jpg'
    };

    if (this.petId !== null && this.petId !== undefined) {
      this.petsService.update(this.petId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/pets/list']).then(() => {
            window.location.reload();
          });
        },
        error: (err) => {
          console.log(err);
          alert('Update failed');
        }
      });
    } else {
      this.petsService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/pets/list']);
        },
        error: (err) => {
          console.log(err);
          alert('Create failed');
        }
      });
    }
  }
}
