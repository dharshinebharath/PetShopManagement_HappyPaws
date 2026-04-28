// This file holds the Angular logic for employee reports dashboard.
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../core/services/employee-service';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reports-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './employee-reports-dashboard.html'
})
export class EmployeeReportsDashboard {

  service = inject(EmployeePetMappingService);
  employeeService = inject(EmployeeService);
  router = inject(Router);

  role: string = '';
  date: string = '';
  roleOptions: string[] = [];

  // Load all employees and get roles.
  ngOnInit() {
    this.employeeService.getAll().subscribe({
      next: (res: any) => {
        const employees = res?.data || [];
        const roles = employees
          .map((e: any) => String(e?.position || '').trim())
          .filter((r: string) => !!r);

        this.roleOptions = Array.from(new Set<string>(roles)).sort((a: string, b: string) =>
          a.localeCompare(b)
        );
      },
      error: () => {
        this.roleOptions = [];
      }
    });
  }

  // Get reports by role.
  getByRole() {
    if (!this.role) {
      alert('Enter role ⚠️Â ');
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

  // Get reports by date.
  getByDate() {
    if (!this.date) {
      alert('Select date ⚠️Â ');
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

