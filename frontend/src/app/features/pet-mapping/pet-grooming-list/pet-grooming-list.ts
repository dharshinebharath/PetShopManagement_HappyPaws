import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetGroomingMappingService } from '../../../core/services/pet-grooming-mapping-service';


@Component({
  selector: 'app-pet-grooming-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-grooming-list.html'
})
export class PetGroomingList {

  route = inject(ActivatedRoute);
  service = inject(PetGroomingMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  services: any[] = [];

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
    this.service.getGroomingByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        // 🔥 IMPORTANT FIX
        this.services = res?.data || [];

        console.log("Assigned services:", this.services);

        // 🔥 FORCE UI UPDATE
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('No services found ❌');
        this.services = [];
      }
    });
  }

  // ✅ DELETE API
  remove(serviceId: number) {
    this.service.removeGrooming(this.petId!, serviceId).subscribe({
      next: () => {
        alert('Removed successfully ✅');
        this.load(); // reload
      },
      error: () => alert('Delete failed ❌')
    });
  }
}