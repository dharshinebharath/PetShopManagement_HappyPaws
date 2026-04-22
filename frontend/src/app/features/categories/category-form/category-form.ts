import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../core/services/categoryService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './category-form.html'
})
export class CategoryForm {

  categoryService = inject(CategoryService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  categoryId: number | null = null;

  formData: any = {
    name: ''
  };

  isLoading = true;

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.categoryId = Number(params['id']);

        this.categoryService.getById(this.categoryId).subscribe({
          next: (res: any) => {

            const data = res.data;

            this.formData.name = data.name;

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Category not found ❌');
            this.router.navigate(['/categories']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {

    if (!this.formData.name) {
      alert('Please enter category name ⚠️');
      return;
    }

    if (this.categoryId !== null && this.categoryId !== undefined) {

      // UPDATE
      this.categoryService.update(this.categoryId, this.formData).subscribe({
        next: () => {
          alert('Updated successfully ✅');
          this.router.navigate(['/category/list']);
        },
        error: () => {
          alert('Update failed ❌');
        }
      });

    } else {

      // CREATE
      const payload = [{
        name: this.formData.name
      }];

      this.categoryService.create(payload).subscribe({
        next: () => {
          alert('Created successfully ✅');
          this.router.navigate(['/category/list']);
        },
        error: () => {
          alert('Create failed ❌');
        }
      });
    }
  }
}