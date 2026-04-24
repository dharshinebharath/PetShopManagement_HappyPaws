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
  viewPets() {
    if (!this.getEmployeeId) {
      alert('Enter Employee ID ⚠️Â');
      return;
    }

    this.service.getPetsByEmployee(Number(this.getEmployeeId)).subscribe({
      next: (res: any) => {

        if (!res || !res.data || res.data.length === 0) {
          alert('No pets found ❌');
          return;
        }

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.getEmployeeId }
        });
      },
      error: () => alert('Invalid Employee ID ❌')
    });
  }
  assign() {
    if (!this.postEmployeeId || !this.postPetId) {
      alert('Enter both IDs ⚠️Â');
      return;
    }

    this.service.assignPet(
      Number(this.postEmployeeId),
      Number(this.postPetId)
    ).subscribe({
      next: () => {
        alert('Assigned Successfully ✅');

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.postEmployeeId }
        });
      },
      error: () => alert('Assign Failed ❌')
    });
  }
  viewEmployees() {
    if (!this.getPetId) {
      alert('Enter Pet ID ⚠️Â');
      return;
    }

    this.service.getEmployeesByPet(Number(this.getPetId)).subscribe({
      next: (res: any) => {

        if (!res || !res.data || res.data.length === 0) {
          alert('No employees found ❌');
          return;
        }

        this.router.navigate(['/employee-pet-mapping/employees-list'], {
          queryParams: { petId: this.getPetId }
        });
      },
      error: () => alert('Invalid Pet ID ❌')
    });
  }
  remove() {
    if (!this.deleteEmployeeId || !this.deletePetId) {
      alert('Enter both IDs ⚠️Â');
      return;
    }

    this.service.removePet(
      Number(this.deleteEmployeeId),
      Number(this.deletePetId)
    ).subscribe({
      next: () => {
        alert('Deleted Successfully ✅');

        this.router.navigate(['/employee-pet-mapping/list'], {
          queryParams: { employeeId: this.deleteEmployeeId }
        });
      },
      error: () => alert('Delete Failed ❌')
    });
  }
}

