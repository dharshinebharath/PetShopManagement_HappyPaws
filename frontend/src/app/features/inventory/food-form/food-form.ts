import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Food } from '../../../core/services/food';

@Component({
  selector: 'app-food-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './food-form.html',
  styleUrls: ['./food-form.css'],
})
export class FoodFormComponent implements OnInit {

  food: Food = {
    name: '',
    brand: '',
    type: '',
    quantity: 0,
    price: 0
  };

  id!: number;
  private baseUrl = 'http://localhost:8081/api/v1/food';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['id']) {
        this.id = params['id'];
        this.http.get<Food>(`${this.baseUrl}/${this.id}`).subscribe(data => {
          this.food = data;
        });
      }
    });
  }

  submit() {
    if (this.id) {
      this.http.put(`${this.baseUrl}/${this.id}`, this.food).subscribe(() => {
        this.router.navigate(['/inventory/food/list']);
      });
    } else {
      this.http.post(this.baseUrl, this.food).subscribe(() => {
        this.router.navigate(['/inventory/food/list']);
      });
    }
  }
}