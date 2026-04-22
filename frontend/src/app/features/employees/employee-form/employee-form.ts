import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './employee-form.html'
})
export class EmployeeForm {

  http = inject(HttpClient);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  private baseUrl = 'http://localhost:8081/api/v1/employees';

  employeeId: number | null = null;

  formData: any = {
    firstName: '',
    lastName: '',
    position: '',
    hireDate: '',
    phoneNumber: '',
    email: '',
    addressId: null
  };

  isLoading = true;

  // AUTH
  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      if (params['id']) {
        this.employeeId = Number(params['id']);

        this.http.get<any>(`${this.baseUrl}/${this.employeeId}`, this.getAuthHeaders())
          .subscribe({
            next: (res) => {

              const data = res.data;

              this.formData.firstName = data.firstName;
              this.formData.lastName = data.lastName;
              this.formData.position = data.position;
              this.formData.hireDate = data.hireDate
                ? new Date(data.hireDate).toISOString().split('T')[0]
                : '';
              this.formData.phoneNumber = data.phoneNumber;
              this.formData.email = data.email;
              this.formData.addressId = data.addressId;

              this.isLoading = false;
              this.cdr.detectChanges();
            },
            error: () => {
              alert('Employee not found ❌');
              this.router.navigate(['/employee/list']);
            }
          });

      } else {
        this.isLoading = false;
      }
    });
  }

  submit() {

    // ✅ VALIDATION BLOCK
    if (
      !this.formData.firstName ||
      !this.formData.lastName ||
      !this.formData.position ||
      !this.formData.hireDate ||
      !this.formData.phoneNumber ||
      !this.formData.email
    ) {
      alert('Please fill all required fields ⚠️');
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.formData.email)) {
      alert('Invalid email format ⚠️');
      return;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(this.formData.phoneNumber)) {
      alert('Phone must be 10 digits ⚠️');
      return;
    }

    // UPDATE
    if (this.employeeId !== null && this.employeeId !== undefined) {

      if (this.formData.hireDate) {
        this.formData.hireDate = this.formData.hireDate.split('T')[0];
      }

      this.http.put(
        `${this.baseUrl}/${this.employeeId}`,
        this.formData,
        this.getAuthHeaders()
      ).subscribe({
        next: () => {
          alert('Updated successfully ✅');
          this.router.navigate(['/employee/list']);
        },
        error: (err) => {
          console.log("FULL ERROR:", err);
          console.log("BACKEND MSG:", err.error);

          if (err.status === 404) {
            alert('Employee not found ❌');
          } else {
            alert('Update failed ❌');
          }
        }
      });

    } else {

      const payload = [this.formData];

      this.http.post(this.baseUrl, payload, this.getAuthHeaders())
        .subscribe({
          next: () => {
            alert('Created successfully ✅');
            this.router.navigate(['/employee/list']);
          },
          error: (err) => {
            console.log(err);
            alert('Create failed ❌');
          }
        });
    }
  }
}