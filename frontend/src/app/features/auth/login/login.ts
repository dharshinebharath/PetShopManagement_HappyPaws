import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  router = inject(Router);
  http = inject(HttpClient);

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  login() {
    const username = this.loginForm.value.username!;
    const password = this.loginForm.value.password!;

    const headers = {
      Authorization: 'Basic ' + btoa(username + ':' + password)
    };

    this.http.get('http://localhost:8081/api/v1/me', { headers })
      .subscribe({
        next: (res: any) => {
          const user = res.username;

          const routeMap: any = {
            Mahakarpagam: '/pets-services-module',
            Dharshine: '/pet-services-module',
            Revathi: '/customertransaction-module',
            Shirlly: '/inventory-module',
            Priyadharshini: '/employees-module'
          };

          if (routeMap[user]) {
            this.router.navigate([routeMap[user]]);
          }
        },
        error: (err) => {
          console.log('Login failed', err);
          alert('Invalid credentials');
        }
      });
  }
}
