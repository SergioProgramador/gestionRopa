import { Component, OnInit } from '@angular/core';
import { Empleado } from 'src/app/empleados/empleado';
import swal from 'sweetalert2';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  mensaje: string = "INICIO DE SESIÓN!";
  empleado: Empleado;

  constructor(private service: AuthServiceService, private router: Router) { 
    this.empleado = new Empleado();
  }

  ngOnInit() {
  
  }

  login(): void{
    console.log(this.empleado);
    if(this.empleado.username == null || this.empleado.password == null){
      swal.fire('Error al Iniciar Sesión', 'Username o password vacíos!', 'error');
      return;
    }

    this.service.login(this.empleado).subscribe(response =>{
      console.log(response);
      //console.log(JSON.parse(atob(response.access_token.split(".")[1]))); //con esto obtenemos
       //el payload del token(la parte de los datos), lo decodificamos con la funcion de JS 'atob'
      //y obtenemos todos los datos en una estructura JSON.

      this.service.saveEmpleado(response.access_token);
      this.service.saveToken(response.access_token);

      let empleado = this.service.empleado; // es un metodo de tipo get
      this.router.navigate(["/productos/showproductos"]);
      swal.fire('Login', `Bienvenido ${empleado.username}, has iniciado sesión correctamente!`, 'success');
    }, error =>{
      if(error.status == 400){
        swal.fire('Error al Iniciar Sesión', 'Usuario o password incorrectos!', 'error');
      }
    }
    );


  }

  home(): void{
    this.router.navigate(['/home']);
  }

}
