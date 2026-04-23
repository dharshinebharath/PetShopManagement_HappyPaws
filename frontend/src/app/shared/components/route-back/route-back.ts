import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-route-back',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './route-back.html',
  styleUrl: './route-back.css',
})
export class RouteBackComponent {
  router = inject(Router);
  currentUrl = '';

  constructor() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.urlAfterRedirects || event.url;
      }
    });
  }

  get show(): boolean {
    return this.currentUrl.includes('/list') || this.currentUrl.includes('/form');
  }

  goBackToDashboard() {
    const u = this.currentUrl;
    if (u.startsWith('/pets/')) return this.router.navigate(['/pets-dashboard']);
    if (u.startsWith('/category/')) return this.router.navigate(['/category-dashboard']);
    if (u.startsWith('/grooming/')) return this.router.navigate(['/grooming-dashboard']);
    if (u.startsWith('/vaccination/')) return this.router.navigate(['/vaccination-dashboard']);
    if (u.startsWith('/customer/') || u.startsWith('/address/')) return this.router.navigate(['/customers-dashboard']);
    if (u.startsWith('/transactions/')) return this.router.navigate(['/transactions-dashboard']);
    if (u.startsWith('/employee/')) return this.router.navigate(['/employee-dashboard']);
    if (u.startsWith('/food/')) return this.router.navigate(['/food-dashboard']);
    if (u.startsWith('/supplier/')) return this.router.navigate(['/supplier-dashboard']);
    if (u.startsWith('/pets-filter/')) return this.router.navigate(['/pets-filter-dashboard']);
    if (u.startsWith('/employee-reports/')) return this.router.navigate(['/employee-reports']);
    if (u.startsWith('/employee-pet-mapping/')) return this.router.navigate(['/employee-pet-mapping']);
    if (u.startsWith('/pet-mapping/grooming/')) return this.router.navigate(['/pet-mapping/grooming']);
    if (u.startsWith('/pet-mapping/food/')) return this.router.navigate(['/pet-mapping/food']);
    if (u.startsWith('/pet-mapping/suppliers/')) return this.router.navigate(['/pet-mapping/suppliers']);
    if (u.startsWith('/pet-mapping/vaccination/')) return this.router.navigate(['/pet-mapping/vaccination']);
    return this.router.navigate(['/']);
  }
}
