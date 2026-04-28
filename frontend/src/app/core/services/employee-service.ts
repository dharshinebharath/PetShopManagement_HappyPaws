// This service handles the app-side requests and data flow for employee service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8081/api/v1/employees';

  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }
  
  // Get all employees.
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  // Get employee by ID.
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // Create a new employee.
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  // Update an employee.
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  // Delete an employee.
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}

