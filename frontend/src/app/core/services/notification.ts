// This service handles the app-side requests and data flow for notification.
import { Injectable, signal } from '@angular/core';

// Type for notification variant
export type NotificationVariant = 'success';

// Interface for notification state
export interface NotificationState {
  message: string;
  variant: NotificationVariant;
}

// This service is used to display notifications to the user.
@Injectable({ providedIn: 'root' })
export class NotificationService {
  // Signal to hold the notification state
  readonly notification = signal<NotificationState | null>(null);
  // Timer for hiding the notification
  private hideTimer: ReturnType<typeof setTimeout> | null = null;

  // Method to show a success notification
  showSuccess(message: string, duration = 4000) {
    // Calling the private show method to display the notification
    this.show({ message, variant: 'success' }, duration);
  }

  clear() {
    // Clearing the notification timer if it exists
    if (this.hideTimer) {
      clearTimeout(this.hideTimer);
      this.hideTimer = null;
    }

    // Clearing the notification
    this.notification.set(null);
  }

  // Private method to show the notification
  private show(notification: NotificationState, duration: number) {
    // Clearing any existing notifications
    this.clear();
    // Setting the new notification
    this.notification.set(notification);
    // Setting a timer to clear the notification after the specified duration
    this.hideTimer = setTimeout(() => {
      this.notification.set(null);
      this.hideTimer = null;
    }, duration);
  }
}
