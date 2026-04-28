// This shared component supports navbar across multiple screens.
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { NotificationService } from '../../../core/services/notification';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})

export class NavbarComponent {

  router = inject(Router);
  notification = inject(NotificationService);
  currentUrl = '';
  displayName = 'Happy Paws';

  constructor() {
    this.currentUrl = this.router.url;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.urlAfterRedirects || event.url;
        this.syncDisplayName();
      }
    });
    this.syncDisplayName();
  }

  // Method to check if navbar should be shown
  get showNavbar(): boolean {
    return this.currentUrl !== '/' && !this.currentUrl.startsWith('/login');
  }

  // Method to sync display name
  private syncDisplayName() {
    const byStorage = sessionStorage.getItem('activeUserName');
    if (byStorage) {
      this.displayName = byStorage;
      return;
    }

    const url = this.currentUrl;
    if (
      url.startsWith('/pets') ||
      url.startsWith('/category') ||
      url.startsWith('/pets-module')
    ) {
      this.displayName = 'Mahakarpagam';
    } else if (
      url.startsWith('/pet-services-module') ||
      url.startsWith('/grooming') ||
      url.startsWith('/vaccination')
    ) {
      this.displayName = 'Dharshine';
    } else if (
      url.startsWith('/customer') ||
      url.startsWith('/transactions') ||
      url.startsWith('/address') ||
      url.startsWith('/customers-module')
    ) {
      this.displayName = 'Revathi';
    } else if (
      url.startsWith('/inventory') ||
      url.startsWith('/food') ||
      url.startsWith('/supplier')
    ) {
      this.displayName = 'Shirlly';
    } else if (
      url.startsWith('/employee') ||
      url.startsWith('/employee-module')
    ) {
      this.displayName = 'Priyadharshini';
    } else {
      this.displayName = 'Happy Paws';
    }
  }

  // Logout method
  logout() {
    sessionStorage.removeItem('activeModuleLabel');
    sessionStorage.removeItem('activeModuleRoute');
    sessionStorage.removeItem('activeUserName');
    sessionStorage.removeItem('activeUserRole');
    this.notification.showSuccess('Logged out successfully');
    this.router.navigate(['/']);
  }
}
