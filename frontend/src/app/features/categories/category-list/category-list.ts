// This file holds the Angular logic for category list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../core/services/categoryService';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
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

      // Get category by ID.
      if (id) {
        this.categoryService.getById(Number(id)).subscribe({
          next: (res: any) => {

            console.log('GET BY ID RESPONSE Ã°Å¸â€˜â€°', res);

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
      }
      // Load all categories.
      else {
        this.loadAll();
      }
    });
  }


  loadAll() {
    this.categoryService.getAll().subscribe({
      next: (res: any) => {

        console.log('GET ALL RESPONSE Ã°Å¸â€˜â€°', res);

        this.categoryList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load categories');
      }
    });
  }
  
  // Pagination.
  currentPage = 1;
  pageSize = 10;

  // Paginate categories.
  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  // Handle page change.
  onPageChange(page: number) {
    this.currentPage = page;
  }

}

