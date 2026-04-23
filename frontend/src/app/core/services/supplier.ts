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

    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // GET ALL
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // GET BY ID
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // CREATE
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // UPDATE
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }

  // DELETE
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

}