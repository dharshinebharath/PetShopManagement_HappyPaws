import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PetGroomingMappingService {

  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8081/api/v1';

  // ✅ AUTH HEADER
  private getAuthHeaders() {
    const username = 'Dharshine';
    const password = 'Dharsh123';

    const auth = btoa(username + ':' + password);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  // ✅ GET
  getGroomingByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/grooming-services`,
      this.getAuthHeaders()
    );
  }

  // ✅ POST
  assignGrooming(petId: number, serviceId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // ✅ DELETE
  removeGrooming(petId: number, serviceId: number) {
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/grooming-services/${serviceId}`,
      this.getAuthHeaders()
    );
  }
}