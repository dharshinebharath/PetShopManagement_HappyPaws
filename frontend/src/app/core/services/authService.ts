// This service handles the app-side requests and data flow for auth service.
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient) {}

  // Method to handle user login
  login(username: string, password: string) {

    // Creating headers for authentication
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password)
    });

    // Making a GET request to the server for authentication
    return this.http.get('http://localhost:8081/api/v1/pets', {
      headers,
      responseType: 'json'
    });
  }
}
