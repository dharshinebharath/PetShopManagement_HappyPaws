import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { customer } from '../../../core/services/customer';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule],
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

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];
      this.selectedCustomerId = id ?? null;

      if (id) {

        // ================= GET BY ID =================
        this.customerService.getCustomerById(id).subscribe({
          next: (res: any) => {

            console.log("GET BY ID RESPONSE:", res);

            const data = res.data;

            if (!data) {
              alert('Customer not found ❌');
              this.router.navigate(['/customers']);
              this.router.navigate(['/customers-dashboard']);
              return;
            }

            this.customers = [data]; // show single result
            this.cdr.detectChanges();
          },
          error: (err) => {

            console.log(err);

            if (err.status === 404) {
              alert('Customer ID not found ❌');
            } else if (err.status === 401) {
              alert('Unauthorized ❌');
            } else {
              alert('Something went wrong ⚠️');
            }
 this.router.navigate(['/customers']);
            this.router.navigate(['/customers-dashboard']);
          }
        });

      } else {

        // ================= GET ALL =================
        this.loadAll();
      }
    });
  }

  // ================= LOAD ALL =================
  loadAll() {

    this.customerService.getAllCustomers().subscribe({
      next: (res: any) => {

        console.log("ALL CUSTOMERS:", res);

        this.customers = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load customers ❌');
      }
    });
  }
}
