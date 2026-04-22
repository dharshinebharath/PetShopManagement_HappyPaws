
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { GroomingService } from '../../../core/services/groomingService';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-grooming-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './grooming-list.html',
  styleUrl: './grooming-list.css',
})
export class GroomingList {

  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  groomingList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // 🔹 GET BY ID
        this.groomingService.getById(Number(id)).subscribe({
          next: (res: any) => {

            if (!res || !res.data) {
              alert('No service found with this ID ❌');
              this.router.navigate(['/grooming']);
              return;
            }

            this.groomingList = [res.data];
            this.cdr.detectChanges();
          },

          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Service ID not found ❌');
            } else if (err.status === 401) {
              alert('Unauthorized ❌ Please login again');
            } else {
              alert('Something went wrong ⚠️');
            }

            this.router.navigate(['/grooming']);
          }
        });

      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.groomingService.getAll().subscribe({
      next: (res: any) => {
        this.groomingList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load data');
      }
    });
  }
}


