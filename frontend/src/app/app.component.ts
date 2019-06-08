import { Component } from '@angular/core';
import { AuthServiceService } from './autentificacion/auth-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private authService: AuthServiceService){

  }

  
}
