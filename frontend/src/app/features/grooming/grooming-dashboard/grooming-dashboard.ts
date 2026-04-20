import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-grooming-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './grooming-dashboard.html'
})
export class GroomingDashboard {

  baseUrl = 'http://localhost:8082/api/v1';
  response: any;

  petId: number = 0;
  serviceId: number = 0;
  id: number = 0;

  constructor(private http: HttpClient) {}

  // 🔹 CRUD

  getAll() {
    this.http.get(`${this.baseUrl}/grooming-services`)
      .subscribe(res => this.response = res);
  }

  getById() {
    this.http.get(`${this.baseUrl}/grooming-services/${this.id}`)
      .subscribe(res => this.response = res);
  }

  create() {
    const data = { name: 'Wash', price: 200 };
    this.http.post(`${this.baseUrl}/grooming-services`, data)
      .subscribe(res => this.response = res);
  }

  update() {
    const data = { name: 'Updated Service', price: 300 };
    this.http.put(`${this.baseUrl}/grooming-services/${this.id}`, data)
      .subscribe(res => this.response = res);
  }

  delete() {
    this.http.delete(`${this.baseUrl}/grooming-services/${this.id}`)
      .subscribe(res => this.response = res);
  }

  // 🔹 RELATIONSHIP

  assignService() {
    this.http.post(`${this.baseUrl}/pets/${this.petId}/grooming-services/${this.serviceId}`, {})
      .subscribe(res => this.response = res);
  }

  getPetServices() {
    this.http.get(`${this.baseUrl}/pets/${this.petId}/grooming-services`)
      .subscribe(res => this.response = res);
  }

  removeService() {
    this.http.delete(`${this.baseUrl}/pets/${this.petId}/grooming-services/${this.serviceId}`)
      .subscribe(res => this.response = res);
  }
}