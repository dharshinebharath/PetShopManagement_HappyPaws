import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './grooming-dashboard.html'
})
export class GroomingDashboard {


  router = inject(Router);
    groomingService = inject(GroomingService);

  // GET ALL
  goToList() {
    this.router.navigate(['/grooming/list']);
  }

  // GET BY ID
  viewById(id: string) {
    if (!id) return;

    this.router.navigate(['/grooming/list'], {
      queryParams: { id: id }
    });
  }

  updateService(id: string) {
    console.log('update', id);
  }

   deleteService(id: string) {
    if (!id) return;

    this.groomingService.delete(Number(id)).subscribe({
      next: () => {
        alert('Service deleted successfully');
        console.log('Deleted ID:', id);
      },
      error: (err) => {
        console.error('Delete failed', err);
        alert('Failed to delete service');
      }
    });
  }
}