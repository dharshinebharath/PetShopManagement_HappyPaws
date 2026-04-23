import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-employee-pet-list',
  standalone: true,
  imports: [CommonModule],
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
}