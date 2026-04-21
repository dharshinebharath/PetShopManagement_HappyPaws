import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodListComponent } from './food-list/food-list';

@Component({
  selector: 'app-inventory',
  standalone: true,
  imports: [CommonModule, FoodListComponent],
  templateUrl: './inventory.html',
  styleUrls: ['./inventory.css']
})
export class InventoryComponent {
  activeTab: 'food' | 'supplier' = 'food';
}