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
    name: new FormControl('', [Validators.required, Validators.minLength(3)])
  });

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.categoryId = Number(params['id']);

        this.categoryService.getById(this.categoryId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.form.patchValue({
              name: data.name
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Category not found');
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
      this.form.markAllAsTouched();
      alert('Please fix errors:\n\nCategory name is required and must be at least 3 characters');
      return;
    }

    const payload = {
      name: this.form.value.name
    };

    if (this.categoryId !== null && this.categoryId !== undefined) {
      this.categoryService.update(this.categoryId, payload).subscribe({
        next: () => {
          alert('Updated successfully');
          this.router.navigate(['/category/list']);
        },
        error: () => {
          alert('Update failed');
        }
      });
    } else {
      this.categoryService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully');
          this.router.navigate(['/category/list']);
        },
        error: () => {
          alert('Create failed');
        }
      });
    }
  }
}
