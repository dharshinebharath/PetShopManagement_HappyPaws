// This file holds the Angular logic for employee list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.css',
})
export class EmployeeList {

  http = inject(HttpClient);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  private baseUrl = 'http://localhost:8081/api/v1/employees';

  employeeList: any[] = [];

  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // Initialize the component.
  ngOnInit() {
    // Get employee ID from query params.
    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        this.http.get<any>(`${this.baseUrl}/${id}`, this.getAuthHeaders())
          .subscribe({
            next: (res) => {

              if (!res || !res.data) {
                alert('No employee found with this ID ❌');
                this.router.navigate(['/employee']);
                return;
              }

              this.employeeList = [res.data];
              this.cdr.detectChanges();
            },

            error: (err) => {
              console.log(err);

              if (err.status === 404) {
                alert('Employee ID not found ❌');
              } else if (err.status === 401) {
                alert('Unauthorized ❌ Please login again');
              } else {
                alert('Something went wrong ⚠️Â');
              }

              this.router.navigate(['/employee']);
            }
          });

      } else {
        this.loadAll();
      }
    });
  }

  // Load all employees.
  loadAll() {
    this.http.get<any>(this.baseUrl, this.getAuthHeaders())
      .subscribe({
        next: (res) => {

          this.employeeList = res.data;

          this.cdr.detectChanges();
        },
        error: (err) => {
          console.log(err);
          alert('Failed to load employees');
        }
      });
  }
  
  // Pagination.
  currentPage = 1;
  pageSize = 10;

  // Paginate employees.
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

