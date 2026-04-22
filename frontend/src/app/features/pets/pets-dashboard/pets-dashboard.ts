import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PetsService } from '../../../core/services/petsService';

@Component({
  selector: 'app-pets-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './pets-dashboard.html'
})
export class PetsDashboard {

  router = inject(Router);
  petService = inject(PetsService);

  // GET ALL
  goToList() {
    this.router.navigate(['/pets/list']);
  }

  // GET BY ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.petService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/pets/list'], {
          queryParams: { id }
        });
      },
      error: () => {
        alert('Pet not found ❌');
      }
    });
  }

  // UPDATE
  updatePet(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.petService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/pets/form'], {
          queryParams: { id }
        });
      },
      error: () => {
        alert('Cannot update ❌ ID not found');
      }
    });
  }

  // DELETE
  deletePet(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.petService.delete(Number(id)).subscribe({
      next: () => {
        alert('Pet deleted successfully ✅');
        this.router.navigate(['/pets/list']);
      },
      error: () => {
        alert('Delete failed ❌');
      }
    });
  }
}