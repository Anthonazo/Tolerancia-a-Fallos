import { HttpClient, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment/environment';


@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  constructor(private http: HttpClient) { }

  public getPersonas(){
    return this.http.get(environment.WS_PATH + 'list');
  }

  public crearPersona(persona: any){
    return this.http.post(environment.WS_PATH + 'crear', persona);
  }

  public eliminarPersona(cedula: string){
    return this.http.delete(environment.WS_PATH + 'eliminar?cedula=' + cedula);
  }

  public actualizarPersona(persona: any){
    return this.http.put(environment.WS_PATH + 'actualizar', persona);
  }

}
