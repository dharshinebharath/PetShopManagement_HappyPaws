// This file holds the Angular logic for grooming dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

// Dashboard component for grooming services
@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './grooming-dashboard.html'
})

export class GroomingDashboard {
  // Injecting required services
  router = inject(Router);
  groomingService = inject(GroomingService);

  goToList() {
    // Navigating to the list of grooming services
    this.router.navigate(['/grooming/list']);
  }

  // View service by ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.groomingService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/grooming/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Service ID not found');
        } else {
          alert('Something went wrong');
        }
      }
    });
  }

  // Update service by ID
  updateService(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.groomingService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/grooming/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Cannot update. ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }

  // Delete service by ID
  deleteService(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.groomingService.delete(Number(id)).subscribe({
      next: () => {
        alert('Service deleted successfully');
        this.router.navigate(['/grooming/list']);
      },
      error: () => {
        alert('Delete failed');
      }
    });
  }
}
