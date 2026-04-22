import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
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
  route = inject(ActivatedRoute);
  http = inject(HttpClient);

  moduleName: string = '';

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  ngOnInit() {
    this.moduleName = this.route.snapshot.paramMap.get('module') || '';
  }

  login() {

    const username = this.loginForm.value.username!;
    const password = this.loginForm.value.password!;

    // 🔐 Basic Auth
    const headers = {
      Authorization: 'Basic ' + btoa(username + ':' + password)
    };

    this.http.get('http://localhost:8081/api/v1/me', { headers })
      .subscribe({
        next: (res: any) => {

          const user = res.username;

          // 🎯 ROLE BASED ROUTING
          const routeMap: any = {
            Mahakarpagam: '/pets-services-module',
            Dharshine: '/pet-services-module',
            Revathi: '/customers-module',
            Shirlly: '/inventory-module',
            Priyadharshini: '/employee-module'
          };

          // if backend login works → use user-based routing
          if (routeMap[user]) {
            this.router.navigate([routeMap[user]]);
          }
        },

        error: (err) => {
          console.log('Login failed', err);

          // fallback: module-based login
          const routeMap: any = {
            pets: '/pets-module',
            petservices: '/pet-services-module',
            customers: '/customers-module',
            inventory: '/inventory-module',
            employees: '/employees-module'
          };

          if (routeMap[this.moduleName]) {
            this.router.navigate([routeMap[this.moduleName]]);
          } else {
            alert('Invalid credentials');
          }
        }
      });
  }
}
