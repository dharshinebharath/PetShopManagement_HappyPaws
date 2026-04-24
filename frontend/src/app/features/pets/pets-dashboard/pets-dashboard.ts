// This file holds the Angular logic for pets dashboard.
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
        alert('Pet not found Ã¢ÂÅ’');
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
        alert('Cannot update Ã¢ÂÅ’ ID not found');
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
        alert('Pet deleted successfully Ã¢Å“â€¦');
        this.router.navigate(['/pets/list']);
      },
      error: () => {
        alert('Delete failed Ã¢ÂÅ’');
      }
    });
  }
}
