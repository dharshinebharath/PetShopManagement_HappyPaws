import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PetGroomingMappingService } from '../../../core/services/pet-grooming-mapping-service';

@Component({
  selector: 'app-pet-grooming-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pet-grooming-dashboard.html'
})
export class PetGroomingDashboard {

  service = inject(PetGroomingMappingService);
  router = inject(Router);

  getPetId: string = '';
  postPetId: string = '';
  postServiceId: string = '';
  deletePetId: string = '';
  deleteServiceId: string = '';

  // ================= GET =================
 viewServices() {
  if (!this.getPetId) {
    alert('Enter Pet ID ⚠️');
    return;
  }

  this.service.getGroomingByPet(Number(this.getPetId)).subscribe({
    
    next: (res: any) => {

      if (!res || !res.data || res.data.length === 0) {
        alert('No services found for this Pet ID ❌');
        return;
      }

      this.router.navigate(['/pet-mapping/grooming/list'], {
        queryParams: { petId: this.getPetId }
      });
    },

    error: (err) => {
      console.log(err);

      alert('Invalid Pet ID ❌');
    }
  });
}
  // ================= POST =================
  assign() {
    if (!this.postPetId || !this.postServiceId) {
      alert('Enter both IDs ⚠️');
      return;
    }

    this.service.assignGrooming(
      Number(this.postPetId),
      Number(this.postServiceId)
    ).subscribe({
      next: () => {
        alert('Assigned Successfully ✅');

        this.router.navigate(['/pet-mapping/grooming/list'], {
          queryParams: { petId: this.postPetId }
        });
      },
      error: () => alert('Assign Failed ❌')
    });
  }

  // ================= DELETE =================
  remove() {
    if (!this.deletePetId || !this.deleteServiceId) {
      alert('Enter both IDs ⚠️');
      return;
    }

    this.service.removeGrooming(
      Number(this.deletePetId),
      Number(this.deleteServiceId)
    ).subscribe({
      next: () => {
        alert('Deleted Successfully ✅');

        this.router.navigate(['/pet-mapping/grooming/list'], {
          queryParams: { petId: this.deletePetId }
        });
      },
      error: () => alert('Delete Failed ❌')
    });
  }
}
