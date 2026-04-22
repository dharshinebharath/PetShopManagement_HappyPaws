import { Component, OnInit } from '@angular/core';
import { Pet } from '../../../core/services/pet';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pet-list',
  imports: [CommonModule],
  templateUrl: './pet-list.html',
  styleUrl: './pet-list.css',
})
export class PetList implements OnInit{
   pets: any[] = [];

  constructor(private petService: Pet) {}

  ngOnInit(): void {
    this.loadPets();
  }

  loadPets() {
    this.petService.getAllPets().subscribe({
      next: (res) => {
        this.pets = res.data;   // VERY IMPORTANT
        console.log(this.pets);
      },
      error: (err) => {
        console.error("Error fetching pets", err);
      }
    });
  }
}
