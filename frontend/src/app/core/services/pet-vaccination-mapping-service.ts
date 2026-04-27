// This service handles the app-side requests and data flow for pet vaccination mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';


// This service is used to manage the assignment of vaccinations to pets.
@Injectable({
  providedIn: 'root',
})
// Class for pet vaccination mapping service
export class PetVaccinationMappingService {

  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8081/api/v1';

  // Method to get authentication headers
  private getAuthHeaders() {
    // Username and password for authentication
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

  // Method to get vaccinations for a specific pet
  getVaccinationByPet(petId: number) {
    // Making a GET request to the API
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/vaccinations`,
      this.getAuthHeaders()
    );
  }

  // Method to assign a vaccination to a specific pet
  assignVaccination(petId: number, vaccinationId: number) {
    // Making a POST request to the API
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // Method to remove a vaccination from a specific pet
  removeVaccination(petId: number, vaccinationId: number) {
    // Making a DELETE request to the API
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      this.getAuthHeaders()
    );
  }
}

