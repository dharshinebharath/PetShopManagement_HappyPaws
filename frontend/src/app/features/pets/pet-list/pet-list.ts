import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { PetsService } from '../../../core/services/petsService';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pets-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-list.html',
  styleUrl: './pet-list.css',
})
export class PetsList {

  petService = inject(PetsService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  petsList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // 🔹 GET BY ID
        this.petService.getById(Number(id)).subscribe({
          next: (res: any) => {

              console.log('GET BY ID RESPONSE 👉', res);  // ✅ ADD HERE

            if (!res || !res.data) {
              alert('No pet found with this ID ❌');
              this.router.navigate(['/pets']);
              return;
            }

            this.petsList = [res.data];
            this.cdr.detectChanges();
          },


          error: (err) => {
            console.log(err);

            if (err.status === 404) {
              alert('Pet ID not found ❌');
            } else if (err.status === 401) {
              alert('Unauthorized ❌ Please login again');
            } else {
              alert('Something went wrong ⚠️');
            }

            this.router.navigate(['/pets']);
          }
        });

      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.petService.getAll().subscribe({
      next: (res: any) => {
                      console.log('GET BY ID RESPONSE 👉', res);  // ✅ ADD HERE

        this.petsList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
        alert('Failed to load data');
      }
    });
  }
}