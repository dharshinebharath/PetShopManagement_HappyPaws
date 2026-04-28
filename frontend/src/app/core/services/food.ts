// This service handles the app-side requests and data flow for food.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  private baseUrl = 'http://localhost:8081/api/v1/food';

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
  // Get all food.
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  // Get food by ID.
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
  // Create a new food.
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  // Update a food.
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  // Delete a food.
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

}

