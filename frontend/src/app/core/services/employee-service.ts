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

    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // GET ALL EMPLOYEES
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // GET EMPLOYEE BY ID
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // CREATE EMPLOYEE
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // UPDATE EMPLOYEE
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }

  // DELETE EMPLOYEE
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}
