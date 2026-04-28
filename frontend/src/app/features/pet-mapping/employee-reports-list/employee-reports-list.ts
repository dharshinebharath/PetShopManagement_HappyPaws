// This file holds the Angular logic for employee reports list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';

import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-reports-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './employee-reports-list.html'
})
export class EmployeeReportsList {

  route = inject(ActivatedRoute);
  service = inject(EmployeePetMappingService);
  cdr = inject(ChangeDetectorRef);

  employees: any[] = [];

  // Load all employees by role or date.
  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      const type = params['type'];
      const value = params['value'];

      if (type === 'role') {
  this.service.getByRole(value).subscribe((res: any) => {
    this.employees = res?.data || [];
    this.cdr.detectChanges();
  });
}

// Get employees by date.
if (type === 'date') {
  this.service.getByHireDate(value).subscribe((res: any) => {
    this.employees = res?.data || [];
    this.cdr.detectChanges();
  });
}

    });
  }
  currentPage = 1;
  pageSize = 10;

  // Paginate employees.
  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  // Handle page change.
  onPageChange(page: number) {
    this.currentPage = page;
  }

}

