import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/autentificacion/auth-service.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit() {
  }

  logout(): void{
    swal.fire('Cerrar Sesión', `${this.authService.empleado.username} has cerrado sesión correctamente`, 'success');
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
