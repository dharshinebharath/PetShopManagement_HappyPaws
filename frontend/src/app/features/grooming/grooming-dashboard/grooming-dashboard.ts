import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';


@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],




import { HttpClient } from '@angular/common/http';




@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,


  imports: [FormsModule, RouterModule],


  templateUrl: './grooming-dashboard.html'
})
export class GroomingDashboard {


  router = inject(Router);
  groomingService = inject(GroomingService);

  router = inject(Router);
  groomingService = inject(GroomingService);


  // GET ALL
  goToList() {
    this.router.navigate(['/grooming/list']);
  }

  // GET BY ID
  viewById(id: string) {
  if (!id) {
    alert('Please enter ID');
    return;
  }

  // 🔥 CALL API HERE (NOT in list page)
  this.groomingService.getById(Number(id)).subscribe({
    next: (res: any) => {
      // ✅ If found → go to list page
      this.router.navigate(['/grooming/list'], {
        queryParams: { id }
      });
    },
    error: (err) => {
      if (err.status === 404) {
        alert('Service ID not found ❌');
      } else {
        alert('Something went wrong ⚠️');
      }
    }
  });
}

  // ✅ UPDATE (CHECK ID FIRST)
  updateService(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.groomingService.getById(Number(id)).subscribe({
      next: () => {
        // ✅ ID exists → go to form
        this.router.navigate(['/grooming/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Cannot update ❌ ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }

  // DELETE
  deleteService(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.groomingService.delete(Number(id)).subscribe({
      next: () => {
        alert('Service deleted successfully ✅');
              this.router.navigate(['/grooming/list']);

      },
      error: (err) => {
        console.error(err);
        alert('Delete failed ❌');
      }
    });
  }
}
