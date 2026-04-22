import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from '../../../core/services/address';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-address-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './address-form.html'
})
export class AddressForm {

  addressService = inject(AddressService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  addressId: number | null = null;

  formData: any = {
    street: '',
    city: '',
    state: '',
    zipCode: ''
  };

  isLoading = true;

  // ================= INIT =================
  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.addressId = Number(params['id']);

        // ✅ FETCH EXISTING ADDRESS
        this.addressService.getById(this.addressId).subscribe({
          next: (res: any) => {

            const data = res.data;

            // ✅ ASSIGN VALUES
            this.formData.street = data.street;
            this.formData.city = data.city;
            this.formData.state = data.state;
            this.formData.zipCode = data.zipCode;

            this.isLoading = false;

            // 🔥 FORCE UI UPDATE
            this.cdr.detectChanges();
          },
          error: () => {
            alert('Address not found ❌');
            this.router.navigate(['/address']);
          }
        });

      } else {
        // CREATE MODE
        this.isLoading = false;
      }
    });
  }

  // ================= SUBMIT =================
  submit() {

    // ✅ validation
    if (!this.formData.street || !this.formData.city) {
      alert('Please fill required fields ⚠️');
      return;
    }

    // ================= UPDATE =================
    if (this.addressId !== null && this.addressId !== undefined) {

      this.addressService.update(this.addressId, this.formData).subscribe({
        next: () => {
          alert('Address updated successfully ✅');
          this.router.navigate(['/address/list']);
        },
        error: (err) => {
          if (err.status === 404) {
            alert('ID not found ❌');
          } else {
            alert('Update failed ❌');
          }
        }
      });

    } 
    
    // ================= CREATE =================
    else {

      const payload = [{
  street: this.formData.street,
  city: this.formData.city,
  state: this.formData.state,
  zipCode: this.formData.zipCode
}];

      console.log('POST PAYLOAD:', payload); // DEBUG

      this.addressService.create(payload).subscribe({
        next: () => {
          alert('Address created successfully ✅');
          this.router.navigate(['/address/list']);
        },
        error: (err) => {
          console.log(err);
          alert('Create failed ❌');
        }
      });
    }
  }
}