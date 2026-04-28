// This service handles the app-side requests and data flow for customer.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class customer {

  private baseUrl = 'http://localhost:8081/api/v1/customers';

  private http: HttpClient = inject(HttpClient);
  private getAuthHeaders() {
    const username = 'Revathi';
    const password = 'Reva123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }
  // Get all customers.
  getAllCustomers() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  // Get customer by ID.
  getCustomerById(id: string) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
  // Create a new customer.
  addCustomer(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  // Update a customer.
  updateCustomer(id: string, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  // Delete a customer.
  deleteCustomer(id: string) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}

