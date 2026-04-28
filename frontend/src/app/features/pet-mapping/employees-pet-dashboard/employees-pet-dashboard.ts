// This file holds the Angular logic for employees pet dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-employee-pet-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './employees-pet-dashboard.html'
})
export class EmployeePetDashboard {

  service = inject(EmployeePetMappingService);
  router = inject(Router);

  getEmployeeId: string = '';
  postEmployeeId: string = '';
  postPetId: string = '';
  getPetId: string = '';
  deleteEmployeeId: string = '';
  deletePetId: string = '';
  
  // View pets of an employee.
  viewPets() {
    if (!this.getEmployeeId) {
      alert('Enter Employee ID');
      return;
    }

    this.service.getPetsByEmployee(Number(this.getEmployeeId)).subscribe({
      next: (res: any) => {

        if (!res || !res.data || res.data.length === 0) {
          alert('No pets found');
          return;
        }

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.getEmployeeId }
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
  assign() {
    if (!this.postEmployeeId || !this.postPetId) {
      alert('Enter both IDs');
      return;
    }

    this.service.assignPet(
      Number(this.postEmployeeId),
      Number(this.postPetId)
    ).subscribe({
      next: () => {
        alert('Assigned Successfully');

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.postEmployeeId }
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
  
  // View employees of a pet.
  viewEmployees() {
    if (!this.getPetId) {
      alert('Enter Pet ID');
      return;
    }

    this.service.getEmployeesByPet(Number(this.getPetId)).subscribe({
      next: (res: any) => {

        if (!res || !res.data || res.data.length === 0) {
          alert('No employees found');
          return;
        }

        this.router.navigate(['/employee-pet-mapping/employees-list'], {
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
  
  // Remove a pet-employee mapping.
  remove() {
    if (!this.deleteEmployeeId || !this.deletePetId) {
      alert('Enter both IDs');
      return;
    }

    this.service.removePet(
      Number(this.deleteEmployeeId),
      Number(this.deletePetId)
    ).subscribe({
      next: () => {
        alert('Deleted Successfully');

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.deleteEmployeeId }
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

