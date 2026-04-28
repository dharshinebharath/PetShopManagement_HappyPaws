// This shared component supports route back across multiple screens.
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-route-back',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './route-back.html',
  styleUrl: './route-back.css',
})

export class RouteBackComponent {

  router = inject(Router);
  route = inject(ActivatedRoute);
  currentUrl = this.router.url;

  constructor() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const url = event.urlAfterRedirects || event.url;
        this.currentUrl = url.split('?')[0]; // Strip query params to ensure accurate matching
      }
    });
  }

  // Method to check if route back should be shown
  get show(): boolean {
    return this.isDashboardRoute || this.currentUrl.includes('list') || this.currentUrl.includes('/form') || this.currentUrl.includes('/add') || this.currentUrl.includes('/edit') || this.currentUrl.includes('/assignment');
  }

  // Method to get button label
  get buttonLabel(): string {
    return this.isDashboardRoute ? 'Back To Module' : 'Back To Dashboard';
  }

  // Method to check if dashboard route
  private get isDashboardRoute(): boolean {
    // These routes act as entry points for each module, so they should return to the module card page.
    const dashboards = [
      '/pets-dashboard',
      '/category-dashboard',
      '/grooming-dashboard',
      '/vaccination-dashboard',
      '/customers-dashboard',
      '/transactions-dashboard',
      '/employee-dashboard',
      '/food-dashboard',
      '/supplier-dashboard',
      '/pets-filter-dashboard',
      '/employee-reports',
      '/employee-pet-mapping',
      '/pet-mapping/grooming',
      '/pet-mapping/food',
      '/pet-mapping/suppliers',
      '/pet-mapping/vaccination'
    ];

    return dashboards.includes(this.currentUrl);
  }

  // Method to go back to dashboard
  goBackToDashboard() {
    const u = this.currentUrl;
    if (u.startsWith('/pets/')) return this.router.navigate(['/pets-dashboard']);
    if (u.startsWith('/category/')) return this.router.navigate(['/category-dashboard']);
    if (u.startsWith('/grooming/')) return this.router.navigate(['/grooming-dashboard']);
    if (u.startsWith('/vaccination/')) return this.router.navigate(['/vaccination-dashboard']);
    if (u.startsWith('/customer/')) return this.router.navigate(['/customers-dashboard'], { queryParams: { tab: 'customer' } });
    if (u.startsWith('/address/')) return this.router.navigate(['/customers-dashboard'], { queryParams: { tab: 'address' } });
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

  // Method to go back
  goBack() {
    if (!this.isDashboardRoute) {
      return this.goBackToDashboard();
    }

    // Keep the navigation predictable by sending each dashboard back to its own module home.
    const u = this.currentUrl;
    if (u === '/pets-dashboard' || u === '/category-dashboard' || u === '/pets-filter-dashboard') {
      return this.router.navigate(['/pets-module']);
    }
    if (u === '/grooming-dashboard' || u === '/vaccination-dashboard' || u === '/pet-mapping/grooming' || u === '/pet-mapping/vaccination') {
      return this.router.navigate(['/pet-services-module']);
    }
    if (u === '/food-dashboard' || u === '/supplier-dashboard' || u === '/pet-mapping/food' || u === '/pet-mapping/suppliers') {
      return this.router.navigate(['/inventory-module']);
    }
    if (u === '/customers-dashboard' || u === '/transactions-dashboard') {
      return this.router.navigate(['/customertransaction-module']);
    }
    if (u === '/employee-dashboard' || u === '/employee-pet-mapping' || u === '/employee-reports') {
      return this.router.navigate(['/employee-module']);
    }

    return this.router.navigate(['/']);
  }
}
