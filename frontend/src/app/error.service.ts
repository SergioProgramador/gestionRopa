import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs';


export type HandleError =
  <T> (operation?: string, result?: T) => (error: HttpErrorResponse) => Observable<T>;


@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  createHandleError = (serviceName = '') => <T>
  (operation = 'operation', result = {} as T) => this.handleError(serviceName, operation, result);

  handleError<T> (serviceName = '', operation = 'operation', result = {} as T) {

    return (error: HttpErrorResponse): Observable<T> => {

      let message = (error.error instanceof ErrorEvent) ?
        error.error.message :
        `server returned code ${error.status} with body "${error.error}"`;
      const errorsHeader = error.headers.get('errors');
      if (errorsHeader) {
        const errors = JSON.parse(errorsHeader);
        // Retrieve the Spring MVC errorMessage of the first FieldError
        if ((errors instanceof Array) && (errors.length > 0) && errors[0].errorMessage) {
          message = errors[0].errorMessage;
        }
      }

      console.error(error);
      console.error(`${serviceName}::${operation} failed: ${message}`);

      return Observable.throw(message);
    };

  }
}