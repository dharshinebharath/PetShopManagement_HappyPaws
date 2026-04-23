import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class transaction {
  private baseUrl = 'http://localhost:8081/api/v1/transactions';
  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const auth = btoa('Revathi:Reva123');

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  getByStatus(status: string) {
    return this.http.get(`${this.baseUrl}/status/${status}`, this.getAuthHeaders());
  }

  getByDateRange(start: string, end: string) {
    return this.http.get(
      `${this.baseUrl}/date-range?startDate=${start}&endDate=${end}`,
      this.getAuthHeaders()
    );
  }

  getByCustomerId(customerId: number) {
    return this.http.get(
      `http://localhost:8081/api/v1/customers/${customerId}/transactions`,
      this.getAuthHeaders()
    );
  }

  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
}
