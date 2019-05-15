import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ErrorService, HandleError} from '../error.service';
import {catchError} from 'rxjs/operators';
import { Categoria } from './categoria';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  private handlerError: HandleError;
  url_list_categorias = "http://localhost:8080/categorias/showcategorias";

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
  


}
