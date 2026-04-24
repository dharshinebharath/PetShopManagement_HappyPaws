// This file holds the Angular logic for pet food list.
import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { PetFoodMappingService } from '../../../core/services/pet-food-mapping-service';

@Component({
  selector: 'app-pet-food-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
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

  load() {
    this.service.getFoodByPet(this.petId!).subscribe({
      next: (res: any) => {

        console.log("API RESPONSE:", res);

        this.foods = res?.data || [];

        console.log("Assigned foods:", this.foods);

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('No food found ❌');
        this.foods = [];
      }
    });
  }

  remove(foodId: number) {
    this.service.removeFood(this.petId!, foodId).subscribe({
      next: () => {
        alert('Removed successfully ✅');
        this.load();
      },
      error: () => alert('Delete failed ❌')
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


