import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EmployeePetMappingService } from '../../../core/services/employee-pet-mapping-service';

@Component({
  selector: 'app-employees-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-employees-list.html'
})
export class PetEmployeesList {

  route = inject(ActivatedRoute);
  service = inject(EmployeePetMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  employees: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }

  // ✅ GET API
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
}