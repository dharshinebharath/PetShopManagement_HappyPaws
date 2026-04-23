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

  // GET ALL
  goToList() {
    this.router.navigate(['/category/list']);
  }

  // GET BY ID
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
      error: () => {
        alert('Category not found ❌');
      }
    });
  }

  // UPDATE
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
      error: () => {
        alert('Cannot update ❌ ID not found');
      }
    });
  }

  // DELETE
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
      error: () => {
        alert('Delete failed ❌');
      }
    });
  }
}