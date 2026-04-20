import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodListComponent } from './food-list/food-list';
import { SupplierListComponent } from './supplier-list/supplier-list';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-inventory',
  standalone: true,
  imports: [CommonModule, FoodListComponent, SupplierListComponent,FormsModule],
  templateUrl: './inventory.html',
  styleUrls: ['./inventory.css']
})
export class InventoryComponent {

 
  activeTab: 'food' | 'supplier' = 'food';

  searchText: string = '';

  showForm: boolean = false;
}