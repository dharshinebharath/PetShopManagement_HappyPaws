import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetFoodMappingService } from '../../../core/services/pet-food-mapping-service';

@Component({
  selector: 'app-pet-food-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-food-list.html'
})
export class PetFoodList {
  route = inject(ActivatedRoute);
  service = inject(PetFoodMappingService);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;
  foods: any[] = [];

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
    this.service.getFoodByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        // 🔥 IMPORTANT FIX
        this.foods = res?.data || [];

        console.log("Assigned foods:", this.foods);

        // 🔥 FORCE UI UPDATE
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('No food found ❌');
        this.foods = [];
      }
    });
  }

  // ✅ DELETE API
  remove(foodId: number) {
    this.service.removeFood(this.petId!, foodId).subscribe({
      next: () => {
        alert('Removed successfully ✅');
        this.load(); // reload
      },
      error: () => alert('Delete failed ❌')
    });
  }
}
