// This service handles the app-side requests and data flow for pet supplier mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PetSupplierMappingService {
  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8081/api/v1';

  private getAuthHeaders() {
    const username = 'Shirlly';
    const password = 'Shirl123';

    const auth = btoa(username + ':' + password);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }

  getSuppliersByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/suppliers`,
      this.getAuthHeaders()
    );
  }

  assignSupplier(petId: number, supplierId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/suppliers/${supplierId}`,
      {},
      this.getAuthHeaders()
    );
  }
}
