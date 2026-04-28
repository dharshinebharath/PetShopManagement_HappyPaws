// This service handles the app-side requests and data flow for pet food mapping service.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PetFoodMappingService {
  http = inject(HttpClient);

  private baseUrl = 'http://localhost:8081/api/v1';

  private getAuthHeaders() {
    const username = 'Shirlly';
    const password = 'Shirl123';

    // encode username and password in base64
    const auth = btoa(username + ':' + password);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`
      })
    };
  }
// Get food by pet.
  getFoodByPet(petId: number) {
    return this.http.get(
      `${this.baseUrl}/pets/${petId}/food`,
      this.getAuthHeaders()
    );
  }
// Assign food to pet.
  assignFood(petId: number, foodId: number) {
    return this.http.post(
      `${this.baseUrl}/pets/${petId}/food/${foodId}`,
      {},
      this.getAuthHeaders()
    );
  }
  // Remove food from pet.
  removeFood(petId: number, foodId: number) {
    return this.http.delete(
      `${this.baseUrl}/pets/${petId}/food/${foodId}`,
      this.getAuthHeaders()
    );
  }
}
