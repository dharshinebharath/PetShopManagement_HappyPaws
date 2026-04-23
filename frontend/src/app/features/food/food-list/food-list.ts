import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FoodService } from '../../../core/services/food';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './food-list.html',
  styleUrl: './food-list.css',
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
            if (!res || !res.data) {
              alert('No food item found with this ID');
              this.router.navigate(['/food-dashboard']);
              return;
            }

            this.foodList = [res.data];
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Food ID not found');
            } else if (err.status === 401) {
              alert('Unauthorized. Please login again');
            } else {
              alert('Something went wrong');
            }

            this.router.navigate(['/food-dashboard']);
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
      error: (err) => {
        console.log(err);
        alert('Failed to load data');
      }
    });
  }
}
