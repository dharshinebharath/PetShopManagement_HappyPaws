import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class NavbarComponent {
  router = inject(Router);
  currentUrl = '';
  moduleLabel = '';

  constructor() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.urlAfterRedirects || event.url;
        this.syncModuleLabel();
      }
    });
    this.syncModuleLabel();
  }

  get showNavbar(): boolean {
    return this.currentUrl !== '/' && !this.currentUrl.startsWith('/login');
  }

  private syncModuleLabel() {
    const byStorage = sessionStorage.getItem('activeModuleLabel');
    if (byStorage) {
      this.moduleLabel = byStorage;
      return;
    }

    const url = this.currentUrl;
    if (url.startsWith('/pets')) this.moduleLabel = 'Maha Karpagam Module';
    else if (url.startsWith('/pet-services') || url.startsWith('/grooming') || url.startsWith('/vaccination')) this.moduleLabel = 'Dharshine Module';
    else if (url.startsWith('/customer') || url.startsWith('/transactions') || url.startsWith('/address')) this.moduleLabel = 'Revathi Module';
    else if (url.startsWith('/inventory') || url.startsWith('/food') || url.startsWith('/supplier')) this.moduleLabel = 'Shirlly Module';
    else if (url.startsWith('/employee')) this.moduleLabel = 'Priyadharshini Module';
    else this.moduleLabel = 'Happy Paws';
  }

  goHome() {
    this.router.navigate(['/']);
  }

  logout() {
    sessionStorage.removeItem('activeModuleLabel');
    sessionStorage.removeItem('activeModuleRoute');
    this.router.navigate(['/']);
  }
}
