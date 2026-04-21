
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
<<<<<<< HEAD
import { provideHttpClient } from '@angular/common/http'; 
export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideHttpClient()]
  
=======


export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
  ]
>>>>>>> b6ae5c2dade2e3b92eba78d3054823df8a3ae120
};


