// This file holds the Angular logic for vaccination dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { VaccinationService } from '../../../core/services/vaccinationService';

@Component({
  selector: 'app-vaccination-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './vaccination-dashboard.html'
})

export class VaccinationDashboard {

  router = inject(Router);
  vaccinationService = inject(VaccinationService);

  // Method to navigate to vaccination list
  goToList() {
    this.router.navigate(['/vaccination/list']);
  }

  // Method to view vaccination by ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter Vaccination ID');
      return;
    }

    this.vaccinationService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/vaccination/list'], {
          queryParams: { id }
        });
      },
      error: () => {
        alert('Vaccination ID not found');
      }
    });
  }

  // Method to update vaccination
  updateVaccination(id: string) {
    if (!id) {
      alert('Enter Vaccination ID to update');
      return;
    }

    this.vaccinationService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/vaccination/form'], {
          queryParams: { id }
        });
      },
      error: () => alert('ID not found')
    });
  }

  // Method to delete vaccination
  deleteVaccination(id: string) {
    if (!id) {
      alert('Enter Vaccination ID to delete');
      return;
    }

    this.vaccinationService.delete(Number(id)).subscribe({
      next: () => {
        alert('Deleted successfully');
        this.router.navigate(['/vaccination/list']);
      },
      error: () => alert('Delete failed')
    });
  }
}
