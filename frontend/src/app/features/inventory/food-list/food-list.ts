import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './food-list.html',
  styleUrls: ['./food-list.css']
})
export class FoodListComponent {

  searchText = '';
  showForm = false;

  foods = [
    { id: 1, name: 'Dog Food', brand: 'Pedigree', type: 'Dry', quantity: 10, price: 500 }
  ];

  newFood: any = {};

  get filteredFoods() {
    return this.foods.filter(f =>
      f.name.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  openForm() {
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
    this.newFood = {};
  }

  saveFood() {
    this.newFood.id = this.foods.length + 1;
    this.foods.push(this.newFood);
    this.closeForm();
  }
}