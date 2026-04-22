import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { SupplierService } from '../../../core/services/supplier';

@Component({
  selector: 'app-supplier-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './supplier-list.html'
})
export class SupplierList {

  supplierService = inject(SupplierService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  supplierList: any[] = [];

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        this.getSupplierById(id);
      } else {
        this.loadAll();
      }
    });
  }

  // ✅ GET SINGLE SUPPLIER
  getSupplierById(id: any) {
    this.supplierService.getById(Number(id)).subscribe({
      next: (res: any) => {

        this.supplierList = [res.data];

        this.cdr.detectChanges();
      },
      error: () => {
        alert('Supplier not found ❌');
        this.router.navigate(['/supplier']);
      }
    });
  }

  // ✅ LOAD ALL SUPPLIERS (FINAL FIX HERE)
  loadAll() {
    this.supplierService.getAll().subscribe({
      next: (res: any) => {

        this.supplierList = res.data;

        // 🔥 CRITICAL FIX → ensure ID is number before sorting
        this.supplierList = this.supplierList.map((s: any) => ({
          ...s,
          id: Number(s.id)   // convert string → number
        }));

        // ✅ SORT PROPERLY
        this.supplierList.sort((a: any, b: any) => a.id - b.id);

        this.cdr.detectChanges();
      },
      error: () => alert('Load failed ❌')
    });
  }

  // ✅ OPTIONAL: Call after update
  refreshAfterUpdate() {
    this.loadAll();
  }

}