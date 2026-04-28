// This service handles the app-side requests and data flow for address.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class AddressService {

  private baseUrl = 'http://localhost:8081/api/v1/addresses';

  http: HttpClient = inject(HttpClient);

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

  // Get all addresses.
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  // Get address by ID.
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // Create a new address.
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // Update an address.
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }

  // Delete an address.
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}

