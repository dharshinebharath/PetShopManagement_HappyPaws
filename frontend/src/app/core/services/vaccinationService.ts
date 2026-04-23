import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class VaccinationService {

  private getAuthHeaders() {
  const username = 'Dharshine';
  const password = 'Dharsh123';

  const auth = btoa(`${username}:${password}`);

  return {
    headers: {
      Authorization: `Basic ${auth}`
    }
  };
}
  private baseUrl = 'http://localhost:8081/api/v1/vaccinations';

  http = inject(HttpClient);
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
}
