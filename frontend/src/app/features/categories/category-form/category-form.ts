// This file holds the Angular logic for category form.
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../core/services/categoryService';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './category-form.html'
})
export class CategoryForm {
  categoryService = inject(CategoryService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  categoryId: number | null = null;
  isLoading = true;

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)])
  });

  // Initialize the component.
  ngOnInit() {
    // Get category id from query params.
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.categoryId = Number(params['id']);

        // Get category by ID.
        this.categoryService.getById(this.categoryId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              name: data.name
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Category not found');
            alert(msg);
            this.router.navigate(['/categories']);
          }
        });
      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {
    if (this.form.invalid) {
      const name = this.form.get('name');
      const errors: string[] = [];
      if (name?.errors) {
        if (name.errors['required']) errors.push('Category name cannot be empty');
        if (name.errors['minlength'] || name.errors['maxlength']) errors.push('Category name must be between 2 and 50 characters');
      }
      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const payload = {
      name: this.form.value.name
    };
    // Update an existing category.
    if (this.categoryId !== null && this.categoryId !== undefined) {
      this.categoryService.update(this.categoryId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/category/list']);
        },
        error: (err) => {
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Update failed');
          alert(msg);
        }
      });
    } 
    // Create a new category.
    else {
      this.categoryService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/category/list']);
        },
        error: (err) => {
          const msg = err.error?.errors?.join('\n') || (typeof err.error === 'string' ? err.error : 'Create failed');
          alert(msg);
        }
      });
    }
  }
}
