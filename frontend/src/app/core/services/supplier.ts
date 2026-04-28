// This service handles the app-side requests and data flow for supplier.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {

  private baseUrl = 'http://localhost:8081/api/v1/suppliers';

  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Shirlly';
    const password = 'Shirl123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // Get all suppliers.
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  
  // Get supplier by ID.
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
  
  // Create a new supplier.
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  
  // Update a supplier.
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  
  // Delete a supplier.
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

}

