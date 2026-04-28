// This service handles the app-side requests and data flow for grooming service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GroomingService {

private baseUrl='http://localhost:8081/api/v1/grooming-services' 

http:HttpClient=inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Dharshine';
    const password = 'Dharsh123';

    // Encoding the username and password for authentication
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }
  // Method to get all grooming services
  getAll() {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }
  // Method to get a grooming service by ID
  getById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
  // Method to create a new grooming service
  create(data: any) {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }
  // Method to update a grooming service by ID
  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }
  // Method to delete a grooming service by ID
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }


}

