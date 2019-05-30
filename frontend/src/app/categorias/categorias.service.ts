import { Injectable, EventEmitter } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ErrorService, HandleError} from '../error.service';
import {catchError} from 'rxjs/operators';
import { Categoria } from './categoria';
import { Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';


@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  _actualizacion = new EventEmitter<any>();

  private handlerError: HandleError;
  url_list_categorias = "http://localhost:8080/categorias/showcategorias";
  url_getOne_categoria = "http://localhost:8080/categorias";
  url_add_categoria = "http://localhost:8080/categorias/add";
  url_remove_categoria = "http://localhost:8080/categorias/removeCat";
  url_update_categoria = "http://localhost:8080/categorias/updateCat";
  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'})

  constructor(private http: HttpClient, private httpErrorHandler: ErrorService) { 
    this.handlerError = httpErrorHandler.createHandleError('CategoriasService');
  }

  //OBTENER TODAS LAS CATEGORIAS
  getCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.url_list_categorias)
      .pipe(
        catchError(this.handlerError('getCategorias', []))
        
      );
  }

  //OBTIENE UNA CATEGORIA
  getCategoriaById(id: number): Observable<any> {
    return this.http.get<any>(this.url_getOne_categoria + '/' + id)
      .pipe(
          catchError(e => {
            console.error(e.error.info);
            Swal.fire('Error al obtener la categoria.', e.error.info, 'error');
            return throwError(e);
          })
      );
  }

  //GUARDA UNA CATEGORIA
  addCategoria(categoria: Categoria): Observable<any> {
    return this.http.post<any>(this.url_add_categoria, categoria, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al crear la categoria.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }

  //BORRA UNA CATEGORIA
  removeCategoria(id: number): Observable<any> {
    return this.http.delete<any>(this.url_remove_categoria + '/' + id, {headers: this.httpHeaders})
      .pipe(
        catchError(e => {
          console.error(e.error.info);
          Swal.fire('Error al eliminar la categoria.', e.error.info, 'error');
          return throwError(e);
        })        
      );
  }

  //ACTUALIZA UNA CATEGORIA
  updateCategoria(id: number, categoria: Categoria): Observable<any> {
    return this.http.put<any>(this.url_update_categoria + '/' + id, categoria, {headers: this.httpHeaders})
      .pipe(
        catchError(e =>{
          console.error(e.error.info);
          Swal.fire('Error al actualizar la categoria.', e.error.info, 'error');
          return throwError(e);
        })
      );
  }
}
