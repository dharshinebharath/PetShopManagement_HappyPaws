// This file holds the Angular logic for pet vaccination list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { PetVaccinationMappingService } from '../../../core/services/pet-vaccination-mapping-service';

@Component({
  selector: 'app-pet-vaccination-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './pet-vaccination-list.html'
})
export class PetVaccinationList {
  route = inject(ActivatedRoute);
  service = inject(PetVaccinationMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  vaccinations: any[] = [];

  // Initialize the component
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }
  // Method to load vaccinations for a pet
  load() {
    this.service.getVaccinationByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        this.vaccinations = res?.data || [];

        console.log("Assigned vaccinations:", this.vaccinations);

        this.cdr.detectChanges();
      },
      error: () => {
        alert('No vaccinations assigned to this pet');
        this.vaccinations = [];
      }
    });
  }
  // // Method to remove a vaccination for a pet
  // remove(vaccinationId: number) {
  //   this.service.removeVaccination(this.petId!, vaccinationId).subscribe({
  //     next: () => {
  //       alert('Removed successfully');
  //       this.load();
  //     },
  //     error: () => alert('Delete failed')
  //   });
  // }

  
  // Pagination properties
  currentPage = 1;
  pageSize = 10;

  // Method to get paginated list of items
  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  // Method to handle page change
  onPageChange(page: number) {
    this.currentPage = page;
  }

}


