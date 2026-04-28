// This service handles the app-side requests and data flow for employee pet mapping service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class EmployeePetMappingService {

  private baseUrl = 'http://localhost:8081/api/v1';
  http = inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);
    return { headers: { Authorization: `Basic ${auth}` } };
  }
  
  // Get all pets by employee.
  getPetsByEmployee(employeeId: number) {
    return this.http.get(`${this.baseUrl}/employees/${employeeId}/pets`, this.getAuthHeaders());
  }
  
  // Get all employees by pet.
  getEmployeesByPet(petId: number) {
    return this.http.get(`${this.baseUrl}/pets/${petId}/employees`, this.getAuthHeaders());
  }
  
  // Assign pet to employee.
  assignPet(employeeId: number, petId: number) {
    return this.http.post(
      `${this.baseUrl}/employees/${employeeId}/pets/${petId}`,
      {},
      this.getAuthHeaders()
    );
  }
  
  // Remove pet.
  removePet(employeeId: number, petId: number) {
    return this.http.delete(
      `${this.baseUrl}/employees/${employeeId}/pets/${petId}`,
      this.getAuthHeaders()
    );
  }

  // Get all employees by role.
  getByRole(role: string) {
    return this.http.get(`${this.baseUrl}/employees/role/${encodeURIComponent(role)}`, this.getAuthHeaders());
  }
  // Get all employees by hire date.
  getByHireDate(date: string) {
    return this.http.get(`${this.baseUrl}/employees/hired-after/${date}`, this.getAuthHeaders());
  }
}

