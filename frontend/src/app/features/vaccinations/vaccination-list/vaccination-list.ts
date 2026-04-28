// This file holds the Angular logic for vaccination list.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { VaccinationService } from '../../../core/services/vaccinationService';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../shared/components/pagination/pagination';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vaccination-list',
  standalone: true,
  imports: [CommonModule, PaginationComponent],
  templateUrl: './vaccination-list.html',
  styleUrl: './vaccination-list.css',
})
// Vaccination list component
export class VaccinationList {

  // Injecting required services
  vaccinationService = inject(VaccinationService);
  route = inject(ActivatedRoute);
  cdr = inject(ChangeDetectorRef);

  // List of vaccinations
  vaccinationList: any[] = [];

  // Initialize the component
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      // Check if vaccination ID exists
      const id = params['id'];

      if (id) {
        this.vaccinationService.getById(id).subscribe({
          next: (res: any) => {

            if (!res || !res.data) {
              alert('No vaccination found with this ID');
              this.vaccinationList = [];
              return;
            }

            this.vaccinationList = [res.data];
            this.cdr.detectChanges();
          },

          error: (err) => {
            alert('Vaccination ID not found');
            this.vaccinationList = [];
            console.log(err);
          }
        });

      } 
      // Load all vaccinations
      else {
        this.loadAll();
      }
    });
  }

  // Method to load all vaccinations
  loadAll() {
    this.vaccinationService.getAll().subscribe({
      next: (res: any) => {
        this.vaccinationList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log(err)
    });
  }

  // Pagination properties
  currentPage = 1;
  pageSize = 10;

  // Method to get paginated items
  paginated<T>(items: T[]): T[] {
    const safe = items || [];
    const start = (this.currentPage - 1) * this.pageSize;
    return safe.slice(start, start + this.pageSize);
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

}

