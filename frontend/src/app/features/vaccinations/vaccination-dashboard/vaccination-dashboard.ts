import { Component, inject } from "@angular/core";
import { VaccinationService } from "../../../core/services/vaccinationService";
import { Router, RouterModule } from "@angular/router";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-vaccination-dashboard',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './vaccination-dashboard.html'
})

export class VaccinationDashboard {


  router = inject(Router);
  vaccinationService = inject(VaccinationService);

  goToList() {
    this.router.navigate(['/vaccination/list']);
  }

  viewById(id: string) {
    if (!id) return;

    this.vaccinationService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/vaccination/list'], {
          queryParams: { id }
        });
      },
      error: () => {
        alert('Vaccination ID not found ❌');
      }
    });
  }

  updateVaccination(id: string) {
    if (!id) return;

    this.vaccinationService.getById(Number(id)).subscribe({
      next: () => {
        this.router.navigate(['/vaccination/form'], {
          queryParams: { id }
        });
      },
      error: () => alert('ID not found ❌')
    });
  }

  deleteVaccination(id: string) {
    if (!id) return;

    this.vaccinationService.delete(Number(id)).subscribe({
      next: () => {
        alert('Deleted successfully ✅');
        this.router.navigate(['/vaccination/list']);
      },
      error: () => alert('Delete failed ❌')
    });
  }

viewVaccination(arg0: string) {
throw new Error('Method not implemented.');
}
deleteVaccination(arg0: string) {
throw new Error('Method not implemented.');
}


// export class VaccinationDashboard {}

}


