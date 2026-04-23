import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PetsService } from '../../../core/services/petsService';

@Component({
  selector: 'app-pets-filter-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pets-filter-dashboard.html'
})
export class PetsFilterDashboard {

  router = inject(Router);
  service = inject(PetsService);

  categoryId = '';
  breed = '';
  min = '';
  max = '';

  byCategory() {
    if (!this.categoryId) {
      alert('Enter Category ID');
      return;
    }

    this.service.getByCategory(Number(this.categoryId)).subscribe({
      next: (res: any) => {
        if (!res?.data?.length) {
          alert('No pets found');
          return;
        }

        this.router.navigate(['/pets-filter/list'], {
          queryParams: { type: 'category', value: this.categoryId },
          state: { pets: res.data }
        });
      },
      error: () => alert('Error while fetching pets')
    });
  }

  byBreed() {
    if (!this.breed) {
      alert('Enter Breed');
      return;
    }

    this.service.getByBreed(this.breed).subscribe({
      next: (res: any) => {
        if (!res?.data?.length) {
          alert('No pets found');
          return;
        }

        this.router.navigate(['/pets-filter/list'], {
          queryParams: { type: 'breed', value: this.breed },
          state: { pets: res.data }
        });
      },
      error: () => alert('Error while fetching pets')
    });
  }

  byPrice() {
    if (!this.min || !this.max) {
      alert('Enter Min & Max');
      return;
    }

    this.service.getByPrice(Number(this.min), Number(this.max)).subscribe({
      next: (res: any) => {
        if (!res?.data?.length) {
          alert('No pets found');
          return;
        }

        this.router.navigate(['/pets-filter/list'], {
          queryParams: { type: 'price', min: this.min, max: this.max },
          state: { pets: res.data }
        });
      },
      error: () => alert('Error while fetching pets')
    });
  }
}
