import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inventory-module',
  standalone: true,
  imports: [RouterLink,RouterOutlet],
  templateUrl: './inventory-module.html',
  styleUrl: './inventory-module.css',
})
export class InventoryModule {

}
