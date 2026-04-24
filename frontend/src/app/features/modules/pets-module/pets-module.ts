// This file holds the Angular logic for pets module.
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-pets-module',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './pets-module.html',
  styleUrl: './pets-module.css',
})
export class PetsModule {}
