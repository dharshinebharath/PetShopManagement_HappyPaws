// This file holds the Angular logic for pet supplier dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PetSupplierMappingService } from '../../../core/services/pet-supplier-mapping-service';

@Component({
  selector: 'app-pet-supplier-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pet-supplier-dashboard.html'
})
export class PetSupplierDashboard {
  service = inject(PetSupplierMappingService);
  router = inject(Router);

  getPetId: string = '';
  postPetId: string = '';
  postSupplierId: string = '';

  // View suppliers for a pet.
  viewSuppliers() {
    if (!this.getPetId) {
      alert('Enter Pet ID');
      return;
    }

    this.service.getSuppliersByPet(Number(this.getPetId)).subscribe({
      next: (res: any) => {
        if (!res || !res.data || res.data.length === 0) {
          alert('No suppliers found for this Pet ID');
          return;
        }

        this.router.navigate(['/pet-mapping/suppliers/list'], {
          queryParams: { petId: this.getPetId }
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

  // Assign a supplier to a pet.
  assign() {
    if (!this.postPetId || !this.postSupplierId) {
      alert('Enter both IDs');
      return;
    }

    this.service.assignSupplier(Number(this.postPetId), Number(this.postSupplierId)).subscribe({
      next: () => {
        alert('Assigned Successfully');
        this.router.navigate(['/pet-mapping/suppliers/list'], {
          queryParams: { petId: this.postPetId }
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
}
