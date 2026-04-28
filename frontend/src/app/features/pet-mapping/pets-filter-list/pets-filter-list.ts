// This file holds the Angular logic for pets filter list.
import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { PetsService } from '../../../core/services/petsService';


@Component({
  selector: 'app-pets-filter-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './pets-filter-list.html'
})
export class PetsFilterList {

  route = inject(ActivatedRoute);
  service = inject(PetsService);

  pets: any[] = [];

  private handlePetsResponse(res: any) {
    this.pets = res?.data || [];

    if (!this.pets.length) {
      alert('No pets found ❌');
    }
  }

  // Load pets by category, breed or price.
  ngOnInit() {
    const routedPets = (history.state?.pets ?? []) as any[];
    if (Array.isArray(routedPets) && routedPets.length > 0) {
      this.pets = routedPets;
      return;
    }

    this.route.queryParams.subscribe(params => {

      const type = params['type'];

      if (type === 'category') {
        this.service.getByCategory(params['value']).subscribe({
          next: (res: any) => this.handlePetsResponse(res),
          error: () => alert('Error while fetching pets ❌')
        });
      }

      else if (type === 'breed') {
        this.service.getByBreed(params['value']).subscribe({
          next: (res: any) => this.handlePetsResponse(res),
          error: () => alert('Error while fetching pets ❌')
        });
      }

      else if (type === 'price') {
        this.service.getByPrice(params['min'], params['max']).subscribe({
          next: (res: any) => this.handlePetsResponse(res),
          error: () => alert('Error while fetching pets ❌')
        });
      }

    });
  }
  currentPage = 1;
  pageSize = 10;

  // Paginate pets.
  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  // Handle page change.
  onPageChange(page: number) {
    this.currentPage = page;
  }

}

