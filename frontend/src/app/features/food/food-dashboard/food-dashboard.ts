// This file holds the Angular logic for food dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { FoodService } from '../../../core/services/food';


@Component({
  selector: 'app-food-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './food-dashboard.html'
})
export class FoodDashboard {

  router = inject(Router);
  foodService = inject(FoodService);
  // Go to food list.
  goToList() {
    this.router.navigate(['/food/list']);
  }
  // View food by ID.
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.foodService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/food/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Food ID not found ❌');
        } else {
          alert('Something went wrong ⚠️Â ');
        }
      }
    });
  }
  // Update food by ID.
  updateFood(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.foodService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/food/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Cannot update ❌ ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }
  // Delete food by ID.
  deleteFood(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.foodService.delete(Number(id)).subscribe({
      next: () => {
        alert('Food deleted successfully ✅');
        this.router.navigate(['/food/list']);
      },
      error: (err) => {
        console.error(err);
        alert('Delete failed ❌');
      }
    });
  }
}

