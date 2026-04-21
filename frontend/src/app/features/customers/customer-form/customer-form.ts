import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../../../core/services/customer';


@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer-form.html',
  styleUrl: './customer-form.css',
})
export class CustomerForm implements OnInit {

  customer: any = this.getEmptyCustomer();
  customerId!: number;
  isEdit = false;

  constructor(
    private customerService: Customer,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  // ================= LOAD FOR EDIT =================
  ngOnInit() {

    const idParam = this.route.snapshot.paramMap.get('id');

    // 👉 STRICT CHECK
    if (idParam) {

      this.customerId = Number(idParam);
      this.isEdit = true;

      // STEP 1: validate ID exists from backend
      this.customerService.getCustomerById(idParam).subscribe({
        next: (data) => {

          if (!data) {
            alert("Customer ID not found");
            this.router.navigate(['/customer/dashboard']);
            return;
          }

          // STEP 2: load data
          this.customer = data;

          if (!this.customer.address) {
            this.customer.address = { addressId: null };
          }
        },
        error: () => {
          alert("Invalid Customer ID");
          this.router.navigate(['/customer/dashboard']);
        }
      });

    } else {
      // if no ID → not allowed in edit page
      alert("No Customer ID provided");
      this.router.navigate(['/customer/dashboard']);
    }
  }

  // ================= SUBMIT =================
  submitCustomer() {

    // 🔴 STRICT RULE: only update allowed in edit mode
    if (!this.isEdit || !this.customerId) {
      alert("Invalid edit operation");
      return;
    }

    const updatePayload = {
      firstName: this.customer.firstName,
      lastName: this.customer.lastName,
      email: this.customer.email,
      phoneNumber: this.customer.phoneNumber
    };

    this.customerService.updateCustomer(String(this.customerId, updatePayload)).subscribe({
      next: () => {
        alert("Customer updated successfully");
        this.router.navigate(['/customer/dashboard']);
      },
      error: (err) => {
        console.log(err);
        alert(err.error?.message || "Update failed");
      }
    });
  }

  // ================= CLOSE =================
  closeForm() {
    this.router.navigate(['/customer/dashboard']);
  }

  // ================= EMPTY =================
  getEmptyCustomer() {
    return {
      customerId: null,
      firstName: '',
      lastName: '',
      email: '',
      phoneNumber: '',
      address: { addressId: null }
    };
  }
}