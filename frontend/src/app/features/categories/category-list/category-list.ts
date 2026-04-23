import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../core/services/categoryService';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css',
})
export class CategoryList {

  categoryService = inject(CategoryService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  categoryList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // 🔹 GET BY ID
        this.categoryService.getById(Number(id)).subscribe({
          next: (res: any) => {

            console.log('GET BY ID RESPONSE 👉', res);

            if (!res || !res.data) {
              alert('No category found ❌');
              this.router.navigate(['/category/list']);
              return;
            }

            this.categoryList = [res.data];
            this.cdr.detectChanges();
          },

          error: (err) => {
            console.log(err);

            alert('Category not found ❌');
            this.router.navigate(['/category/list']);
          }
        });

      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.categoryService.getAll().subscribe({
      next: (res: any) => {

        console.log('GET ALL RESPONSE 👉', res);

        this.categoryList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load categories');
      }
    });
  }
}