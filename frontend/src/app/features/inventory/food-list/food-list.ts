import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Food } from '../../../core/services/food';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './food-list.html',
  styleUrls: ['./food-list.css']
})
export class FoodListComponent implements OnInit {

  foods: Food[] = [];
  searchId!: number;

  private baseUrl = 'http://localhost:8081/api/v1/food';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.http.get<Food[]>(this.baseUrl).subscribe(data => {
      this.foods = data;
    });
  }

  search() {
    if (this.searchId) {
      this.http.get<Food>(`${this.baseUrl}/${this.searchId}`).subscribe(data => {
        this.foods = [data];
      });
    }
  }

  delete(id: number) {
    this.http.delete(`${this.baseUrl}/${id}`).subscribe(() => {
      this.getAll();
    });
  }

  edit(id: number) {
    this.router.navigate(['/inventory/food/form'], { queryParams: { id } });
  }
}