import { ChangeDetectorRef, Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../../core/services/food';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-food-form',
  imports: [FormsModule],
  templateUrl: './food-form.html',
  styleUrl: './food-form.css',
})
export class FoodForm {
 foodService = inject(FoodService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  foodId: number | null = null;

  formData: any = {
    name: '',
    brand: '',
    type: '',
    quantity: 0,
    price: 0
  };

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.foodId = Number(params['id']);

        this.foodService.getById(this.foodId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.formData.name = data.name;
            this.formData.brand = data.brand;
            this.formData.type = data.type;
            this.formData.quantity = data.quantity;
            this.formData.price = data.price;

            this.cdr.detectChanges();
          },
          error: () => {
            alert('Food not found ❌');
            this.router.navigate(['/food']);
          }
        });

      }
    });
  }

  submit() {

    if (!this.formData.name || !this.formData.price) {
      alert('Fill required fields ⚠️');
      return;
    }

    if (this.foodId !== null) {

      this.foodService.update(this.foodId, this.formData).subscribe({
        next: () => {
          alert('Updated ✅');
          this.router.navigate(['/food/list']);
        },
        error: () => alert('Update failed ❌')
      });

    } else {

      const payload = [this.formData];

      this.foodService.create(payload).subscribe({
        next: () => {
          alert('Created ✅');
          this.router.navigate(['/food/list']);
        },
        error: () => alert('Create failed ❌')
      });
    }
  }
}
