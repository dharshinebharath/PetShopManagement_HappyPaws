import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employees-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './employees-dashboard.html'
})
export class EmployeesDashboard {

  router = inject(Router);
  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8082/api/employees';

  // AUTH HEADERS
  private getAuthHeaders() {
    const username = 'Dharshine';
    const password = 'Dharsh123';

    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // GET ALL
  goToList() {
    this.router.navigate(['/employee/list']);
  }

  // GET BY ID
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders()).subscribe({
      next: (res: any) => {
        // if exists → go to list page with query param
        this.router.navigate(['/employee/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Employee ID not found ❌');
        } else {
          alert('Something went wrong ⚠️');
        }
      }
    });
  }

  // UPDATE (CHECK ID FIRST)
  updateEmployee(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders()).subscribe({
      next: () => {
        // ID exists → go to form
        this.router.navigate(['/employee/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Cannot update ❌ Employee not found');
        } else {
          alert('Error checking employee');
        }
      }
    });
  }

  // DELETE
  deleteEmployee(id: string) {
    if (!id) {
      alert('Enter ID to delete');
      return;
    }

    this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders()).subscribe({
      next: () => {
        alert('Employee deleted successfully ✅');
        this.router.navigate(['/employee/list']);
      },
      error: (err) => {
        console.error(err);
        alert('Delete failed ❌');
      }
    });
  }
}