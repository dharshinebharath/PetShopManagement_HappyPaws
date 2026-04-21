import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Customer {
  private http=inject(HttpClient);
   private baseUrl = 'http://localhost:8080/api/v1/customers';
  // GET ALL
  getAllCustomers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  // GET BY ID
  getCustomerById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // POST
  addCustomer(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }

  // PUT
  updateCustomer(id: string, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  // DELETE
  deleteCustomer(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
