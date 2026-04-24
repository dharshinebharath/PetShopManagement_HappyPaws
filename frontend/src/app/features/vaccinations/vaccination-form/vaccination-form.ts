// This file holds the Angular logic for vaccination form.
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VaccinationService } from '../../../core/services/vaccinationService';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vaccination-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './vaccination-form.html'
})
export class VaccinationForm {

  vaccinationService = inject(VaccinationService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  serviceId: number | null = null;
  isLoading = true;

  // ================= FORM GROUP =================
  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [Validators.required, Validators.minLength(5)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    available: new FormControl(true)
  });

  // ================= INIT =================
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {

        this.serviceId = Number(params['id']);

        this.vaccinationService.getById(this.serviceId).subscribe({
          next: (res: any) => {

            const data = res.data;

            this.form.patchValue({
              name: data.name,
              description: data.description,
              price: data.price,
              available: data.available
            });

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Vaccination not found ❌');
            this.router.navigate(['/vaccination']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  // ================= SUBMIT =================
  submit() {

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

      alert('❌ Please fix errors:\n\n' + errors.join('\n'));

      return;
    }

    // ================= VALID FORM =================
    const payload = this.form.value;

    if (this.serviceId) {

      this.vaccinationService.update(this.serviceId, payload).subscribe({
        next: () => {
          alert('Updated successfully ✅');
          this.router.navigate(['/vaccination/list']);
        },
        error: () => alert('Update failed ❌')
      });

    } else {

      const createPayload = [{
        name: this.form.value.name,
        description: this.form.value.description,
        price: Number(this.form.value.price),
        available: this.form.value.available
      }];

      this.vaccinationService.create(createPayload).subscribe({
        next: () => {
          alert('Created successfully ✅');
          this.router.navigate(['/vaccination/list']);
        },
        error: () => alert('Create failed ❌')
      });

    }
  }
}
