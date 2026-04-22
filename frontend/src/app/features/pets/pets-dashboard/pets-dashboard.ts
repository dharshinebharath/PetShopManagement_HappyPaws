import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PetList } from '../pet-list/pet-list';

@Component({
  selector: 'app-pets-dashboard',
  imports: [[CommonModule, PetList]],
  templateUrl: './pets-dashboard.html',
  styleUrl: './pets-dashboard.css',
})
export class PetsDashboard {
  showApis: boolean = false;

  openApis() {
    this.showApis = true;
}}