import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { GroomingService } from '../../../core/services/groomingService';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-grooming-list',
  imports: [CommonModule],
  templateUrl: './grooming-list.html',
  styleUrl: './grooming-list.css',
})
export class GroomingList  {

 groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
cdr=inject(ChangeDetectorRef);
  groomingList: any[] = [];

  ngOnInit() {

    // check if id is passed
    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {
        // 🔹 GET BY ID
        this.groomingService.getById(id).subscribe({
          next: (res: any) => {
            this.groomingList = [res.data];
             // wrap single object into array
             this.cdr.detectChanges();
          },
          error: (err) => console.log(err)
        });

      } else {
        // 🔹 GET ALL
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.groomingService.getAll().subscribe({
      next: (res: any) => {
        this.groomingList = res.data; // backend ApiResponse format
                     this.cdr.detectChanges();

      },
      error: (err) => console.log(err)
    });
  }

}
