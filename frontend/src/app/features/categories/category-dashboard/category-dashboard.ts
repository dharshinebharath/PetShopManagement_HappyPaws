// This file holds the Angular logic for category dashboard.
import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CategoryService } from '../../../core/services/categoryService';

@Component({
  selector: 'app-category-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './category-dashboard.html'
})
export class CategoryDashboard {

  router = inject(Router);
  categoryService = inject(CategoryService);
  // Navigate to list.
  goToList() {
    this.router.navigate(['/category/list']);
  }
  // View by ID.
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.categoryService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/category/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message;
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n');
        }

        alert(msg);
      }
    });
  }
  // Update by ID.
  updateCategory(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.categoryService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/category/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message;
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n');
        }

        alert(msg);
      }
    });
  }
  // Delete by ID.
  deleteCategory(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.categoryService.delete(Number(id)).subscribe({
      next: () => {
        alert('Category deleted successfully ✅');
        this.router.navigate(['/category/list']);
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message;
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n');
        }

        alert(msg);
      }
    });
  }
}

