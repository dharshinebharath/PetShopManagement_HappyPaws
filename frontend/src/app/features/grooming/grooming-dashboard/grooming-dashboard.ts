
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,

  imports: [FormsModule, RouterModule],

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

  baseUrl = 'http://localhost:8082/api/v1';
  response: any;

  petId: number = 0;
  serviceId: number = 0;
  id: number = 0;

  constructor(private http: HttpClient) {}

  // 🔹 CRUD

  getAll() {
    this.http.get(`${this.baseUrl}/grooming-services`)
      .subscribe(res => this.response = res);
  }

  getById() {
    this.http.get(`${this.baseUrl}/grooming-services/${this.id}`)
      .subscribe(res => this.response = res);
  }

  create() {
    const data = { name: 'Wash', price: 200 };
    this.http.post(`${this.baseUrl}/grooming-services`, data)
      .subscribe(res => this.response = res);
  }

  update() {
    const data = { name: 'Updated Service', price: 300 };
    this.http.put(`${this.baseUrl}/grooming-services/${this.id}`, data)
      .subscribe(res => this.response = res);
  }

  delete() {
    this.http.delete(`${this.baseUrl}/grooming-services/${this.id}`)
      .subscribe(res => this.response = res);
  }

  // 🔹 RELATIONSHIP

  assignService() {
    this.http.post(`${this.baseUrl}/pets/${this.petId}/grooming-services/${this.serviceId}`, {})
      .subscribe(res => this.response = res);
  }

  getPetServices() {
    this.http.get(`${this.baseUrl}/pets/${this.petId}/grooming-services`)
      .subscribe(res => this.response = res);
  }

  removeService() {
    this.http.delete(`${this.baseUrl}/pets/${this.petId}/grooming-services/${this.serviceId}`)
      .subscribe(res => this.response = res);

  }
}