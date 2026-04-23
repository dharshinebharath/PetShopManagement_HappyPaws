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

    const auth = btoa(username + ':' + password);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  // ================= GET =================
  getVaccinationByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/vaccinations`,
      this.getAuthHeaders()
    );
  }

  // ================= POST =================
  assignVaccination(petId: number, vaccinationId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      {},
      this.getAuthHeaders()
    );
  }

  // ================= DELETE =================
  removeVaccination(petId: number, vaccinationId: number) {
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/vaccinations/${vaccinationId}`,
      this.getAuthHeaders()
    );
  }
}
