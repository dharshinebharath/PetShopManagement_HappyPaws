// This service handles the app-side requests and data flow for transaction.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class transaction {
  private baseUrl = 'http://localhost:8081/api/v1/transactions';
  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    // encode username and password in base64
    const auth = btoa('Revathi:Reva123');

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // Get all transactions.
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // Get transaction by ID.
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // Get transactions by status.
  getByStatus(status: string) {
    return this.http.get(`${this.baseUrl}/status/${status}`, this.getAuthHeaders());
  }

  // Get transactions by date range.
  getByDateRange(start: string, end: string) {
    return this.http.get(
      `${this.baseUrl}/date-range?startDate=${start}&endDate=${end}`,
      this.getAuthHeaders()
    );
  }

  // Get transactions by customer ID.
  getByCustomerId(customerId: number) {
    return this.http.get(
      `http://localhost:8081/api/v1/customers/${customerId}/transactions`,
      this.getAuthHeaders()
    );
  }

  // Create a new transaction.
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // Update a transaction.
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
}
