import { ChangeDetectorRef, Component } from '@angular/core';
import { Customer } from '../../../core/services/customer';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer-list',
  imports: [CommonModule],
    standalone: true,  
  templateUrl: './customer-list.html',
  styleUrl: './customer-list.css',
})
export class CustomerList {
  customers: any[] = [];

  constructor(private customerService: Customer,  private cdr: ChangeDetectorRef) {}

   ngOnInit() {
    this.customerService.getAllCustomers().subscribe({
      next: (response: any) => {
        console.log("API Response:", response);

        this.customers = response.data;  
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}