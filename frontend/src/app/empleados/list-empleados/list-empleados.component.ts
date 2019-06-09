import { Component, OnInit } from '@angular/core';
import { Empleado } from '../empleado';
import { EmpleadoService } from '../empleado.service';
import { ModalService } from 'src/app/modal.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-list-empleados',
  templateUrl: './list-empleados.component.html',
  styleUrls: ['./list-empleados.component.scss']
})
export class ListEmpleadosComponent implements OnInit {

  errorMessage: String;
  empleados: Empleado[];

  empleadoSeleccionado: Empleado;

  constructor(private service: EmpleadoService, private modalService: ModalService) { }

  ngOnInit() {
    this.service.getEmpleados().subscribe(
      empleados => {
        this.empleados = empleados;
      })
  }

  delete(empleado: Empleado): void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar el empleado ${empleado.username}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.service.removeEmpleado(empleado.username).subscribe(
          response => {
            this.empleados = this.empleados.filter(em => em !== empleado)
            swal.fire(
              'Empleado eliminado!',
              `Empleado ${empleado.username} eliminado con éxito`,
              'success'
            )
          }
        );
        swal.fire(  
          'Eliminado!',
          'El empleado ha sido eliminado',
          'success'
        )
      }
    })
  }

  abrirModal(){
    this.modalService.openModal();
  }

  abrirModal2(empleado: Empleado){
    this.empleadoSeleccionado = empleado;
    this.modalService.openModal2();
  }

}
