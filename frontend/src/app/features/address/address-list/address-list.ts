// This file holds the Angular logic for address list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { AddressService } from '../../../core/services/address';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-address-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './address-list.html'
})
export class AddressList {

  addressService = inject(AddressService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  addressList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {

        this.addressService.getById(Number(id)).subscribe({
          next: (res: any) => {
            this.addressList = [res.data];
            this.cdr.detectChanges();
          },
          error: () => alert('Address not found Ã¢ÂÅ’')
        });

      } else {

        this.loadAll();
      }
    });
  }

  loadAll() {
    this.addressService.getAll().subscribe({
      next: (res: any) => {
        this.addressList = res.data;
        this.cdr.detectChanges();
      },
      error: () => alert('Failed to load addresses Ã¢ÂÅ’')
    });
  }
  currentPage = 1;
  pageSize = 10;

  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

}


