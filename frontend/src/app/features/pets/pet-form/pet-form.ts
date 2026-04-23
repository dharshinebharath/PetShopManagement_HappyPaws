import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PetsService } from '../../../core/services/petsService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pet-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pet-form.html'
})
export class PetForm {

  petsService = inject(PetsService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  petId: number | null = null;

  formData: any = {
    name: '',
    breed: '',
    age: 0,
    price: 0,
    description: '',
    categoryId: 0
  };

  isLoading = true;

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.petId = Number(params['id']);

        this.petsService.getById(this.petId).subscribe({
          next: (res: any) => {

            const data = res.data;

            this.formData.name = data.name;
            this.formData.breed = data.breed;
            this.formData.age = data.age;
            this.formData.price = data.price;
            this.formData.description = data.description;

            // 🔥 IMPORTANT
            this.formData.categoryId = data.category_id;

            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Pet not found ❌');
            this.router.navigate(['/pets']);
          }
        });

      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {

    // ✅ VALIDATION
    if (!this.formData.name || !this.formData.price || !this.formData.categoryId) {
      alert('Please fill required fields ⚠️');
      return;
    }

    // ✅ FINAL PAYLOAD (MATCH BACKEND DTO)
    const payload = {
      name: this.formData.name,
      breed: this.formData.breed,
      age: Number(this.formData.age),
      price: Number(this.formData.price),
      description: this.formData.description,

      // 🔥 MUST MATCH DTO FIELD NAME
      category_id: Number(this.formData.categoryId),

      // 🔥 REQUIRED FIELD (VERY IMPORTANT)
      image_url: "default.jpg"
    };

    console.log("FINAL PAYLOAD 👉", payload);

    // ================= UPDATE =================
    if (this.petId !== null && this.petId !== undefined) {

      this.petsService.update(this.petId, payload).subscribe({
        next: () => {
          alert('Updated successfully ✅');
          this.router.navigate(['/pets/list']).then(() => {
  window.location.reload();
});
        },
        error: (err) => {
          console.log(err);
          alert('Update failed ❌');
        }
      });

    } else {

      // ================= CREATE =================
      this.petsService.create([payload]).subscribe({
        next: () => {
          alert('Created successfully ✅');
          this.router.navigate(['/pets/list']);
        },
        error: (err) => {
          console.log(err);
          alert('Create failed ❌');
        }
      });
    }
  }
}