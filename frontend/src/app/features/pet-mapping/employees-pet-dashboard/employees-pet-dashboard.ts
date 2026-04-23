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

  // ================= GET Pets by Employee =================
  viewPets() {
    if (!this.getEmployeeId) {
      alert('Enter Employee ID ⚠️');
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

  // ================= POST =================
  assign() {
    if (!this.postEmployeeId || !this.postPetId) {
      alert('Enter both IDs ⚠️');
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

  // ================= GET Employees by Pet =================
  viewEmployees() {
    if (!this.getPetId) {
      alert('Enter Pet ID ⚠️');
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

  // ================= DELETE =================
  remove() {
    if (!this.deleteEmployeeId || !this.deletePetId) {
      alert('Enter both IDs ⚠️');
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