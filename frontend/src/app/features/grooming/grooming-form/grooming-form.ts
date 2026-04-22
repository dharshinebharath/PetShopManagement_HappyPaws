
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GroomingService } from '../../../core/services/groomingService';

import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-grooming-form',
  standalone: true,

  imports: [ReactiveFormsModule, CommonModule],

  imports: [FormsModule],

  templateUrl: './grooming-form.html'
})
export class GroomingForm {

  groomingService = inject(GroomingService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  serviceId: number | null = null;

  isLoading = true;

  // ✅ FORM GROUP (UPDATED)
  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [Validators.required, Validators.minLength(5)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    available: new FormControl(true)
  });



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

        // FETCH EXISTING DATA

        this.serviceId = Number(params['id']);

        // ✅ FETCH EXISTING DATA

        this.groomingService.getById(this.serviceId).subscribe({
          next: (res: any) => {

            const data = res.data;


            // PATCH FORM VALUES
            this.form.patchValue({
              name: data.name,
              description: data.description,
              price: data.price,
              available: data.available
            });

            this.isLoading = false;

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

  // 🔥 FORCE VALIDATION CHECK
  if (this.form.invalid) {

    this.form.markAllAsTouched();

    let errors: string[] = [];

    const name = this.form.get('name');
    const desc = this.form.get('description');
    const price = this.form.get('price');

    // ================= NAME =================
    if (name?.errors) {
      if (name.errors['required']) {
        errors.push('Name is required');
      }
      if (name.errors['minlength']) {
        errors.push('Name must be at least 3 characters');
      }
    }

    // ================= DESCRIPTION =================
    if (desc?.errors) {
      if (desc.errors['required']) {
        errors.push('Description is required');
      }
      if (desc.errors['minlength']) {
        errors.push('Description must be at least 5 characters');
      }
    }

    // ================= PRICE =================
    if (price?.errors) {
      if (price.errors['required']) {
        errors.push('Price is required');
      }
      if (price.errors['min']) {
        errors.push('Price must be greater than 0');
      }
    }

    // 🔥 FINAL ALERT (ALL ERRORS TOGETHER)
    alert('❌ Please fix errors:\n\n' + errors.join('\n'));

    return;
  }

  // ================= VALID FORM =================
  const payload = this.form.value;

  if (this.serviceId) {

    this.groomingService.update(this.serviceId, payload).subscribe({

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

      error: () => alert('Update failed ❌')

      error: (err) => {
        if (err.status === 404) {
          alert('ID not found ❌');
        } else {
          alert('Update failed ❌');
        }
      }

    });

  } else {


    this.groomingService.create([payload]).subscribe({

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

      error: () => alert('Create failed ❌')
    });

  }
}
}


      error: (err) => {
        console.log(err);
        alert('Create failed ❌');
      }
    });
  }
}
}

