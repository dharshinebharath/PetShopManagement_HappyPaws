import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../core/services/employee-service';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-reports-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './employee-reports-dashboard.html'
})
export class EmployeeReportsDashboard {

  service = inject(EmployeePetMappingService);
  router = inject(Router);

  role: string = '';
  date: string = '';

  // ================= ROLE =================
  getByRole() {
    if (!this.role) {
      alert('Enter role ⚠️');
      return;
    }

    this.service.getByRole(this.role).subscribe({
      next: (res: any) => {

        if (!res?.data?.length) {
          alert('No employees found ❌');
          return;
        }

        this.router.navigate(['/employee-reports/list'], {
          queryParams: { type: 'role', value: this.role }
        });
      },
      error: () => alert('Error ❌')
    });
  }

  // ================= DATE =================
  getByDate() {
    if (!this.date) {
      alert('Select date ⚠️');
      return;
    }

    this.service.getByHireDate(this.date).subscribe({
      next: (res: any) => {

        if (!res?.data?.length) {
          alert('No employees found ❌');
          return;
        }

        this.router.navigate(['/employee-reports/list'], {
          queryParams: { type: 'date', value: this.date }
        });
      },
      error: () => alert('Error ❌')
    });
  }
}