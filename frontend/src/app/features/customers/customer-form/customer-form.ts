import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './customer-form.html'
})
export class CustomerForm {

  customerService = inject(customer);
  route = inject(ActivatedRoute);
  router = inject(Router);

  customerId: number | null = null;

  // ✅ SINGLE OBJECT (USED IN HTML)
  customer: any = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    address: {
      addressId: null
    }
  };

  isLoading = true;

  // ================= INIT =================
  ngOnInit() {

    this.route.paramMap.subscribe(params => {

      const id = params.get('id');

      if (id) {

        this.customerId = Number(id);

        // ================= GET BY ID =================
        this.customerService.getCustomerById(id).subscribe({
          next: (res: any) => {

            const data = res.data;

            if (!data) {
              alert('Customer not found ❌');
              this.router.navigate(['/customer/list']);
              return;
            }

            // ✅ FIX: assign to SAME object used in HTML
            this.customer.firstName = data.firstName;
            this.customer.lastName = data.lastName;
            this.customer.email = data.email;
            this.customer.phoneNumber = data.phoneNumber;

            if (data.address) {
              this.customer.address.addressId = data.address.addressId;
            }

            this.isLoading = false;
          },
          error: () => {
            alert('Error fetching customer ❌');
            this.router.navigate(['/customer/list']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  // ================= SUBMIT =================
  submitCustomer() {

    // validation
    if (!this.customer.firstName || !this.customer.email) {
      alert('Please fill required fields ⚠️');
      return;
    }

    // ================= UPDATE =================
    if (this.customerId) {

      this.customerService.updateCustomer(
        this.customerId.toString(),
        this.customer
      ).subscribe({
        next: () => {
          alert('Customer updated successfully ✅');
          this.router.navigate(['/customer/list']);
        },
        error: (err) => {
          console.log(err);
          alert(err.error?.message || 'Update failed ❌');
        }
      });

    }

    // ================= CREATE =================
   else {

  const payload = {
    firstName: this.customer.firstName,
    lastName: this.customer.lastName,
    email: this.customer.email,
    phoneNumber: this.customer.phoneNumber,
    addressId: this.customer.address.addressId
  };

  this.customerService.addCustomer([payload]).subscribe({
    next: () => {
      alert('Customer created successfully ✅');
      this.router.navigate(['/customer/list']);
    },
    error: (err) => {
      console.log(err);
      alert(err.error?.message || 'Create failed ❌');
    }
  });

    }
  }
}