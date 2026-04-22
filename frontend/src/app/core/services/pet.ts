import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class Pet {
  private baseUrl = 'http://localhost:8080/api/v1/pets';

  constructor(private http: HttpClient) {}

  getAllPets(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
