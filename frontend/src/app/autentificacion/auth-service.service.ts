import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Empleado } from '../empleados/empleado';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private url = "http://localhost:8080/oauth/token";
  private _empleado: Empleado;
  private _token: string;

  constructor(private http: HttpClient) { }

  public get empleado(): Empleado{
    if(this._empleado != null){
      return this._empleado;
    }else if(this._empleado == null && sessionStorage.getItem('empleado') != null){
      this._empleado = JSON.parse(sessionStorage.getItem('empleado')) as Empleado;
      return this._empleado;
    }
    return new Empleado();
  }

  public get token(): string{
    if(this._token != null){
      return this._token;
    }else if(this._token == null && sessionStorage.getItem('token') != null){
      this._token = sessionStorage.getItem('token') ;
      return this._token;
    }
    return null;
  }

  login(empleado: Empleado): Observable<any>{
    const claims = btoa ('angularapp' + ':' + '12345');  
    const httpHeaders = new HttpHeaders({'Content-Type':'application/x-www-form-urlencoded', 
    'Authorization':'Basic '+ claims});
    let params =new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('username', empleado.username);
    params.set('password', empleado.password);
    return this.http.post(this.url, params.toString(), {headers: httpHeaders});
  }

  logout(): void{
    this._token = null;
    this._empleado = null;
    sessionStorage.clear(); //eliminamos el empleado y el token del sessionStorage
  }

  saveToken(accessToken: string): void{
    this._token =   accessToken;
    sessionStorage.setItem('token', accessToken);
  }

  saveEmpleado(accessToken: string): void{
    let payload = this.obtenerPayload(accessToken);
    this._empleado = new Empleado();
    this._empleado.nombre = payload.nombre;
    this._empleado.apellidos = payload.apellido;
    this._empleado.username = payload.user_name;
    this._empleado.email = payload.email;
    this._empleado.roles = payload.authorities;

    sessionStorage.setItem('empleado', JSON.stringify(this._empleado)); //stringify convierte el objeto en string o texto.
  }

  obtenerPayload(accessToken: string): any{
    if(accessToken != null){
      return JSON.parse(atob(accessToken.split(".")[1]))
    }
    return null;
  }

  isAuthenticated(): boolean{
    let payload = this.obtenerPayload(this.token);
    if(payload != null && payload.user_name && payload.user_name.length>0){
      return true;
    }
    return false;
  }
}
