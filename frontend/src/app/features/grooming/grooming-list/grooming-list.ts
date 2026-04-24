// This file holds the Angular logic for grooming list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

@Component({
  selector: 'app-grooming-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './grooming-list.html',
  styleUrl: './grooming-list.css',
})
export class GroomingList {
  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  groomingList: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const id = params['id'];

      if (id) {
        this.groomingService.getById(Number(id)).subscribe({
          next: (res: any) => {
            if (!res || !res.data) {
              alert('No service found with this ID');
              this.router.navigate(['/grooming-dashboard']);
              return;
            }

            this.groomingList = [res.data];
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Service ID not found');
            } else if (err.status === 401) {
              alert('Unauthorized. Please login again');
            } else {
              alert('Something went wrong');
            }

            this.router.navigate(['/grooming-dashboard']);
          }
        });
      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.groomingService.getAll().subscribe({
      next: (res: any) => {
        this.groomingList = res.data;
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

