import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Customer } from '../../../core/services/customer';

@Component({
  selector: 'app-customers-dashboard',
  imports: [RouterModule,CommonModule],
  templateUrl: './customers-dashboard.html',
  styleUrl: './customers-dashboard.css',
})

export class CustomersDashboard {
  constructor(private router: Router, private customerService:Customer) {}
  
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
      next: (data) => {
        console.log(data);
        alert("Check console for data");
      },
      error: () => alert("Customer not found")
    });
  }

    

  editCustomer(id: string) {
    error: (err: { error: { message: any; }; }) => {
  console.log("ADD ERROR:", err);
  alert(err?.error?.message || "Add failed");
}
    console.log('Edit Customer:', id);
 this.router.navigate(['/customer/edit', id]);
  }

  deleteCustomer(id: string) {
    if (!id) {
      alert('Enter Customer ID');
      return;
    }
    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        alert("Deleted successfully");
      },
      error: () => alert("Delete failed")
    });
  }
    // Later you can call API here
    // this.customerService.delete(id).subscribe(...)

  // ================= ADDRESS METHODS =================

  viewAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }
    console.log('View Address:', id);
  }

  editAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }
    console.log('Edit Address:', id);
  }

  deleteAddress(id: string) {
    if (!id) {
      alert('Enter Address ID');
      return;
    }
    console.log('Delete Address:', id);
  }
}
