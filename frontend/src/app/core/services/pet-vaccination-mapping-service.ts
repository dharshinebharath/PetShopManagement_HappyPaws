// This service handles the app-side requests and data flow for pet vaccination mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class PetVaccinationMappingService {

  http = inject(HttpClient);

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

  // Method to get vaccinations for a specific pet
  getVaccinationByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/vaccinations`,
      this.getAuthHeaders()
    );
  }

  // Method to assign a vaccination to a specific pet
  assignVaccination(petId: number, vaccinationId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // Method to remove a vaccination from a specific pet
  removeVaccination(petId: number, vaccinationId: number) {
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      this.getAuthHeaders()
    );
  }
}

