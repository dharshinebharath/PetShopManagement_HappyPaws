// This service handles the app-side requests and data flow for category service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {

  private baseUrl = 'http://localhost:8081/api/v1/categories';

  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Mahakarpagam';
    const password = 'Maha123';

    const auth = btoa(`${username}:${password}`);

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
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

}

