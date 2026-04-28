// This service handles the app-side requests and data flow for pets service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PetsService {

  private baseUrl = 'http://localhost:8081/api/v1/pets';
  http: HttpClient = inject(HttpClient);

  private getAuthHeaders() {
    const username = 'Mahakarpagam';
    const password = 'Maha123';

    // encode username and password in base64
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  // Get all pets.
getAll() {
  return this.http.get(this.baseUrl, this.getAuthHeaders());
}
// Get pet by ID.
getById(id: number) {
  return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}

// Create a new pet.
create(data: any) {
  return this.http.post(this.baseUrl, data, this.getAuthHeaders());
}

// Update a pet.
update(id: number, data: any) {
  return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
}

// Delete a pet.
delete(id: number) {
  return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}
// Get pets by category.
getByCategory(id: number) {
  return this.http.get(`${this.baseUrl}/category/${id}`, this.getAuthHeaders());
}
// Get pets by breed.
getByBreed(breed: string) {
  return this.http.get(`${this.baseUrl}/breed/${encodeURIComponent(breed)}`, this.getAuthHeaders());
}

// Get pets by price range.
getByPrice(min: number, max: number) {
  return this.http.get(
    `${this.baseUrl}/price?min=${min}&max=${max}`,
    this.getAuthHeaders()
  );
}
}

