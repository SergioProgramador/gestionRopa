import { Injectable, EventEmitter } from '@angular/core';
import { HandleError, ErrorService } from '../error.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Producto } from './producto';
import { catchError, map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AuthServiceService } from '../autentificacion/auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private _verificarSubida = new EventEmitter<any>();

  private handlerError: HandleError;
  url_list_productos = "http://localhost:8080/productos/showproductos";
  url_getOne_producto = "http://localhost:8080/productos";
  url_add_producto = "http://localhost:8080/productos/addProducto";
  url_remove_producto = "http://localhost:8080/productos/removeProducto";
  url_update_producto = "http://localhost:8080/productos/updateProducto";
  url_uploadImagen = "http://localhost:8080/productos/uploadImagen"
  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})

  constructor(private http: HttpClient, private httpErrorHandler: ErrorService, private router: Router,
    private authService: AuthServiceService) { 
    this.handlerError = httpErrorHandler.createHandleError('ProductosService')
  }

  private addAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  //METODO PARA SABER SI ESTA AUTETIFICADO
  private autorizado(e): boolean{
    if(e.status == 401 || e.status == 403) {      
      this.router.navigate[('login')];
      return true;     
    }
    return false;
  }

  get verificarSubida(): EventEmitter<any>{
    return this._verificarSubida;
  }

  //OBTENER TODAS LOS PRODUCTOS
  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.url_list_productos, {headers: this.httpHeaders});
      
  }

  //OBTIENE UN PRODUCTO
  getProductoById(id: number): Observable<Producto> {
    return this.http.get<Producto>(this.url_getOne_producto + '/' + id, {headers: this.httpHeaders})
      .pipe(
          catchError(e => {
            console.error(e.error.info);
            Swal.fire('Error al obtener el producto.', e.error.info, 'error');
            return throwError(e);
          })
      );
  }

  //GUARDA UN PRODUCTO
  addProducto(producto: Producto): Observable<any> {
    return this.http.post<any>(this.url_add_producto, producto, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al crear el producto.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //BORRA UN PRODUCTO
  removeProducto(id: number): Observable<any> {
    return this.http.delete<any>(this.url_remove_producto + '/' + id, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar el producto.', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }

  //ACTUALIZA UN PRODUCTO
  updateProducto(id: number, producto: Producto): Observable<any> {
    return this.http.put<any>(this.url_update_producto + '/' + id, producto, {headers: this.httpHeaders})
      .pipe(
        catchError(e =>{
          console.error(e.error.info);
          Swal.fire('Error al actualizar el producto.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //METODO PARA SUBIR LA IMAGEN
  uploadImagen(file: File, id): Observable<Producto>{

    let formData = new FormData();
    formData.append("file", file);
    formData.append("id", id);
    
    return this.http.post(this.url_uploadImagen, formData).pipe(
      map((response: any) => response.producto as Producto),
      catchError(e => {
        console.error(e.error.info);
        Swal.fire(e.error.info, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}
