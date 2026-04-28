// This file holds the Angular logic for employee dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './employee-dashboard.html'
})
export class EmployeeDashboard {

  router = inject(Router);
  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8081/api/v1/employees';
  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }
  // Go to employee list.
  goToList() {
    this.router.navigate(['/employee/list']);
  }
  // View employee by ID.
  viewById(id: string) {
    if (!id) {
      alert('Please enter ID');
      return;
    }

    this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders()).subscribe({
      next: (res: any) => {
        this.router.navigate(['/employee/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        if (err.status === 404) {
          alert('Employee ID not found ❌');
        } else {
          alert('Something went wrong ⚠️Â ');
        }
      }
    });
  }
  // Update employee by ID.
  updateEmployee(id: string) {
    if (!id) {
      alert('Enter ID to update');
      return;
    }

    this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders()).subscribe({
      next: () => {
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
  // Delete employee by ID.
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

