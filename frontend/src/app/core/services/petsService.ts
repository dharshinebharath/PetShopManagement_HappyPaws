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

    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }

  private getFilterAuthHeaders() {
    const username = 'Priyadharshini';
    const password = 'Priya123';
    const auth = btoa(`${username}:${password}`);

    return {
      headers: {
        Authorization: `Basic ${auth}`
      }
    };
  }
getAll() {
  return this.http.get(this.baseUrl, this.getAuthHeaders());
}
getById(id: number) {
  return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}
create(data: any) {
  return this.http.post(this.baseUrl, data, this.getAuthHeaders());
}
update(id: number, data: any) {
  return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
}
delete(id: number) {
  return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
}

getByCategory(id: number) {
  return this.http.get(`${this.baseUrl}/category/${id}`, this.getFilterAuthHeaders());
}

getByBreed(breed: string) {
  return this.http.get(`${this.baseUrl}/breed/${encodeURIComponent(breed)}`, this.getFilterAuthHeaders());
}

getByPrice(min: number, max: number) {
  return this.http.get(
    `${this.baseUrl}/price?min=${min}&max=${max}`,
    this.getFilterAuthHeaders()
  );
}
}

