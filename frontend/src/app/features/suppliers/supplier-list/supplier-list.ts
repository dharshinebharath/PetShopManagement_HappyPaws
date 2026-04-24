// This file holds the Angular logic for supplier list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { SupplierService } from '../../../core/services/supplier';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-supplier-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './supplier-list.html',
  styleUrl: './supplier-list.css',
})
export class SupplierList {
  supplierService = inject(SupplierService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  supplierList: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const id = params['id'];

      if (id) {
        this.supplierService.getById(Number(id)).subscribe({
          next: (res: any) => {
            if (!res || !res.data) {
              alert('No supplier found with this ID');
              this.router.navigate(['/supplier-dashboard']);
              return;
            }

            this.supplierList = [res.data];
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Supplier ID not found');
            } else if (err.status === 401) {
              alert('Unauthorized. Please login again');
            } else {
              alert('Something went wrong');
            }

            this.router.navigate(['/supplier-dashboard']);
          }
        });
      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.supplierService.getAll().subscribe({
      next: (res: any) => {
        this.supplierList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load data');
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

