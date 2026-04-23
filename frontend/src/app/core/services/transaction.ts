import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class transaction {

  private baseUrl = 'http://localhost:8081/api/v1/transactions';
  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const auth = btoa(`Revathi:Reva123`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // ================= GET ALL =================
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // ================= GET BY ID =================
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // ================= GET BY STATUS =================
  getByStatus(status: string) {
    return this.http.get(`${this.baseUrl}/status/${status}`, this.getAuthHeaders());
  }

  // ================= GET BY DATE RANGE =================
 getByDateRange(start: string, end: string) {
  return this.http.get(
    `${this.baseUrl}/date-range?startDate=${start}&endDate=${end}`,
    this.getAuthHeaders()
  );
}

  // ================= GET BY CUSTOMER =================
  getByCustomerId(customerId: number) {
    return this.http.get(
      `${this.baseUrl}/customer/${customerId}`,
      this.getAuthHeaders()
    );
  }

  // ================= CREATE ONLY =================
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
}