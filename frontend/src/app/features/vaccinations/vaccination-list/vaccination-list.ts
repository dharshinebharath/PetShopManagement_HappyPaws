import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { VaccinationService } from '../../../core/services/vaccinationService';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vaccination-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vaccination-list.html',
  styleUrl: './vaccination-list.css',
})
export class VaccinationList {

  vaccinationService = inject(VaccinationService);
  route = inject(ActivatedRoute);
  cdr = inject(ChangeDetectorRef);

  vaccinationList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // ✅ GET BY ID
        this.vaccinationService.getById(id).subscribe({
          next: (res: any) => {

            if (!res || !res.data) {
              alert('No vaccination found with this ID ❌');
              this.vaccinationList = [];
              return;
            }

            this.vaccinationList = [res.data];
            this.cdr.detectChanges();
          },

          error: (err) => {
            alert('Vaccination ID not found ❌');
            this.vaccinationList = [];
            console.log(err);
          }
        });

      } else {
        // ✅ GET ALL
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.vaccinationService.getAll().subscribe({
      next: (res: any) => {
        this.vaccinationList = res.data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log(err)
    });
  }
}