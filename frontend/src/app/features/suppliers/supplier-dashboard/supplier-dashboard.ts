// This file holds the Angular logic for supplier dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { SupplierService } from '../../../core/services/supplier';


@Component({
  selector: 'app-supplier-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './supplier-dashboard.html'
})
export class SupplierDashboard {

  router = inject(Router);
  supplierService = inject(SupplierService);

  // GET ALL
  goToList() {
    this.router.navigate(['/supplier/list']);
  }

  // GET BY ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.supplierService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/supplier/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Supplier ID not found Ã¢ÂÅ’');
        } else {
          alert('Something went wrong Ã¢Å¡Â Ã¯Â¸Â');
        }
      }
    });
  }

  // UPDATE
  updateSupplier(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.supplierService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/supplier/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Cannot update Ã¢ÂÅ’ ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }

  // DELETE
  deleteSupplier(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.supplierService.delete(Number(id)).subscribe({
      next: () => {
        alert('Supplier deleted successfully Ã¢Å“â€¦');
        this.router.navigate(['/supplier/list']);
      },
      error: (err) => {
        console.error(err);
        alert('Delete failed Ã¢ÂÅ’');
      }
    });
  }
}
