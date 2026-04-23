// import { ChangeDetectorRef, Component, inject } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { CommonModule } from '@angular/common';
// import { AddressService } from '../../../core/services/address';

// @Component({
//   selector: 'app-address-list',
//   standalone: true,
//   imports: [CommonModule],
//   templateUrl: './address-list.html'
// })
// export class AddressList {

//   addressService = inject(AddressService);
//   route = inject(ActivatedRoute);
//   router = inject(Router);
//   cdr = inject(ChangeDetectorRef);

//   addressList: any[] = [];

//   ngOnInit() {

//     this.route.queryParams.subscribe(params => {

//       const id = params['id'];

//       // ❌ No ID → redirect
//       if (!id) {
//         alert('Please provide Address ID ❌');
//         this.router.navigate(['/address']);
//         return;
//       }

//       // ✅ GET BY ID ONLY
//       this.addressService.getById(Number(id)).subscribe({
//         next: (res: any) => {

//           if (!res || !res.data) {
//             alert('Address not found ❌');
//             this.router.navigate(['/address']);
//             return;
//           }

//           this.addressList = [res.data]; // wrap as array for table
//           this.cdr.detectChanges();
//         },

//         error: (err) => {
//           console.log(err);

//           if (err.status === 404) {
//             alert('Address ID not found ❌');
//           } else if (err.status === 401) {
//             alert('Unauthorized ❌');
//           } else {
//             alert('Something went wrong ⚠️');
//           }

//           this.router.navigate(['/address']);
//         }
//       });

//     });
//   }
// }
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { AddressService } from '../../../core/services/address';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-address-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './address-list.html'
})
export class AddressList {

  addressService = inject(AddressService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  addressList: any[] = [];

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      const id = params['id'];

      if (id) {

        // 🔹 GET BY ID
        this.addressService.getById(Number(id)).subscribe({
          next: (res: any) => {
            this.addressList = [res.data];
            this.cdr.detectChanges();
          },
          error: () => alert('Address not found ❌')
        });

      } else {

        // ✅ DEFAULT → GET ALL
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.addressService.getAll().subscribe({
      next: (res: any) => {
        this.addressList = res.data;
        this.cdr.detectChanges();
      },
      error: () => alert('Failed to load addresses ❌')
    });
  }
}