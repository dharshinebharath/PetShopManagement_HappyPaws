// This file holds the Angular logic for pet vaccination dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PetVaccinationMappingService } from '../../../core/services/pet-vaccination-mapping-service';

@Component({
  selector: 'app-pet-vaccination-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pet-vaccination-dashboard.html'
})
// Logic for pet-vaccination dashboard
export class PetVaccinationDashboard {

  // Injecting required services
  service = inject(PetVaccinationMappingService);
  router = inject(Router);

  // Form fields for pet and vaccination IDs
  getPetId: string = '';
  postPetId: string = '';
  postVaccinationId: string = '';
  deletePetId: string = '';
  deleteVaccinationId: string = '';

  // Method to view vaccinations for a pet
  viewVaccinations() {
    if (!this.getPetId) {
      alert('Enter Pet ID ⚠️Â');
      return;
    }

    this.service.getVaccinationByPet(Number(this.getPetId)).subscribe({
      next: (res: any) => {

        if (!res || !res.data || res.data.length === 0) {
          alert('No vaccinations found ❌');
          return;
        }

        this.router.navigate(['/pet-mapping/vaccination/list'], {
          queryParams: { petId: this.getPetId }
        });
      },
      error: () => alert('Invalid Pet ID ❌')
    });
  }

  // Method to assign a vaccination to a pet
  assign() {
    if (!this.postPetId || !this.postVaccinationId) {
      alert('Enter both IDs ⚠️ ');
      return;
    }

    this.service.assignVaccination(
      Number(this.postPetId),
      Number(this.postVaccinationId)
    ).subscribe({
      next: () => {
        alert('Assigned Successfully ✅');

        this.router.navigate(['/pet-mapping/vaccination/list'], {
          queryParams: { petId: this.postPetId }
        });
      },
      error: () => alert('Assign Failed ❌')
    });
  }

  // Method to remove a vaccination from a pet
  remove() {
    if (!this.deletePetId || !this.deleteVaccinationId) {
      alert('Enter both IDs ⚠️ ');
      return;
    }

    this.service.removeVaccination(
      Number(this.deletePetId),
      Number(this.deleteVaccinationId)
    ).subscribe({
      next: () => {
        alert('Deleted Successfully ✅');

        this.router.navigate(['/pet-mapping/vaccination/list'], {
          queryParams: { petId: this.deletePetId }
        });
      },
      error: () => alert('Delete Failed ❌')
    });
  }
}

