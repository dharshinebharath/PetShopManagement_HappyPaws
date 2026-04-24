// This file holds the Angular logic for pet supplier list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { PetSupplierMappingService } from '../../../core/services/pet-supplier-mapping-service';

@Component({
  selector: 'app-pet-supplier-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './pet-supplier-list.html'
})
export class PetSupplierList {
  route = inject(ActivatedRoute);
  service = inject(PetSupplierMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  suppliers: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.petId = Number(params['petId']);

      if (this.petId) {
        this.load();
      }
    });
  }

  load() {
    this.service.getSuppliersByPet(this.petId!).subscribe({
      next: (res: any) => {
        this.suppliers = res?.data || [];
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('No suppliers found');
        this.suppliers = [];
      }
    });
  }
  currentPage = 1;
  pageSize = 10;

  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

}

