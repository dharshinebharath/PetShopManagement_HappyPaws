// This service handles the app-side requests and data flow for notification.
import { Injectable, signal } from '@angular/core';

export type NotificationVariant = 'success';

export interface NotificationState {
  message: string;
  variant: NotificationVariant;
}

@Injectable({ providedIn: 'root' })
export class NotificationService {
  readonly notification = signal<NotificationState | null>(null);
  private hideTimer: ReturnType<typeof setTimeout> | null = null;

  showSuccess(message: string, duration = 4000) {
    this.show({ message, variant: 'success' }, duration);
  }

  clear() {
    if (this.hideTimer) {
      clearTimeout(this.hideTimer);
      this.hideTimer = null;
    }

    this.notification.set(null);
  }

  private show(notification: NotificationState, duration: number) {
    this.clear();
    this.notification.set(notification);
    this.hideTimer = setTimeout(() => {
      this.notification.set(null);
      this.hideTimer = null;
    }, duration);
  }
}
