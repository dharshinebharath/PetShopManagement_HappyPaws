import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { customer } from '../../../core/services/customer';
import { AddressService } from '../../../core/services/address';
// import{address} from '../../../core/services/address';


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

  // 🔹 Tab Switch
  selectTab(tab: string) {
    this.activeTab = tab;
  }

  // ================= CUSTOMER METHODS =================

  viewCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }
    this.customerService.getCustomerById(id).subscribe({
      next: (res) => {
        console.log(res);
        alert('Check console for data');
      },
      error: () => alert('Customer not found ❌')
    });
    this.router.navigate(['/customer/list'], {
      queryParams: { id }
    });
  }

  editCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.router.navigate(['/customer/edit', id]);
  }

  deleteCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }

    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        alert('Deleted successfully ✅');
      },
      error: () => alert('Delete failed ❌')
    });
  }

  // ================= ADDRESS METHODS =================

  // 🔹 GET BY ID
  viewAddress(id: string) {
  if (!id) {
    alert('Enter Address ID');
    return;
  }

  this.router.navigate(['/address/list'], {
    queryParams: { id }
  });
}

  // 🔹 UPDATE (check ID first)
  editAddress(id: string) {
  if (!id) {
    alert('Enter Address ID');
    return;
  }

  this.addressService.getById(Number(id)).subscribe({
    next: () => {

      // ✅ FIXED HERE
      this.router.navigate(['/address/add'], {
        queryParams: { id }
      });

    },
    error: (err) => {
      if (err.status === 404) {
        alert('Cannot update ❌ ID not found');
      } else {
        alert('Error checking ID');
      }
    }
  });
}

  // 🔹 DELETE
  deleteAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }

    this.addressService.delete(Number(id)).subscribe({
      next: () => {
        alert('Address deleted successfully ✅');
      },
      error: () => alert('Delete failed ❌')
    });
  }
}
