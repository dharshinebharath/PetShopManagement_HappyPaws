// This shared component supports toast across multiple screens.
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { NotificationService } from '../../../core/services/notification';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.html',
  styleUrl: './toast.css',
})
// Toast component
export class ToastComponent {
  // Injecting required services
  notification = inject(NotificationService);

  // Method to dismiss toast
  dismiss() {
    this.notification.clear();
  }
}
