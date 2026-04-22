import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';

import { FoodList } from '../food-list/food-list';
import { FoodForm } from '../food-form/food-form';
import { FoodService } from '../../../core/services/food';

@Component({
  selector: 'app-food-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FoodList,
    FoodForm,
    FormsModule,
    RouterOutlet
  ],
  templateUrl: './food-dashboard.html',
  styleUrl: './food-dashboard.css',
})
export class FoodDashboard {

  router = inject(Router);
  foodService = inject(FoodService);

  // ✅ ADD FOOD (FIXED)
  addFood() {
    this.router.navigate(['/food/form']);
  }

  // VIEW ALL
  goToList() {
    this.router.navigate(['/food/list']);
  }

  // VIEW BY ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.foodService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/food/list'], { queryParams: { id } });
      },
      error: (err) => {
        if (err.status === 404) alert('Food not found ❌');
        else alert('Something went wrong ⚠️');
      }
    });
  }

  // UPDATE
  updateFood(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.foodService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/food/form'], { queryParams: { id } });
      },
      error: () => alert('ID not found ❌')
    });
  }

  // DELETE
  deleteFood(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.foodService.delete(Number(id)).subscribe({
      next: () => {
        alert('Deleted successfully ✅');
        this.router.navigate(['/food/list']);
      },
      error: () => alert('Delete failed ❌')
    });
  }
}