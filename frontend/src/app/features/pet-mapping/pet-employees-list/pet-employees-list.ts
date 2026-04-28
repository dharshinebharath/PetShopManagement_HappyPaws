// This file holds the Angular logic for pet employees list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-employees-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './pet-employees-list.html'
})
export class PetEmployeesList {

  route = inject(ActivatedRoute);
  service = inject(EmployeePetMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  employees: any[] = [];

  // Load all employees assigned to a pet.
  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }

  load() {
    this.service.getEmployeesByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        this.employees = res?.data || [];

        this.cdr.detectChanges();
      },
      error: () => {
        alert('No employees found ❌');
        this.employees = [];
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

  onPageChange(page: number) {
    this.currentPage = page;
  }

}

