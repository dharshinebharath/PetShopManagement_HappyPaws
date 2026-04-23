import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { AddressService } from '../../../core/services/address';

@Component({
  selector: 'app-customers-dashboard',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './customers-dashboard.html',
  styleUrl: './customers-dashboard.css',
})
export class CustomersDashboard {
  router = inject(Router);
  customerService = inject(customer);
  addressService = inject(AddressService);

  activeTab: string = 'customer';

  selectTab(tab: string) {
    this.activeTab = tab;
  }

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
      error: () => alert('Customer not found')
    });
  }

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
        if (err.status === 404) {
          alert('Cannot update, ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }

  deleteCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        alert('Deleted successfully');
      },
      error: () => alert('Delete failed')
    });
  }

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
        if (err.status === 404) {
          alert('Address ID not found');
        } else {
          alert('Something went wrong');
        }
      }
    });
  }

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
        if (err.status === 404) {
          alert('Cannot update. ID not found');
        } else {
          alert('Error checking ID');
        }
      }
    });
  }

  deleteAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }

    this.addressService.delete(Number(id)).subscribe({
      next: () => {
        alert('Address deleted successfully');
      },
      error: () => alert('Delete failed')
    });
  }
}
