import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './login.html'
})
export class Login {

  router = inject(Router);
  route = inject(ActivatedRoute);
  http = inject(HttpClient);

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  login() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();

      const errors: string[] = [];
      const username = this.loginForm.get('username');
      const password = this.loginForm.get('password');

      if (username?.errors) {
        if (username.errors['required']) errors.push('Username is required');
        if (username.errors['minlength']) errors.push('Username must be at least 3 characters');
      }

      if (password?.errors) {
        if (password.errors['required']) errors.push('Password is required');
        if (password.errors['minlength']) errors.push('Password must be at least 6 characters');
      }

      alert('Please fix errors:\n\n' + errors.join('\n'));
      return;
    }

    const username = this.loginForm.value.username!.trim();
    const password = this.loginForm.value.password!;
    const moduleKey = (this.route.snapshot.paramMap.get('module') ?? '').trim();

    const moduleCredentials: Record<string, { route: string; username: string; password: string }> = {
      'pets-module': { route: '/pets-module', username: 'Mahakarpagam', password: 'Maha123' },
      'pet-services-module': { route: '/pet-services-module', username: 'Dharshine', password: 'Dharsh123' },
      'inventory-module': { route: '/inventory-module', username: 'Shirlly', password: 'Shirl123' },
      'customers-module': { route: '/customers-module', username: 'Revati', password: 'Rev123' },
      'custoners-module': { route: '/customers-module', username: 'Revati', password: 'Rev123' },
      'employee-module': { route: '/employee-module', username: 'Priyadharshini', password: 'Priya123' }
    };

    const moduleConfig = moduleCredentials[moduleKey];

    if (!moduleConfig) {
      alert('Invalid module selection');
      return;
    }

    if (username !== moduleConfig.username || password !== moduleConfig.password) {
      alert(`Use the correct username and password for ${moduleConfig.route}`);
      return;
    }

    const headers = {
      Authorization: 'Basic ' + btoa(username + ':' + password)
    };

    this.http.get('http://localhost:8081/api/v1/me', { headers })
      .subscribe({
        next: (res: any) => {
          if (res?.username && res.username !== moduleConfig.username) {
            alert('Logged-in user does not match selected module');
            return;
          }

          this.router.navigateByUrl(moduleConfig.route);
        },
        error: () => {
          alert('Invalid credentials');
        }
      });
  }
}
