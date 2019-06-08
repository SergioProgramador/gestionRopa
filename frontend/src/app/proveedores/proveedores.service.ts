import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Proveedores } from './proveedores. model';
import { catchError } from 'rxjs/operators';
import {ErrorService, HandleError} from '../error.service';
import { AuthServiceService } from '../autentificacion/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {

  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})
  private handlerError: HandleError;
  url_list_proveedores = "http://localhost:8080/proveedores/showproveedores";
  url_getOne_proveedor ="http://localhost:8080/proveedores";
  url_add_proveedor = "http://localhost:8080/proveedores/addProveedor";
  url_remove_proveedor = "http://localhost:8080/proveedores/removeProveedor";
  url_update_proveedor = "http://localhost:8080/proveedores/updateProveedor";

  constructor(private http: HttpClient, private httpErrorHandler: ErrorService, private authService: AuthServiceService) {
    this.handlerError = httpErrorHandler.createHandleError('CategoriasService');
   }

   private addAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

   //OBTENER TODAS LOS PROVEEDORES
  getProveedores(): Observable<Proveedores[]> {
    return this.http.get<Proveedores[]>(this.url_list_proveedores, {headers: this.addAuthorizationHeader()})
      .pipe(
        catchError(this.handlerError('getProveedores', []))
        
      );
  }

   //OBTIENE UN PROVEEDOR
   getProveedorById(id: string): Observable<any> {
    return this.http.get<any>(this.url_getOne_proveedor + '/' + id, {headers: this.addAuthorizationHeader()})
      .pipe(
          catchError(e => {
            console.error(e.error.info);
            Swal.fire('Error al obtener el proveedor.', e.error.info, 'error');
            return throwError(e);
          })
      );
  }

  //GUARDA UN PROVEEDOR
  addProveedor(proveedor: Proveedores): Observable<any> {
    return this.http.post<any>(this.url_add_proveedor, proveedor, {headers: this.addAuthorizationHeader()})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al crear el proveedor.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //BORRA UN PROVEEDOR
  removeProveedor(id: number): Observable<any> {
    return this.http.delete<any>(this.url_remove_proveedor + '/' + id, {headers: this.addAuthorizationHeader()})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar el proveedor', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }

  //ACTUALIZA UN PROVEEDOR
  updateProveedor(id: number, proveedor: Proveedores): Observable<any> {
    return this.http.put<any>(this.url_update_proveedor + '/' + id, proveedor, {headers: this.addAuthorizationHeader()})
      .pipe(
        catchError(e =>{
          console.error(e.error.info);
          Swal.fire('Error al actualizar el proveedor', e.error.info, 'error');
          return throwError(e);
        })
      );
  }
}
