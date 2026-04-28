// This file holds the Angular logic for pet grooming list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { PetGroomingMappingService } from '../../../core/services/pet-grooming-mapping-service';


@Component({
  selector: 'app-pet-grooming-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './pet-grooming-list.html'
})

export class PetGroomingList {
  route = inject(ActivatedRoute);
  service = inject(PetGroomingMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  services: any[] = [];

  // Initialize the component
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      // Get pet ID from route parameters
      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }

  // Load grooming services
  load() {
    this.service.getGroomingByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        this.services = res?.data || [];

        console.log("Assigned services:", this.services);

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('No services assigned to this pet');
        this.services = [];
      }
    });
  }

  // Remove grooming service
  remove(serviceId: number) {
    this.service.removeGrooming(this.petId!, serviceId).subscribe({
      next: () => {
        alert('Removed successfully');
        this.load();
      },
      error: () => alert('Delete failed')
    });
  }
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


