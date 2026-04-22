import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  // ✅ FIXED PORT (make sure backend runs on this)
  private baseUrl = 'http://localhost:8081/api/v1/food';

  private http = inject(HttpClient);

  // ✅ AUTH HEADER
  private getAuthHeaders(): { headers: HttpHeaders } {
    const username = 'Shirlly';
    const password = 'Shirl123';

    const auth = btoa(`${username}:${password}`);

    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${auth}`,
        'Content-Type': 'application/json'
      })
    };
  }

  // 🔹 GET ALL
  getAll(): Observable<any> {
    return this.http.get(this.baseUrl, this.getAuthHeaders());
  }

  // 🔹 GET BY ID
  getById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }

  // 🔹 CREATE
  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data, this.getAuthHeaders());
  }

  // 🔹 UPDATE
  update(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data, this.getAuthHeaders());
  }

  // 🔹 DELETE
  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, this.getAuthHeaders());
  }
}