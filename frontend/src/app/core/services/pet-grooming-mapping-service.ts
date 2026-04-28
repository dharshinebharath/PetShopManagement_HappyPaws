// This service handles the app-side requests and data flow for pet grooming mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class PetGroomingMappingService {
  

  // Constructor for dependency injection
  http = inject(HttpClient);

  // Base URL for the pet grooming mapping API
  private baseUrl = 'http://localhost:8081/api/v1';

  private getAuthHeaders() {
    const username = 'Dharshine';
    const password = 'Dharsh123';

    // Encoding the username and password for authentication
    const auth = btoa(username + ':' + password);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  // Method to get grooming services for a specific pet
  getGroomingByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/grooming-services`,
      this.getAuthHeaders()
    );
  }

  // Method to assign a grooming service to a specific pet
  assignGrooming(petId: number, serviceId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // Method to remove a grooming service from a specific pet
  removeGrooming(petId: number, serviceId: number) {
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      this.getAuthHeaders()
    );
  }
}
