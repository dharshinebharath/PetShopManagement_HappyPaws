import { Component, inject } from '@angular/core';

import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { FoodService } from '../../../core/services/food';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './food-list.html'
})
export class FoodList {

  foodService = inject(FoodService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  foodList: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        this.foodService.getById(Number(id)).subscribe({
          next: (res: any) => {
            this.foodList = [res.data];
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Food not found ❌');
            this.router.navigate(['/food']);
          }
        });
      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.foodService.getAll().subscribe({
      next: (res: any) => {
        this.foodList = res.data;
        this.cdr.detectChanges();
      },
      error: () => alert('Load failed ❌')
    });
  }
}