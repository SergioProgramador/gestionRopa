import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { HandleError, ErrorService } from '../error.service';
import { AuthServiceService } from '../autentificacion/auth-service.service';
import { Empleado } from './empleado';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})
  private handlerError: HandleError;

  url_add_empleado = "http://localhost:8080/empleados/addEmpleado";
  url_list_empleados = "http://localhost:8080/empleados/showempleados";
  url_remove_empleado = "http://localhost:8080/empleados/remove";

  constructor(private http: HttpClient, private httpErrorHandler: ErrorService, private authService: AuthServiceService) { 

  }

  //OBTENER TODAS LOS EMPLEADOS
  getEmpleados(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(this.url_list_empleados, {headers: this.httpHeaders});      
  }

  //GUARDA UN EMPLEADO
  addEmpleado(empleado: Empleado): Observable<any> {
    return this.http.post<any>(this.url_add_empleado, empleado, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al crear el empleado.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //BORRA UN EMPLEADO
  removeEmpleado(username: string): Observable<any> {
    return this.http.delete<any>(this.url_remove_empleado + '/' + username, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar el empleado', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }
}
