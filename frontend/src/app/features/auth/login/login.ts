import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  route:ActivatedRoute=inject(ActivatedRoute);
  router:Router=inject(Router);
  moduleName:string='';
loginForm: FormGroup=new FormGroup({
  username: new FormControl(''),
  password: new FormControl('')
})

  ngOnInit()
  {
    this.moduleName=this.route.snapshot.paramMap.get('module') || '';
  }
login() {
  if (this.loginForm.value.username === 'admin' && this.loginForm.value.password === 'admin') {

    const routeMap: any = {
      pets: 'pets-module',
      petservices: '/pet-services-module',
      customers: 'customers-module',
      inventory: 'inventory-module',
      employees: 'employees-module'
    };
 
  this.router.navigate(['/'+ routeMap[this.moduleName]])


}

}
}

