// This shared component supports footer across multiple screens.
import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
// Footer component
export class FooterComponent {
  // Current year
  currentYear = new Date().getFullYear();
}
