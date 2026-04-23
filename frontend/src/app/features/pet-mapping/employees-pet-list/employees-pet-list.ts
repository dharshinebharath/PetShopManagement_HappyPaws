import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-employee-pet-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './employees-pet-list.html'
})
export class EmployeePetList {

  route = inject(ActivatedRoute);
  service = inject(EmployeePetMappingService);
  cdr = inject(ChangeDetectorRef);

  employeeId: number | null = null;
  pets: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.employeeId = Number(params['employeeId']);
      if (this.employeeId) this.load();
    });
  }

  load() {
    this.service.getPetsByEmployee(this.employeeId!).subscribe({
      next: (res: any) => {
        this.pets = res?.data || [];
        this.cdr.detectChanges();
      },
      error: () => {
        alert('No pets found ❌');
        this.pets = [];
      }
    });
  }

  remove(petId: number) {
    this.service.removePet(this.employeeId!, petId).subscribe({
      next: () => {
        alert('Removed successfully ✅');
        this.load();
      },
      error: () => alert('Delete failed ❌')
    });
  }
  currentPage = 1;
  pageSize = 8;

  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

}

