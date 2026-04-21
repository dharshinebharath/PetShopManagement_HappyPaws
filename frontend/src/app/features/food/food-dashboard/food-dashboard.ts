import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';

import { FoodList } from '../food-list/food-list';
import { FoodForm } from '../food-form/food-form';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { FoodService } from '../../../core/services/food';

@Component({
  selector: 'app-food-dashboard',
  standalone: true,
  imports: [CommonModule, FoodList, FoodForm,FormsModule],
  templateUrl: './food-dashboard.html',
  styleUrl: './food-dashboard.css',
})
export class FoodDashboard {

    router = inject(Router);
  foodService = inject(FoodService);

  goToList() {
    this.router.navigate(['/food/list']);
  }

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