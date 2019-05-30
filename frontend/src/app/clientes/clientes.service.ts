import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Cliente } from './cliente';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HandleError, ErrorService } from '../error.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  private handlerError: HandleError;
  url_list_clientes = "http://localhost:8080/clientes/showclientes";
  url_getOne_cliente = "http://localhost:8080/clientes";
  url_add_cliente = "http://localhost:8080/clientes/addCliente";
  url_remove_cliente = "http://localhost:8080/categorias/removeCliente";
  url_update_cliente = "http://localhost:8080/categorias/updateCliente";
  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})

  constructor(private http: HttpClient, private httpErrorHandler: ErrorService) { 
    this.handlerError = httpErrorHandler.createHandleError('ClientesService')
  }

  //OBTENER TODAS LOS CLIENTES
  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.url_list_clientes)
      .pipe(
        catchError(this.handlerError('getClientes', []))        
      );
  }

  //OBTIENE UN CLIENTE
  getClienteById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(this.url_getOne_cliente + '/' + id)
      .pipe(
          catchError(e => {
            console.error(e.error.info);
            Swal.fire('Error al obtener el cliente.', e.error.info, 'error');
            return throwError(e);
          })
      );
  }

  //GUARDA UN CLIENTE
  addCliente(cliente: Cliente): Observable<any> {
    return this.http.post<any>(this.url_add_cliente, cliente, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al crear el cliente.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //BORRA UN CLIENTE
  removeCliente(id: number): Observable<any> {
    return this.http.delete<any>(this.url_remove_cliente + '/' + id, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar el cliente.', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }

  //ACTUALIZA UN CLIENTE
  updateCliente(id: number, cliente: Cliente): Observable<any> {
    return this.http.put<any>(this.url_update_cliente + '/' + id, cliente, {headers: this.httpHeaders})
      .pipe(
        catchError(e =>{
          console.error(e.error.info);
          Swal.fire('Error al actualizar el cliente.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }
}
