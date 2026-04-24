// This service handles the app-side requests and data flow for employee pet mapping service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class EmployeePetMappingService {

  private baseUrl = 'http://localhost:8081/api/v1';
  http = inject(HttpClient);

  private getAuthHeaders() {
    const auth = btoa('Priyadharshini:Priya123');
    return { headers: { Authorization: `Basic ${auth}` } };
  }
  getPetsByEmployee(employeeId: number) {
    return this.http.get(`${this.baseUrl}/employees/${employeeId}/pets`, this.getAuthHeaders());
  }
  getEmployeesByPet(petId: number) {
    return this.http.get(`${this.baseUrl}/pets/${petId}/employees`, this.getAuthHeaders());
  }
  assignPet(employeeId: number, petId: number) {
    return this.http.post(
      `${this.baseUrl}/employees/${employeeId}/pets/${petId}`,
      {},
      this.getAuthHeaders()
    );
  }
  removePet(employeeId: number, petId: number) {
    return this.http.delete(
      `${this.baseUrl}/employees/${employeeId}/pets/${petId}`,
      this.getAuthHeaders()
    );
  }
  getByRole(role: string) {
    return this.http.get(`${this.baseUrl}/employees/role/${encodeURIComponent(role)}`, this.getAuthHeaders());
  }

  getByHireDate(date: string) {
    return this.http.get(`${this.baseUrl}/employees/hired-after/${date}`, this.getAuthHeaders());
  }
}

