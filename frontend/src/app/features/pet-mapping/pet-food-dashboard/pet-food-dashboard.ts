// This file holds the Angular logic for pet food dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PetFoodMappingService } from '../../../core/services/pet-food-mapping-service';

@Component({
  selector: 'app-pet-food-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pet-food-dashboard.html'
})
export class PetFoodDashboard {
  service = inject(PetFoodMappingService);
  router = inject(Router);

  getPetId: string = '';
  postPetId: string = '';
  postFoodId: string = '';
  deletePetId: string = '';
  deleteFoodId: string = '';

  // View food by pet ID.
  viewFood() {
    if (!this.getPetId) {
      alert('Enter Pet ID');
      return;
    }

    this.service.getFoodByPet(Number(this.getPetId)).subscribe({
      next: (res: any) => {
        if (!res || !res.data || res.data.length === 0) {
          alert('No food found for this Pet ID');
          return;
        }

        this.router.navigate(['/pet-mapping/food/list'], {
          queryParams: { petId: this.getPetId }
        });
      },
      error: (err) => {
        console.log(err);
        alert('Invalid Pet ID');
      }
    });
  }

  // Assign food to a pet.
  assign() {
    if (!this.postPetId || !this.postFoodId) {
      alert('Enter both IDs');
      return;
    }

    this.service.assignFood(Number(this.postPetId), Number(this.postFoodId)).subscribe({
      next: () => {
        alert('Assigned Successfully');
        this.router.navigate(['/pet-mapping/food/list'], {
          queryParams: { petId: this.postPetId }
        });
      },
      error: () => alert('Assign Failed')
    });
  }

  // Remove a pet-food mapping.
  remove() {
    if (!this.deletePetId || !this.deleteFoodId) {
      alert('Enter both IDs');
      return;
    }

    this.service.removeFood(Number(this.deletePetId), Number(this.deleteFoodId)).subscribe({
      next: () => {
        alert('Deleted Successfully');
        this.router.navigate(['/pet-mapping/food/list'], {
          queryParams: { petId: this.deletePetId }
        });
      },
      error: () => alert('Delete Failed')
    });
  }
}
