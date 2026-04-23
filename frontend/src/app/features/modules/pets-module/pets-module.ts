import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-pets-module',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './pets-module.html',
  styleUrl: './pets-module.css',
})
export class PetsModule {}