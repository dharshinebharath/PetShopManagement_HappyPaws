// This service handles the app-side requests and data flow for pet grooming mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';


// This service is used to manage the assignment of grooming services to pets.
@Injectable({
  providedIn: 'root',
})
// Class for pet grooming mapping service
export class PetGroomingMappingService {
  

  // Constructor for dependency injection
  http = inject(HttpClient);

  // Base URL for the pet grooming mapping API
  private baseUrl = 'http://localhost:8081/api/v1';

  // Method to get authentication headers
  private getAuthHeaders() {
    // Username and password for authentication
    const username = 'Dharshine';
    const password = 'Dharsh123';
    // Encoding the username and password for authentication
    const auth = btoa(username + ':' + password);

    // Returning the authentication headers
    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  // Method to get grooming services for a specific pet
  getGroomingByPet(petId: number) {
    // Making a GET request to the API
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/grooming-services`,
      this.getAuthHeaders()
    );
  }

  // Method to assign a grooming service to a specific pet
  assignGrooming(petId: number, serviceId: number) {
    // Making a POST request to the API
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // Method to remove a grooming service from a specific pet
  removeGrooming(petId: number, serviceId: number) {
    // Making a DELETE request to the API
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      this.getAuthHeaders()
    );
  }
}
