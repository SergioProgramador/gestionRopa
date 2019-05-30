import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Factura } from './factura';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Producto } from '../productos/producto';

@Injectable({
  providedIn: 'root'
})
export class FacturasService {

  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})
  url_getOne_factura = "http://localhost:8080/facturas";
  url_remove_factura = "http://localhost:8080/facturas/removeFactura"
  url_getProductosFiltrados = "http://localhost:8080/facturas/productosFiltrados"

  constructor(private httpClient: HttpClient) { 

  }

  //OBTIENE UNA FACTURA
  getFacturaById(id: number): Observable<Factura> {
    return this.httpClient.get<Factura>(this.url_getOne_factura + '/' + id)
      .pipe(
          catchError(e => {
            console.error(e.error.info);
            Swal.fire('Error al obtener la factura.', e.error.info, 'error');
            return throwError(e);
          })
      );
  }

  //BORRA UNA FACTURA
  removeFactura(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.url_remove_factura + '/' + id, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar la factura.', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }

  //PRODUCTOS FILTRADOS
  getProductosFiltrados(nombre: string): Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(this.url_getProductosFiltrados + '/' + nombre);
  }
}
