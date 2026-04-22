import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
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

  goToList() {
    this.router.navigate(['/supplier/list']);
  }

  viewById(id: string) {
    if (!id) {
      alert('Enter ID');
      return;
    }

    this.supplierService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/supplier/list'], { queryParams: { id } });
      },
      error: () => alert('Supplier not found ❌')
    });
  }

  updateSupplier(id: string) {
    if (!id) {
      alert('Enter ID');
      return;
    }

    this.supplierService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/supplier/form'], { queryParams: { id } });
      },
      error: () => alert('ID not found ❌')
    });
  }

  deleteSupplier(id: string) {
    if (!id) {
      alert('Enter ID');
      return;
    }

    this.supplierService.delete(Number(id)).subscribe({
      next: () => {
        alert('Deleted ✅');
        this.router.navigate(['/supplier/list']);
      },
      error: () => alert('Delete failed ❌')
    });
  }
}