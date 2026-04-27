// This service handles the app-side requests and data flow for vaccination service.
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

// This service is used to manage vaccination records.
@Injectable({
  providedIn: 'root',
})
// Class for vaccination service
export class VaccinationService {

  // Method to get authentication headers
  private getAuthHeaders() {
  const username = 'Dharshine';
  const password = 'Dharsh123';

  // Encoding the username and password for authentication
  const auth = btoa(`${username}:${password}`);

  // Returning the authentication headers
  return {
    headers: {
      Authorization: `Basic ${auth}`
    }
  };
}
// Base URL for the vaccination API
  private baseUrl = 'http://localhost:8081/api/v1/vaccinations';

  http = inject(HttpClient);
// Method to get all vaccinations
getAll() {
  // Making a GET request to the API
  return this.http.get(this.baseUrl, this.getAuthHeaders());
}

// Method to get a vaccination by ID
getById(id: number) {
  // Making a GET request to the API
  return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}

// Method to create a new vaccination
create(data: any) {
  // Making a POST request to the API
  return this.http.post(this.baseUrl, data, this.getAuthHeaders());
}

// Method to update a vaccination
update(id: number, data: any) {
  // Making a PUT request to the API
  return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
}

// Method to delete a vaccination
delete(id: number) {
  return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}
}
