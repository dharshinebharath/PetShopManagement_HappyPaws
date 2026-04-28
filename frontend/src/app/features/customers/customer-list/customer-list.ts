// This file holds the Angular logic for customer list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';
import { customer } from '../../../core/services/customer';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './customer-list.html',
  styleUrl: './customer-list.css',
})
export class CustomerList {
  customerService = inject(customer);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  customers: any[] = [];
  selectedCustomerId: string | null = null;

  // Initialize the component.
  ngOnInit() {
    // Get customer id from query params.
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      this.selectedCustomerId = id ?? null;

      // Get customer by ID.
      if (id) {
        this.customerService.getCustomerById(id).subscribe({
          next: (res: any) => {
            const data = res.data;

            if (!data) {
              alert('Customer not found');
              this.router.navigate(['/customers-dashboard']);
              return;
            }

            this.customers = [data];
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Customer ID not found');
            } else if (err.status === 401) {
              alert('Unauthorized');
            } else {
              alert('Something went wrong');
            }

            this.router.navigate(['/customers-dashboard']);
          }
        });
      } 
      // Load all customers.
      else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.customerService.getAllCustomers().subscribe({
      next: (res: any) => {
        this.customers = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load customers');
      }
    });
  }
  // Pagination.
  currentPage = 1;
  pageSize = 10;

  // Paginate customers.
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

