// This file holds the Angular logic for customers dashboard.
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-customers-dashboard',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './customers-dashboard.html',
  styleUrl: './customers-dashboard.css',
})
export class CustomersDashboard implements OnInit {
  router = inject(Router);
  route = inject(ActivatedRoute);
  customerService = inject(customer);
  addressService = inject(AddressService);

  activeTab: string = 'customer';

  // Initialize the component.
  ngOnInit() {
    // Get active tab from query params.
    this.route.queryParamMap.subscribe(queryParams => {
      const tab = queryParams.get('tab');
      if (tab === 'customer' || tab === 'address') {
        this.activeTab = tab;
      }
    });
  }

  // Select tab.
  selectTab(tab: string) {
    this.activeTab = tab;
  }

  // View customer.
  viewCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.customerService.getCustomerById(id).subscribe({
      next: () => {
        this.router.navigate(['/customer/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Edit customer.
  editCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.customerService.getCustomerById(id).subscribe({
      next: () => {
        this.router.navigate(['customer/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Delete customer.
  deleteCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        alert('Deleted successfully');
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // View address.
  viewAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }

    this.addressService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/address/list'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Edit address.
  editAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }

    this.addressService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['address/form'], {
          queryParams: { id }
        });
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }

  // Delete address.
  deleteAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }

    this.addressService.delete(Number(id)).subscribe({
      next: () => {
        alert('Deleted successfully');
      },
      error: (err) => {
        let msg = 'Something went wrong';

        if (err.error?.message) {
          msg = err.error.message; // for 404, duplicate, etc.
        }
        else if (err.error?.errors) {
          msg = err.error.errors.join('\n'); // for validation list
        }

        alert(msg);
      }
    });
  }
}
