import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.css',
})

export class EmployeeList {}

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

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // 🔹 GET BY ID
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
                alert('Something went wrong ⚠️');
              }

              this.router.navigate(['/employee']);
            }
          });

      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.http.get<any>(this.baseUrl, this.getAuthHeaders())
      .subscribe({
        next: (res) => {

          // ⚠️ IMPORTANT: ApiResponse -> data
          this.employeeList = res.data;

          this.cdr.detectChanges();
        },
        error: (err) => {
          console.log(err);
          alert('Failed to load employees');
        }
      });
  }
}

