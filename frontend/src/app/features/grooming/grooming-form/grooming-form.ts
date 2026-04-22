
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-grooming-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './grooming-form.html'
})
export class GroomingForm {

  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  serviceId: number | null = null;

  formData: any = {
    name: '',
    description: '',
    price: 0,
    available: true
  };

  isLoading = true;

  // 🔥 IMPORTANT PART (YOU WERE MISSING THIS)
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.serviceId = Number(params['id']);

        // ✅ FETCH EXISTING DATA
        this.groomingService.getById(this.serviceId).subscribe({
          next: (res: any) => {

            const data = res.data;

            // ✅ ASSIGN FIELD BY FIELD (NO DELAY ISSUE)
            this.formData.name = data.name;
            this.formData.description = data.description;
            this.formData.price = data.price;
            this.formData.available = data.available;

            this.isLoading = false;

            // 🔥 FORCE UI UPDATE
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Service not found ❌');
            this.router.navigate(['/grooming']);
          }
        });

      } else {
        // ✅ CREATE MODE
        this.isLoading = false;
      }
    });
  }

  submit() {

  // ✅ validation
  if (!this.formData.name || !this.formData.price) {
    alert('Please fill required fields ⚠️');
    return;
  }

  // 🔥 FIXED CONDITION
  if (this.serviceId !== null && this.serviceId !== undefined) {

    // ================= UPDATE =================
    this.groomingService.update(this.serviceId, this.formData).subscribe({
      next: () => {
        alert('Updated successfully ✅');
        this.router.navigate(['/grooming/list']);
      },
      error: (err) => {
        if (err.status === 404) {
          alert('ID not found ❌');
        } else {
          alert('Update failed ❌');
        }
      }
    });

  } else {

    // ================= CREATE =================
    const payload = [{
      name: this.formData.name,
      description: this.formData.description,
      price: Number(this.formData.price),   // 🔥 FIX
      available: this.formData.available
    }];

    console.log('POST PAYLOAD:', payload); // DEBUG

    this.groomingService.create(payload).subscribe({
      next: () => {
        alert('Created successfully ✅');
        this.router.navigate(['/grooming/list']);
      },
      error: (err) => {
        console.log(err);
        alert('Create failed ❌');
      }
    });
  }
}
}