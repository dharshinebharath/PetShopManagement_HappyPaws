import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-reports-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-reports-list.html'
})
export class EmployeeReportsList {

  route = inject(ActivatedRoute);
  service = inject(EmployeePetMappingService);
  cdr = inject(ChangeDetectorRef);

  employees: any[] = [];

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

if (type === 'date') {
  this.service.getByHireDate(value).subscribe((res: any) => {
    this.employees = res?.data || [];
    this.cdr.detectChanges();
  });
}

    });
  }
}