import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';
import { SupplierService } from '../../../core/services/supplier';

@Component({
  selector: 'app-supplier-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './supplier-form.html'
})
export class SupplierForm {

  supplierService = inject(SupplierService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  supplierId: number | null = null;

  formData: any = {
    name: '',
    contact_person: '',
    phone_number: '',
    email: '',
    address_id: null
  };

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.supplierId = Number(params['id']);

        this.supplierService.getById(this.supplierId).subscribe({
          next: (res: any) => {
            const data = res.data;

            this.formData = { ...data };

            this.cdr.detectChanges();
          },
          error: () => {
            alert('Supplier not found ❌');
            this.router.navigate(['/supplier']);
          }
        });

      }
    });
  }

  submit() {

    if (!this.formData.name) {
      alert('Name required ⚠️');
      return;
    }

    if (this.supplierId !== null) {

      this.supplierService.update(this.supplierId, this.formData).subscribe({
        next: () => {
          alert('Updated ✅');
          this.router.navigate(['/supplier/list']);
        },
        error: () => alert('Update failed ❌')
      });

    } else {

      const payload = [this.formData];

      this.supplierService.create(payload).subscribe({
        next: () => {
          alert('Created ✅');
          this.router.navigate(['/supplier/list']);
        },
        error: () => alert('Create failed ❌')
      });
    }
  }
}