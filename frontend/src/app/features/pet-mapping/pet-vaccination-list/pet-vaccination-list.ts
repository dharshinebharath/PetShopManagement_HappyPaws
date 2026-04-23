import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetVaccinationMappingService } from '../../../core/services/pet-vaccination-mapping-service';

@Component({
  selector: 'app-pet-vaccination-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-vaccination-list.html'
})
export class PetVaccinationList {

  route = inject(ActivatedRoute);
  service = inject(PetVaccinationMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  vaccinations: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }

  // ================= GET =================
  load() {
    this.service.getVaccinationByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        this.vaccinations = res?.data || [];

        console.log("Assigned vaccinations:", this.vaccinations);

        this.cdr.detectChanges();
      },
      error: () => {
        alert('No vaccinations found ❌');
        this.vaccinations = [];
      }
    });
  }

  // ================= DELETE =================
  remove(vaccinationId: number) {
    this.service.removeVaccination(this.petId!, vaccinationId).subscribe({
      next: () => {
        alert('Removed successfully ✅');
        this.load();
      },
      error: () => alert('Delete failed ❌')
    });
  }
}