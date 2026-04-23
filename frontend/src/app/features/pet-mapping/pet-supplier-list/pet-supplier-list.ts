import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetSupplierMappingService } from '../../../core/services/pet-supplier-mapping-service';

@Component({
  selector: 'app-pet-supplier-list',
  standalone: true,
  imports: [CommonModule],
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
}
