import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class customer {

  private baseUrl = 'http://localhost:8081/api/v1/customers';

  private http: HttpClient = inject(HttpClient);

  // ================= AUTH HEADERS =================
  private getAuthHeaders() {
    const username = 'Revathi';
    const password = 'Reva123';

    const auth = btoa(`${username}:${password}`);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  // ================= GET ALL =================
  getAllCustomers() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // ================= GET BY ID =================
  getCustomerById(id: string) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // ================= CREATE =================
  addCustomer(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // ================= UPDATE =================
  updateCustomer(id: string, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }

  // ================= DELETE =================
  deleteCustomer(id: string) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}