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
  goToList() {
    this.router.navigate(['/category/list']);
  }
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
        alert('Category not found Ã¢ÂÅ’');
      }
    });
  }
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
        alert('Cannot update Ã¢ÂÅ’ ID not found');
      }
    });
  }
  deleteCategory(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.categoryService.delete(Number(id)).subscribe({
      next: () => {
        alert('Category deleted successfully Ã¢Å“â€¦');
        this.router.navigate(['/category/list']);
      },
      error: () => {
        alert('Delete failed Ã¢ÂÅ’');
      }
    });
  }
}

