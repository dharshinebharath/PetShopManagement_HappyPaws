import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './employee-form.html'
})
export class EmployeeForm {

  http = inject(HttpClient);
  route = inject(ActivatedRoute);
  router = inject(Router);
  cdr = inject(ChangeDetectorRef);

  private baseUrl = 'http://localhost:8082/api/employees';

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
    const username = 'Dharshine';
    const password = 'Dharsh123';
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

        // GET BY ID
        this.http.get<any>(`${this.baseUrl}/${this.employeeId}`, this.getAuthHeaders())
          .subscribe({
            next: (res) => {

              const data = res.data; // ⚠️ IMPORTANT (ApiResponse)

              this.formData.firstName = data.firstName;
              this.formData.lastName = data.lastName;
              this.formData.position = data.position;
              this.formData.hireDate = data.hireDate;
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

    // validation
    if (!this.formData.firstName || !this.formData.email) {
      alert('Please fill required fields ⚠️');
      return;
    }

    // UPDATE
    if (this.employeeId !== null && this.employeeId !== undefined) {

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
          if (err.status === 404) {
            alert('Employee not found ❌');
          } else {
            alert('Update failed ❌');
          }
        }
      });

    } else {

      // CREATE
      const payload = [this.formData]; // because backend expects List<EmployeesRequestDTO>

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