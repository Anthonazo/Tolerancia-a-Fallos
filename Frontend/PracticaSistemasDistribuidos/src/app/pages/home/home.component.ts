import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/models/Persona';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  personas: any[] = [];
  persona: Persona = new Persona();

  constructor(private personaService: PersonaService) { }

  ngOnInit(): void {
    this.getPersonas();
  }

  getPersonas(): void {
    this.personaService.getPersonas().subscribe((data: any) => {
      this.personas = data;
    });
  }

  agregarPersona(): void {
    this.personaService.crearPersona(this.persona).subscribe(() => {
      this.getPersonas();
      this.persona = new Persona();
    })
  }

  eliminarPersona(cedula: string): void {
    this.personaService.eliminarPersona(cedula).subscribe(() => {
      this.getPersonas();
    });
  }

  actualizarPersona(): void {
    this.personaService.actualizarPersona(this.persona).subscribe(() => {
      this.getPersonas();
    });
  }
}





