import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-pet-services-module',
  imports: [RouterLink, RouterOutlet],
  templateUrl: './pet-services-module.html',
  styleUrl: './pet-services-module.css',
})
export class PetServicesModule {}
