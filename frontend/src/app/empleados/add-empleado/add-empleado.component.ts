import { Component, OnInit } from '@angular/core';
import { EmpleadoService } from '../empleado.service';
import { Router } from '@angular/router';
import { ModalService } from 'src/app/modal.service';
import { Empleado } from '../empleado';
import swal from 'sweetalert2';

@Component({
  selector: 'app-add-empleado',
  templateUrl: './add-empleado.component.html',
  styleUrls: ['./add-empleado.component.scss']
})
export class AddEmpleadoComponent implements OnInit {

  empleado: Empleado;
  errorMessage: string;

  constructor(private service: EmpleadoService, private router: Router, private modalService: ModalService) { 
    this.empleado = <Empleado>{};
  }

  ngOnInit() {
  }

  onSubmit() {
    this.service.addEmpleado(this.empleado).subscribe(
      new_empleado => {
        console.log(new_empleado);
        this.modalService.closeModal();
        swal.fire('Nuevo Empleado', `Empleado ${new_empleado.empleado.username} añadido correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

  cerrarModal(){
    this.modalService.closeModal();
    this.empleado.username = null;
    this.empleado.password = null;
    this.empleado.nombre = null;
    this.empleado.apellidos = null;
    this.empleado.direccion = null;
    this.empleado.email = null;
    this.empleado.telefono = null;
  }

}
