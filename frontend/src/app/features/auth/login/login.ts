import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.html'
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

    // 🔐 Basic Auth header
    const headers = {
      Authorization: 'Basic ' + btoa(username + ':' + password)
    };

    this.http.get('http://localhost:8081/api/v1/me', { headers })
      .subscribe({
        next: (res: any) => {

          const user = res.username;

          // 🎯 ROLE BASED REDIRECT
          const routeMap: any = {
            Mahakarpagam: '/pets-services-module',
            Dharshine: '/pet-services-module',
            Revathi: '/customers-module',
            Shirlly: '/inventory-module',
            Priyadharshini: '/employees-module'
          };

          this.router.navigate([routeMap[user]]);
        },

        error: (err) => {
          console.log('Login failed', err);
          alert('Invalid credentials');
        }
      });
  }
}

import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  route:ActivatedRoute=inject(ActivatedRoute);
  router:Router=inject(Router);
  moduleName:string='';
loginForm: FormGroup=new FormGroup({
  username: new FormControl(''),
  password: new FormControl('')
})

  ngOnInit()
  {
    this.moduleName=this.route.snapshot.paramMap.get('module') || '';
  }
login() {
  if (this.loginForm.value.username === 'admin' && this.loginForm.value.password === 'admin') {

    const routeMap: any = {
      pets: 'pets-module',
      petservices: '/pet-services-module',
      customers: 'customers-module',
      inventory: 'inventory-module',
      employees: 'employees-module'
    };
 
  this.router.navigate(['/'+ routeMap[this.moduleName]])


}

}
}


