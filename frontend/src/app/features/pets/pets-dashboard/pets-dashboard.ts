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
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
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
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
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
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }
}
